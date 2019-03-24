package obruening.multiinstance.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import obruening.multiinstance.config.UpdateEvent;

@Component
public class TaskTableViewFxmlController extends Controller implements ApplicationListener<UpdateEvent> {

	private static Logger logger = LoggerFactory.getLogger(TaskTableViewFxmlController.class);

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTableView;

    @FXML
    private TableColumn<Task, String> idColumn;
    
    @FXML
    private TableColumn<Task, String> taskDefinitionKeyColumn;

    @FXML
    private TableColumn<Task, String> processInstanceIdColumn;

    @FXML
    private TableColumn<Task, String> processDefinitionIdColumn;

    @FXML
    private TableColumn<Task, String> assigneeColumn;

    @FXML
    public void initialize() throws MalformedURLException {

        idColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("id"));
        taskDefinitionKeyColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskDefinitionKey"));
        processInstanceIdColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("processInstanceId"));
        processDefinitionIdColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("processDefinitionId"));
        assigneeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("assignee"));

        
        taskTableView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				if (event instanceof MouseEvent) {
					MouseEvent mouseEvent = (MouseEvent)event;
					MouseButton button = mouseEvent.getButton();
					if (button == MouseButton.SECONDARY) {
						Task task = taskTableView.getSelectionModel().getSelectedItem();
						taskService.complete(task.getId());
						applicationEventPublisher.publishEvent(new UpdateEvent(this));
					}
				}
			}
		});
        update();
    }

    @Override
    public void onApplicationEvent(UpdateEvent event) {
        update();
    }

    private void update() {

        List<Task> taskList = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
        ObservableList<Task> observableTaskList = FXCollections.observableArrayList(taskList);
        taskTableView.setItems(observableTaskList);
    }

}
