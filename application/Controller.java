package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

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
	private Text text1;
	@FXML
	private TextField creditsInput1;
	@FXML
	private Text text11;
	@FXML
	private TextField gradeInput1;
	@FXML
	private Button removeButton1;
	@FXML
	private Text text2;
	@FXML
	private TextField creditsInput2;
	@FXML
	private Text text22;
	@FXML
	private TextField gradeInput2;
	@FXML
	private Button removeButton2;
	@FXML
	private Text text3;
	@FXML
	private TextField creditsInput3;
	@FXML
	private Text text33;
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
	
	private ArrayList<TextField> listOfTextFields = new ArrayList<TextField>();
	int numberOfRows = 3;

	//Written by: Elizabeth Nondorf
	@FXML
	private void calculateGPA(ActionEvent e)
	{
		int numberSemesterHours = 0;
		
		int listTextFieldIndex = 0;
		
		double totalClassPoints = 0.00;
		
		double gpa = 0.00;
		
		for(int i = 0; i < numberOfRows; i++)
		{
			//get the row's class hours
			int classHours = Integer.parseInt(listOfTextFields.get(listTextFieldIndex).getText());
			listTextFieldIndex++;
			
			numberSemesterHours = numberSemesterHours + classHours;
			
			//get the row's grade
			String grade = listOfTextFields.get(listTextFieldIndex).getText();
			listTextFieldIndex++;
			double gradeScaled=0.00;
			
			if(grade.toUpperCase().equals("A+"))
			{
				gradeScaled = 4.0;
			}
			else if(grade.toUpperCase().equals("A"))
			{
				gradeScaled = 4.0;
			}
			else if(grade.toUpperCase().equals("A-"))
			{
				gradeScaled = 3.7;
			}
			else if(grade.toUpperCase().equals("B+"))
			{
				gradeScaled = 3.3;
			}
			else if(grade.toUpperCase().equals("B"))
			{
				gradeScaled = 3.0;
			}
			else if(grade.toUpperCase().equals("B-"))
			{
				gradeScaled = 2.7;
			}
			else if(grade.toUpperCase().equals("C+"))
			{
				gradeScaled = 2.3;
			}
			else if(grade.toUpperCase().equals("C"))
			{
				gradeScaled = 2.0;
			}
			else if(grade.toUpperCase().equals("C-"))
			{
				gradeScaled = 1.7;
			}
			else if(grade.toUpperCase().equals("D+"))
			{
				gradeScaled = 1.3;
			}
			else if(grade.toUpperCase().equals("D"))
			{
				gradeScaled = 1.0;
			}
			else if(grade.toUpperCase().equals("D-"))
			{
				gradeScaled = 1.0;
			}
			else if(grade.toUpperCase().equals("F"))
			{
				gradeScaled = 0.0;
			}
			else
			{
				try {
					throw new Exception("Unknown Grade. ABORTING.");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			totalClassPoints = totalClassPoints + (classHours*gradeScaled);
		}
		
		gpa = totalClassPoints/numberSemesterHours;
		gpa = Math.round(gpa*100.00);
		gpa = gpa/100.00;
		
		gpaOutput.setText(Double.toString(gpa));
	}

	//Written by: Emily Black
	@SuppressWarnings("static-access")
	@FXML
	private void addRow(ActionEvent e)
	{
		numberOfRows++;
		
		final Text creditsName = new Text("Credits:");
		creditsName.fontProperty().setValue(new Font(15));
		creditsName.setId("text" + numberOfRows);
		
		final TextField creditsInput4 = new TextField();
		creditsInput4.setPromptText("Ex: 3");
		creditsInput4.setId("creditsInput" + numberOfRows);
		listOfTextFields.add(creditsInput4);
		creditsInput4.setPadding(new Insets(5, 5, 5, 5));
		
		final Text gradeName = new Text("Grade:");
		gradeName.fontProperty().setValue(new Font(15));
		gradeName.setId("text" + numberOfRows * 11); //Assumes they won't go over 9 rows
		
		final TextField gradeInput4 = new TextField();
		gradeInput4.setPromptText("Ex: C-");
		creditsInput4.setId("gradeInput" + numberOfRows);
		listOfTextFields.add(gradeInput4);
		gradeInput4.setPadding(new Insets(5, 5, 5, 5));
		
		final Button removeButton4 = new Button("-");
		removeButton4.fontProperty().setValue(new Font(15));
		removeButton4.setPadding(new Insets(5, 5, 5, 5));
		removeButton4.setId("removeButton" + numberOfRows);
		
		removeButton4.addEventHandler(ActionEvent.ACTION, (event)-> {
            inputGrid.getChildren().remove(creditsName);
            inputGrid.getChildren().remove(creditsInput4);
            inputGrid.getChildren().remove(gradeName);
            inputGrid.getChildren().remove(gradeInput4);
            inputGrid.getChildren().remove(removeButton4);
            numberOfRows--;
        });
		
		inputGrid.add(creditsName, 0, numberOfRows);
		inputGrid.setHalignment(creditsName, HPos.CENTER);
		inputGrid.setValignment(creditsName, VPos.CENTER);
		inputGrid.setMargin(creditsName, new Insets(5, 5, 5, 5));
		inputGrid.setConstraints(creditsName, 0, numberOfRows);
		
		inputGrid.add(creditsInput4, 1, numberOfRows);
		inputGrid.setHalignment(creditsInput4, HPos.CENTER);
		inputGrid.setValignment(creditsInput4, VPos.CENTER);
		inputGrid.setMargin(creditsInput4, new Insets(5, 5, 5, 5));
		inputGrid.setConstraints(creditsInput4, 1, numberOfRows);
		
		inputGrid.add(gradeName, 2, numberOfRows);
		inputGrid.setHalignment(gradeName, HPos.CENTER);
		inputGrid.setValignment(gradeName, VPos.CENTER);
		inputGrid.setMargin(gradeName, new Insets(5, 5, 5, 5));
		inputGrid.setConstraints(gradeName, 2, numberOfRows);
		
		inputGrid.add(gradeInput4, 3, numberOfRows);
		inputGrid.setHalignment(gradeInput4, HPos.CENTER);
		inputGrid.setValignment(gradeInput4, VPos.CENTER);
		inputGrid.setMargin(gradeInput4, new Insets(5, 5, 5, 5));
		inputGrid.setConstraints(gradeInput4, 3, numberOfRows);
		
		inputGrid.add(removeButton4, 4, numberOfRows);
		inputGrid.setHalignment(removeButton4, HPos.CENTER);
		inputGrid.setValignment(removeButton4, VPos.CENTER);
		inputGrid.setMargin(removeButton4, new Insets(5, 5, 5, 5));
		inputGrid.setConstraints(removeButton4, 4, numberOfRows);
		
	}

	@FXML
	private void saveSemester(ActionEvent e)
	{
		
	}

	@FXML
	private void newSemester(ActionEvent e)
	{
		
	}

	//Written by: Emily Black (STILL WORKING ON IT)
	@FXML
	private void removeRow(ActionEvent event)
	{
		@SuppressWarnings("static-access")
		int row = inputGrid.getRowIndex((Node) event.getSource());
		inputGrid.getChildren().remove("text" + row);
		inputGrid.getChildren().remove("creditsInput" + row);
		inputGrid.getChildren().remove("text" + row * 11);
		inputGrid.getChildren().remove("gradeInput" + numberOfRows);
		inputGrid.getChildren().remove("removeButton" + numberOfRows);
		
		//setVisible(false) and setManaged(false) to each element
		numberOfRows--;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Constrains first row	
		inputGrid.setConstraints(text1, 0, 0); // column=1 row=0
		inputGrid.setConstraints(creditsInput1, 1, 0);
		inputGrid.setConstraints(text11, 2, 0);
		inputGrid.setConstraints(gradeInput1, 3, 0);
		inputGrid.setConstraints(removeButton1, 4, 0);
		
		// Constrains second row
		inputGrid.setConstraints(text2, 0, 1);
		inputGrid.setConstraints(creditsInput2, 1, 1);
		inputGrid.setConstraints(text22, 2, 1);
		inputGrid.setConstraints(gradeInput2, 3, 1);
		inputGrid.setConstraints(removeButton2, 4, 1);
		
		// Constrains third row
		inputGrid.setConstraints(text3, 0, 2);
		inputGrid.setConstraints(creditsInput3, 1, 2);
		inputGrid.setConstraints(text33, 2, 2);
		inputGrid.setConstraints(gradeInput3, 3, 2);
		inputGrid.setConstraints(removeButton3, 4, 2);
		
		listOfTextFields.add(creditsInput1);
		listOfTextFields.add(gradeInput1);
		listOfTextFields.add(creditsInput2);
		listOfTextFields.add(gradeInput2);
		listOfTextFields.add(creditsInput3);
		listOfTextFields.add(gradeInput3);
	}

}
