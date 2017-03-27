package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/** 
 * Controller for GPA-Calculator
 * Last modified: 3/26/17
 * @author GPA-Calculator Team
 * This program calculates your GPA based on credits/grade. Also has graph/chart feature. 
 */
public class Controller implements Initializable
{
	@FXML
	private GridPane inputGrid;
	@FXML
	private TextField creditsInput1;
	@FXML
	private TextField gradeInput1;
	@FXML
	private Button removeButton1;
	@FXML
	private TextField creditsInput2;
	@FXML
	private TextField gradeInput2;
	@FXML
	private Button removeButton2;
	@FXML
	private TextField creditsInput3;
	@FXML
	private TextField gradeInput3;
	@FXML
	private Button removeButton3;
	@FXML
	private Text gpaOutput;
	@FXML
	private Button calcButton;
	@FXML
	private Button addButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button newButton;
	@FXML
	private CategoryAxis xAxis;
	@FXML
    private NumberAxis yAxis;
	@FXML
	private LineChart<CategoryAxis, Number> graph;
	private XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	
	int numberOfRows = 3;

	@FXML
	private void calculateGPA(ActionEvent e)
	{
	}

	//Written by: Emily Black
	@SuppressWarnings("static-access")
	@FXML
	private void addRow(ActionEvent e)
	{
		numberOfRows++;
		
		Text creditsName = new Text("Credits:");
		creditsName.fontProperty().setValue(new Font(15));
		
		TextField creditsInput4 = new TextField();
		creditsInput4.setPromptText("Ex: 3");
		creditsInput4.setId("creditsInput" + numberOfRows);
		creditsInput4.setPadding(new Insets(5, 5, 5, 5));
		
		Text gradeName = new Text("Grade:");
		gradeName.fontProperty().setValue(new Font(15));
		
		TextField gradeInput4 = new TextField();
		gradeInput4.setPromptText("Ex: C-");
		creditsInput4.setId("gradeInput" + numberOfRows);
		gradeInput4.setPadding(new Insets(5, 5, 5, 5));
		
		Button removeButton4 = new Button("-");
		removeButton4.fontProperty().setValue(new Font(15));
		removeButton4.setPadding(new Insets(5, 5, 5, 5));
		removeButton4.setId("removeButton" + numberOfRows);
		
		//TODO: removeButton4.setOnAction(); code that goes in removeRow method, goes here
		
		inputGrid.add(creditsName, 0, numberOfRows);
		inputGrid.setHalignment(creditsName, HPos.CENTER);
		inputGrid.setValignment(creditsName, VPos.CENTER);
		inputGrid.setMargin(creditsName, new Insets(5, 5, 5, 5));
		
		inputGrid.add(creditsInput4, 1, numberOfRows);
		inputGrid.setHalignment(creditsInput4, HPos.CENTER);
		inputGrid.setValignment(creditsInput4, VPos.CENTER);
		inputGrid.setMargin(creditsInput4, new Insets(5, 5, 5, 5));
		
		inputGrid.add(gradeName, 2, numberOfRows);
		inputGrid.setHalignment(gradeName, HPos.CENTER);
		inputGrid.setValignment(gradeName, VPos.CENTER);
		inputGrid.setMargin(gradeName, new Insets(5, 5, 5, 5));
		
		inputGrid.add(gradeInput4, 3, numberOfRows);
		inputGrid.setHalignment(gradeInput4, HPos.CENTER);
		inputGrid.setValignment(gradeInput4, VPos.CENTER);
		inputGrid.setMargin(gradeInput4, new Insets(5, 5, 5, 5));
		
		inputGrid.add(removeButton4, 4, numberOfRows);
		inputGrid.setHalignment(removeButton4, HPos.CENTER);
		inputGrid.setValignment(removeButton4, VPos.CENTER);
		inputGrid.setMargin(removeButton4, new Insets(5, 5, 5, 5));
		
	}

	@FXML
	private void saveSemester(ActionEvent e)
	{
		
	}

	@FXML
	private void newSemester(ActionEvent e)
	{
		
	}

	@FXML
	private void removeRow(ActionEvent event)
	{
		//inputGrid.getChildren().remove(e.getSource());
		numberOfRows--;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub. Nothing goes here.	
	}

}
