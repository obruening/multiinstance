package obruening.multiinstance.controller;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
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
public class HiProcinstTableViewFxmlController extends Controller implements ApplicationListener<UpdateEvent> {

	@Autowired
	private HistoryService historyService;

	@FXML
	private TableView<HistoricProcessInstance> hiProcinstTableView;

	@FXML
	private TableColumn<HistoricProcessInstance, String> idColumn;
	
	@FXML
	private TableColumn<HistoricProcessInstance, String> processDefinitionIdColumn;

	@FXML
	private TableColumn<HistoricProcessInstance, Date> startTimeColumn;

	@FXML
	private TableColumn<HistoricProcessInstance, Date> endTimeColumn;
	

	@FXML
	public void initialize() throws MalformedURLException {

		idColumn.setCellValueFactory(new PropertyValueFactory<HistoricProcessInstance, String>("id"));
		processDefinitionIdColumn.setCellValueFactory(new PropertyValueFactory<HistoricProcessInstance, String>("processDefinitionId"));
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<HistoricProcessInstance, Date>("startTime"));
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<HistoricProcessInstance, Date>("endTime"));

		update();
	}

	@Override
	public void onApplicationEvent(UpdateEvent event) {
		
    	update();
	}

	private void update() {

		List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().list();
		ObservableList<HistoricProcessInstance> observableHistoricProcessInstanceList = FXCollections.observableArrayList(historicProcessInstanceList);
		hiProcinstTableView.setItems(observableHistoricProcessInstanceList);
	}

}
