package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashSet;

/** 
 * Controller for GPA-Calculator
 * Last modified: 4/26/17
 * @author GPA-Calculator Team
 * This program calculates your GPA based on credits/grade. Also has graph/chart feature. 
 */
public class Controller implements Initializable
{
	@FXML
	private GridPane inputGrid;
	@FXML
	private Text text0;
	@FXML
	private TextField creditsInput0;
	@FXML
	private Text text00;
	@FXML
	private ComboBox<String> gradeInput0;
	@FXML
	private Button removeButton0;
	@FXML
	private Text text1;
	@FXML
	private TextField creditsInput1;
	@FXML
	private Text text11;
	@FXML
	private ComboBox<String> gradeInput1;
	@FXML
	private Button removeButton1;
	@FXML
	private Text text2;
	@FXML
	private TextField creditsInput2;
	@FXML
	private Text text22;
	@FXML
	private ComboBox<String> gradeInput2;
	@FXML
	private Button removeButton2;
	@FXML
	private Text gpaOutput;
	@FXML
	private Text cumGPAOutput;
	@FXML
	private Button calcButton;
	@FXML
	private Button addButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button newButton;
	@FXML
	private AreaChart<String, Double> graph;
	@FXML
	private PieChart pieChart;
	
	private final Model model = new Model();
	private Text creditsName;
	private TextField creditsInput;
	private Text gradeName;
	private ComboBox<String> gradeInput;
	private Button removeButton;
	private ArrayList<TextField> listOfCredits = new ArrayList<TextField>();
	private ArrayList<ComboBox<String>> listOfGrades = new ArrayList<ComboBox<String>>();
	
	//Written by: Elizabeth Nondorf and Emily Black
	@FXML
	private void calculateGPA(ActionEvent e)
	{	
		
		//If we have no fields
		if(model.getNumOfRows() == 0)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR: NO FIELD(S) FOUND.");
			alert.setContentText("Please make sure you have at least one row filled out before calculating your GPA.");
			alert.showAndWait();
			return;
		}
		
