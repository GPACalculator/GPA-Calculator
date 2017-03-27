package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** 
 * Driver for GPA-Calculator
 * Last modified: 3/26/17
 * @author GPA-Calculator Team
 * This program calculates your GPA based on credits/grade. Also has graph/chart feature. 
 */
public class Driver extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/View.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("GPA Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}