import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub. Nothing goes here.	
	}

}
