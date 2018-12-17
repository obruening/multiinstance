package obruening.multiinstance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public abstract class Controller {
	
	@Autowired
	protected ConfigurableApplicationContext springContext;
	
	protected Parent loadFXML(String resourceName) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));
		fxmlLoader.setControllerFactory(springContext::getBean);
    	return fxmlLoader.load();
    }

}
