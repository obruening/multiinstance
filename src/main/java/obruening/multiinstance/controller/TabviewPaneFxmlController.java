package obruening.multiinstance.controller;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

@Component
public class TabviewPaneFxmlController extends Controller {

	@Autowired
	private HostServices hostServices;
	
	@FXML
	private Tab taskTab;
	
	@FXML
	private Tab hiProcinstTab;

	@FXML
	private TabPane tabPane;
	
	@FXML
	private WebView h2WebView;
	
	@FXML
	private Hyperlink h2Hyperlink;

	@FXML
	private WebView helpWebView;
	
	private final static String H2_URL = "http://localhost:8082";
	
	private final static String HELP = "/html/multiinstance.html";

	@FXML
	public void initialize() throws IOException {
		
		taskTab.setContent(loadFXML("/fxml/task_table_view.fxml"));
		hiProcinstTab.setContent(loadFXML("/fxml/hi_procinst_table_view.fxml"));
		
		URL h2Url = new URL(H2_URL);
    	WebEngine h2WebEngine = h2WebView.getEngine();
    	h2WebEngine.load(h2Url.toString());
    	
		URL helpUrl = this.getClass().getResource(HELP);
    	WebEngine helpWebEngine = helpWebView.getEngine();
    	helpWebEngine.load(helpUrl.toString());

    	h2Hyperlink.setOnAction(event -> hostServices.showDocument(H2_URL));
  	}
}
