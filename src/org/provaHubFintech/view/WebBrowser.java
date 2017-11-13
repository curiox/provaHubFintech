package org.provaHubFintech.view;

import javafx.application.*;
import javafx.embed.swing.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.provaHubFintech.server.DatabaseServerApplication;
import org.provaHubFintech.singleton.ConfigSingleton;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class WebBrowser extends JFrame {
	
	private static final long serialVersionUID = -7557599105005691421L;
	JFXPanel javafxpanel;
	WebView webComponent;
	JPanel mainPanel;
	
	public WebBrowser() {
		javafxpanel = new JFXPanel();
		
		initSwingComponents();
		
		loadJavaFXScene();
	}
	
	private void initSwingComponents() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(javafxpanel, BorderLayout.CENTER);
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);
	}
	
	public void loadJavaFXScene() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				BorderPane borderPane = new BorderPane();
				webComponent = new WebView();
				
				webComponent.getEngine().load(ConfigSingleton.getInstance().indexPath);
				borderPane.setCenter(webComponent);
				Scene scene = new Scene(borderPane, 450, 450);
				javafxpanel.setScene(scene);
			}
		});
	}

	public static void main(String[] args) {
		try {
			Component comp = new Component();
			comp.getServers().add(Protocol.HTTP, 8080);
			DatabaseServerApplication app = new DatabaseServerApplication();
			app.createInBoundRoot();
			comp.getDefaultHost().attach("/database", app);
			comp.start();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					WebBrowser browser = new WebBrowser();
					browser.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}