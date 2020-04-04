package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import models.VennSet;
import models.VennShape;
import views.MainApp;

public class TestModeController extends ShapeSceneController implements Initializable {
	
	@FXML
	private Button importTxtBttn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Test Mode");
		alert.setHeaderText("Test Mode has Been Entered");
		alert.setContentText("Welcome to VennCreate's test mode. In this mode you are able to input a .txt file"
				+ " with the format specified in VennCreate's user manual, and practice placing the text in the correct location."
				+ "The timer will constantly be running, so go as fast as possible!");

		ButtonType ready = new ButtonType("Ready!");
		ButtonType goBack = new ButtonType("Go Back");

		alert.getButtonTypes().setAll(ready, goBack);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ready) {
			alert.close();
		} else {
			try {
				goShapeScene();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void goShapeScene() throws IOException {
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/shapeScene.fxml"));

			MainApp.primaryStage.setScene(new Scene(loader.load()));
	}
	
	@FXML
	private void importTxt() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Text Files", "*.txt"),
			     new FileChooser.ExtensionFilter("CSV Files", "*.csv")
			);
		
		File selectedFile = fileChooser.showOpenDialog(MainApp.primaryStage);
		
		super.mainScene.getChildren().remove(importTxtBttn);
		
	}
	

}