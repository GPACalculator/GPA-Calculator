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
	private TextField creditsInput1;
	@FXML
	private Text text00;
	@FXML
	private ComboBox<String> gradeInput1;
	@FXML
	private Button removeButton1;
	@FXML
	private Text text1;
	@FXML
	private TextField creditsInput2;
	@FXML
	private Text text11;
	@FXML
	private ComboBox<String> gradeInput2;
	@FXML
	private Button removeButton2;
	@FXML
	private Text text2;
	@FXML
	private TextField creditsInput3;
	@FXML
	private Text text22;
	@FXML
	private ComboBox<String> gradeInput3;
	@FXML
	private Button removeButton3;
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
	
	private ArrayList<TextField> listOfCredits = new ArrayList<TextField>();
	private ArrayList<ComboBox<String>> listOfGrades = new ArrayList<ComboBox<String>>();
	int numberOfRows = 3; 
	int numberOfSemesters = 1; 
	boolean isSaved = false;
	
	public final Model model = new Model();
	
	//Written by: Elizabeth Nondorf
	@FXML
	private void calculateGPA(ActionEvent e)
	{
		//If any input fields are empty
		for(int i = 0; i < inputGrid.getChildren().size(); i++)
		{
			if(inputGrid.getChildren().get(i) == null)
			{
				try {
					
					throw new Exception("Field(s) empty. ABORTING.");
				} catch (Exception e1) {
					e1.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("You have left a field empty");
					alert.setContentText("Please make sure you have completely filled out the form before calculating your GPA.");
					alert.showAndWait();
				}
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
	@SuppressWarnings({ "static-access", "unchecked" })
	@FXML
	private void addRow(ActionEvent e)
	{
		saveButton.setDisable(true);
		
		newButton.setDisable(true);
		numberOfRows++;
		
		final Text creditsName = new Text("Credits:");
		creditsName.fontProperty().setValue(new Font(15));
		creditsName.setId("text" + numberOfRows);
		
		final TextField creditsInput4 = new TextField();
		creditsInput4.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
		creditsInput4.setPromptText("Ex: 3");
		creditsInput4.setId("creditsInput" + numberOfRows);
		listOfCredits.add(creditsInput4);
		
		final Text gradeName = new Text("Grade:");
		gradeName.fontProperty().setValue(new Font(15));
		gradeName.setId("text" + numberOfRows + "" + numberOfRows);
		
		final ComboBox<String> gradeInput4 = new ComboBox<String>();
		gradeInput4.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		creditsInput4.setId("gradeInput" + numberOfRows);
		listOfGrades.add(gradeInput4);
		
		final Button removeButton4 = new Button("-");
		removeButton4.fontProperty().setValue(new Font(15));
		removeButton4.setId("removeButton" + numberOfRows);
		
		removeButton4.addEventHandler(ActionEvent.ACTION, (event)-> {
			int row = inputGrid.getRowIndex((Node) event.getSource());
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
			numberOfRows--;
        });
		
		
		inputGrid.add(creditsName, 0, numberOfRows);
		inputGrid.add(creditsInput4, 1, numberOfRows);
		inputGrid.add(gradeName, 2, numberOfRows);
		inputGrid.add(gradeInput4, 3, numberOfRows);
		inputGrid.add(removeButton4, 4, numberOfRows);
		
	}

	//Written by: Emily Black and Elizabeth Nondorf and Stephanie Whitworth
	@FXML
	private void saveSemester(ActionEvent e)
	{
		//Check for input
		if(!(gpaOutput.getText().equals(null)))
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
		if(!(gpaOutput.getText().equals(null)))
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
			if(model.isSaved() == true)
			{
				// clear grid
				for(int i = numberOfRows; i > 3; i--)
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
				for(int j = numberOfRows-1; j > 3; j--)
				{
					listOfCredits.remove(listOfCredits.get(j));
					listOfGrades.remove(listOfGrades.get(j));
				}
				
				numberOfRows = 3;
				
				//Clear first 3 rows
				for(int k = 0; k < numberOfRows; k++)
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
		else
		{
			try {
				throw new Exception("No GPA to save. ABORTING.");
			} catch (Exception e1) {
				e1.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("You have not calculated your GPA yet");
				alert.setContentText("Please hit \"Calculate\" to calculate your GPA.");
				alert.showAndWait();
			}
		}
		
}

	//Written by: Emily Black
	@FXML
	private void removeRow(ActionEvent event)
	{
		@SuppressWarnings("static-access")
		int row = inputGrid.getRowIndex((Node) event.getSource());
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
		numberOfRows--;
	}
	
	//Written by: Emily Black and Elizabeth Nondorf
	@SuppressWarnings({ "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saveButton.setDisable(true);
		newButton.setDisable(true);
		
		listOfCredits.add(creditsInput1);
		gradeInput1.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput1);
		
		listOfCredits.add(creditsInput2);
		gradeInput2.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput2);
		
		listOfCredits.add(creditsInput3);
		gradeInput3.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F");
		listOfGrades.add(gradeInput3);
		
		creditsInput1.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
		creditsInput2.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
		creditsInput3.addEventFilter(KeyEvent.KEY_TYPED , numericalValue(2));
	}

	//Written by: Emily Black
	@SuppressWarnings("rawtypes")
	private EventHandler numericalValue(int maxLength) {
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