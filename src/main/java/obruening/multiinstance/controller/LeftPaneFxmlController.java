package obruening.multiinstance.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import obruening.multiinstance.command.Command;
import obruening.multiinstance.config.UpdateEvent;

@Component
public class LeftPaneFxmlController extends Controller {
	
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Autowired
    private List<Command> commandList;
    
    @FXML
    private VBox vBox;

	private EventHandler<ActionEvent> eventHandler;
	
    @FXML
    public void initialize() {
    	
    	 eventHandler = new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent event) {
 				
 				Command command = (Command)((Button)event.getSource()).getUserData();
 				command.execute();
 				applicationEventPublisher.publishEvent(new UpdateEvent(command));
 			}
 		};
    	
 		
 		for (Command command : commandList) {
 			vBox.getChildren().add(createCommandButton(command));
		}
    }
    
    private Button createCommandButton(Command command) {
    	Button button = new Button(command.getName());
    	button.setMinWidth(100);
    	button.setUserData(command);
    	button.setOnAction(eventHandler);
    	
    	double margin = 10;
    	VBox.setMargin(button, new Insets(margin, margin, 0, margin));
    	
    	return button;
    }

}