		//If any input fields are empty
		for(int i = 0; i < model.getNumOfRows(); i++)
		{
			if(listOfCredits.get(i).getText().equals("") || listOfGrades.get(i).getValue() == null)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("ERROR: EMPTY FIELD(S).");
				alert.setContentText("Please make sure you have completely filled out the form before calculating your GPA.");
				alert.showAndWait();
				return;
			}
		}
		
		saveButton.setDisable(false);
		newButton.setDisable(false);
		/*Gives the calculator all of the credits*/
		model.setCredits(listOfCredits);
		/*Gvies the calculator all of the values from the grades*/
		model.setGradeList(listOfGrades);
		
		/*Calculate the gpa after error checking*/
		model.calculateGPA();
		
		/*Makes the data visible*/
		gpaOutput.setText(model.getCurrGpa() + "");
		cumGPAOutput.setText(model.getCumGpa() + "");
	}

	//Written by: Emily Black
	@SuppressWarnings({ "unchecked" })
	@FXML
	private void addRow(ActionEvent e)
	{
		saveButton.setDisable(true);
		newButton.setDisable(true);
		
		creditsName = new Text("Credits:");
		creditsName.fontProperty().setValue(new Font(15));
		creditsName.setId("text" + model.getNumOfRows());
		
		creditsInput = new TextField();
		creditsInput.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
		creditsInput.setPromptText("Ex: 3");
		listOfCredits.add(creditsInput);
		creditsInput.setId("creditsInput" + model.getNumOfRows());
		
		gradeName = new Text("Grade:");
		gradeName.fontProperty().setValue(new Font(15));
		gradeName.setId("text" + model.getNumOfRows() + "" + model.getNumOfRows());
		
		gradeInput = new ComboBox<String>();
		gradeInput.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput);
		gradeInput.setId("gradeInput" + model.getNumOfRows());
		
		removeButton = new Button("-");
		removeButton.fontProperty().setValue(new Font(15));
		removeButton.setId("removeButton" + model.getNumOfRows());
		
		removeButton.setOnAction(event1 -> {
		    removeRow(event1);
		});
		
		inputGrid.add(creditsName, 0, model.getNumOfRows());
		inputGrid.add(creditsInput, 1, model.getNumOfRows());
		inputGrid.add(gradeName, 2, model.getNumOfRows());
		inputGrid.add(gradeInput, 3, model.getNumOfRows());
		inputGrid.add(removeButton, 4, model.getNumOfRows());
		
		model.setNumOfRows(model.getNumOfRows()+1);
	}

	//Written by: Emily Black and Elizabeth Nondorf and Stephanie Whitworth
	@FXML
	private void saveSemester(ActionEvent e)
	{
		//Check for input
		if(gpaOutput.getText() == null)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR: EMPTY GPA");
			alert.setContentText("Please make sure you calculate your GPA before saving your semester.");
			alert.showAndWait();
			return;
		}
		else
		{
			//set the series
			model.setSeries(Double.parseDouble(gpaOutput.getText()));
			//increment the number of semesters
			model.incrementSemesters();
			//set the data
			pieChart.setData(model.createPieData());
			//make the chart visible
			pieChart.setLegendVisible(false);	
		}
	}
	
	//Written by: Elizabeth Nondorf and Stephanie Whitworth
	@SuppressWarnings("unchecked")
	@FXML
	private void newSemester(ActionEvent e)
	{
		// save semester & clear elements if we have gpa to save
		if(gpaOutput.getText() == null)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR: EMPTY GPA");
			alert.setContentText("Please make sure you calculate your GPA before saving your semester.");
			alert.showAndWait();
			return;
		}
		else
		{
			//If we havent saved
			if(model.isSaved() == false)
			{
				//create the series data
				model.setSeries(Double.parseDouble(gpaOutput.getText()));
				
				//increment the number of semesters
				model.incrementSemesters();
				
				graph.getData().clear(); //clears data so we dont add duplicates
				graph.getData().addAll(model.getSeries());
				graph.setLegendVisible(false);
				//we let model know that it is saved
				model.setSaved(true);
				
				pieChart.setData(model.createPieData());
				pieChart.setLegendVisible(false);
			}
			//If we have saved
			if(model.isSaved() == true)
			{
				// clear grid
				for(int i = model.getNumOfRows(); i > 2; i--)
				{
					int row = i;
					Set<Node> deleteNodes = new HashSet<>(5);
					 
					for (Node child : inputGrid.getChildren()) {
				        	// get index from child
					        Integer rowIndex = GridPane.getRowIndex(child);
				
					        // handle null values for index=0
					        int r = rowIndex == null ? 0 : rowIndex;
				
					        if (r > row) {
					            // decrement rows for rows after the deleted row
					            GridPane.setRowIndex(child, r-1);
					        } else if (r == row) {
					            // collect matching rows for deletion
					            deleteNodes.add(child);
					            child.setManaged(false);
					        }
					}
					
					// remove nodes from row
					inputGrid.getChildren().removeAll(deleteNodes);
				}
				
				//Remove extra rows from list
				for(int j = model.getNumOfRows()-1; j > 3; j--)
				{
					listOfCredits.remove(listOfCredits.get(j));
					listOfGrades.remove(listOfGrades.get(j));
				}
				
				model.setNumOfRows(3);
				
				//Clear first 3 rows
				for(int k = 0; k < model.getNumOfRows(); k++)
				{
					listOfCredits.get(k).clear();
					listOfGrades.get(k).valueProperty().set(null);
				}
				
				//Clear the data in the model
				model.clearData();
			}
			
			gpaOutput.setText("");
			model.setSaved(false);
			
			saveButton.setDisable(true);
			newButton.setDisable(true);
		}	
	}

	//Written by: Emily Black
	@FXML
	private void removeRow(ActionEvent event)
	{
		@SuppressWarnings("static-access")
		int row = inputGrid.getRowIndex((Node) event.getSource());
		ArrayList<Node> deleteNodes = new ArrayList<Node>(5);
		 
		for (Node child : inputGrid.getChildren()) {
	        	// get index from child
		        Integer rowIndex = GridPane.getRowIndex(child);
	
		        // handle null values for index=0
		        int r = rowIndex == null ? 0 : rowIndex;
	
		        if (r > row) {
		            // decrement rows for rows after the deleted row
		            GridPane.setRowIndex(child, r-1);
		        } else if (r == row) {
		            // collect matching rows for deletion
		            deleteNodes.add(child);
		            child.setManaged(false);
		        }
		}
		
		inputGrid.getChildren().removeAll(deleteNodes);
		model.setNumOfRows(model.getNumOfRows()-1);
		
		listOfGrades.remove(row);
		model.setGradeList(listOfGrades);
		listOfCredits.remove(row);
		for(int i = 0; i < listOfCredits.size(); i++)
		{
			System.out.println(listOfCredits.get(i).getText());
		}
		model.setCredits(listOfCredits);
    }
	
	//Written by: Emily Black and Elizabeth Nondorf
	@SuppressWarnings({ "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saveButton.setDisable(true);
		newButton.setDisable(true);
		
		listOfCredits.add(creditsInput0);
		gradeInput0.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput0);
		
		listOfCredits.add(creditsInput1);
		gradeInput1.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput1);
		
		listOfCredits.add(creditsInput2);
		gradeInput2.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput2);
		
		creditsInput0.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
		creditsInput1.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
		creditsInput2.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
	}

	//Written by: Emily Black
	@SuppressWarnings("rawtypes")
	private EventHandler numericalValue(int maxLength) 
	{
		return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            TextField txt_TextField = (TextField) e.getSource();                
	            if (txt_TextField.getText().length() >= maxLength) {                    
	                e.consume();
	            }
	            if(e.getCharacter().matches("[0-9]")){ 
	                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
	                    e.consume();
	                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
	                    e.consume(); 
	                }
	            }else{
	                e.consume();
	            }
	        }
	    };
	}

}