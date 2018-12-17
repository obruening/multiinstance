package obruening.multiinstance.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import obruening.multiinstance.config.UpdateEvent;

@Component
public class TaskTableViewFxmlController extends Controller implements ApplicationListener<UpdateEvent> {

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
