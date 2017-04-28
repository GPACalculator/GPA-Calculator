package application;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

//Written by: Stephanie Whitworth
public class Model {
	
	//series that holds xy chart data
	private XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
	//list containing all of the credit fields
	private ArrayList<Integer> listOfCredits = new ArrayList<Integer>();
	//list containing all of the grades
	private ArrayList<String> listOfGrades = new ArrayList<String>();
	//current number of rows
	int numberOfRows = 3; 
	//current number of semesters
	int numberOfSemesters = 1;
	//tags whether or not something is saved
	boolean isSaved = false;
	//the total number of credits
	private double totalCreditPoints = 0.0;
	//the total gpa
	private double totalGradePoints = 0.0;
	//contains the information for the pie chart
	private int[] pieData = new int[5];
	//total grade
	private int totalGrades = 0;
	//the current gpa
	private double currGpa = 0.0;
	//current cumulative gpa
	private double cumGpa = 0.0;
		
	
	//Written by: Elizabeth Nondorf
	public void calculateGPA()
	{
		double numberSemesterHours = 0.0;
		double totalClassPoints = 0.0;
		double gradeScaled=0.0;
		int classHours = 0;
		String grade = "";
		
		for(int i = 0; i < numberOfRows; i++)
		{
			//get the row's class hours
			classHours = listOfCredits.get(i);
			
			numberSemesterHours += classHours;
			
			//get the row's grade
			grade = listOfGrades.get(i);
			
			//cases for calculating the grades
			if(grade.equals("A+"))
			{
				gradeScaled = 4.0;
				pieData[0] = pieData[0]+1;
			}
			else if(grade.equals("A"))
			{
				gradeScaled = 4.0;
				pieData[0] = pieData[0]+1;
			}
			else if(grade.equals("A-"))
			{
				gradeScaled = 3.7;
				pieData[0] = pieData[0]+1;
			}
			else if(grade.equals("B+"))
			{
				gradeScaled = 3.3;
				pieData[1] = pieData[1]+1;
			}
			else if(grade.equals("B"))
			{
				gradeScaled = 3.0;
				pieData[1] = pieData[1]+1;
			}
			else if(grade.equals("B-"))
			{
				gradeScaled = 2.7;
				pieData[1] = pieData[1]+1;
			}
			else if(grade.equals("C+"))
			{
				gradeScaled = 2.3;
				pieData[2] = pieData[2]+1;
			}
			else if(grade.equals("C"))
			{
				gradeScaled = 2.0;
				pieData[2] = pieData[2]+1;
			}
			else if(grade.equals("C-"))
			{
				gradeScaled = 1.7;
				pieData[2] = pieData[2]+1;
			}
			else if(grade.equals("D+"))
			{
				gradeScaled = 1.3;
				pieData[3] = pieData[3]+1;
			}
			else if(grade.equals("D"))
			{
				gradeScaled = 1.0;
				pieData[3] = pieData[3]+1;
			}
			else if(grade.equals("D-"))
			{
				gradeScaled = 0.7;
				pieData[3] = pieData[3]+1;
			}
			else if(grade.equals("F"))
			{
				gradeScaled = 0.0;
				pieData[4] = pieData[4]+1;
			}
			
			totalClassPoints += (classHours*gradeScaled);
		}
		
		//Math for cumulativeGPA
		totalGradePoints += totalClassPoints;
		totalCreditPoints += numberSemesterHours;
		
		//set the current cumulative gpa
		setCumGpa(Math.round((totalGradePoints/totalCreditPoints) * 100.0) / 100.0);
		//Current semesters GPA
		setCurrGPA(Math.round(totalClassPoints/numberSemesterHours * 100.0) / 100.0);
	}
	
	//Written by: Stephanie Whitworth
	//Creates the series used in the pie chart. This is good because we can call this over and over
	public void setSeries(double gpaType) {
		//we need to create a series with the gpa output
		series.getData().add(new XYChart.Data<String, Double>("Semester " + numberOfSemesters, gpaType));
		return;
	}
	
	public Series<String,Double> getSeries() {
		return series;
	}
	
	//Written by: Elizabeth Nandorf and Stephanie whitworth
	public ObservableList<Data> createPieData() {
		//set the data as saved to a series
		isSaved = true;
		
		// Pie chart code
		for(int i = 0; i<pieData.length; i++)
		{
			totalGrades = totalGrades + pieData[i];
		}
		
		//calculate the percentage of the grades
		double[] percents = new double[5];
		for(int k = 0; k<percents.length; k++)
		{
			percents[k]=(100.0*pieData[k]/totalGrades);
		}
		
		//formats the numbers in the pie chart
		NumberFormat formatter = new DecimalFormat("#0.00");
		
		//create the observable list
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("A  "+formatter.format(percents[0])+"%", pieData[0]), new PieChart.Data("B  "+formatter.format(percents[1])+"%", pieData[1]),
				new PieChart.Data("C  "+formatter.format(percents[2])+"%", pieData[2]), new PieChart.Data("D  "+formatter.format(percents[3])+"%", pieData[3]), new PieChart.Data("F  "+formatter.format(percents[4])+"%", pieData[4]));
		
		return pieChartData;
	}
	
	//Written by: Stephanie Whitworth
	/*Makes the list of credits*/
	public void setCredits(ArrayList<TextField> listOfCredits) {
		for (int i = 0; i < listOfCredits.size(); i++) {
			//convert each string to an integer
			this.listOfCredits.add(Integer.parseInt(listOfCredits.get(i).getText()));
		}
		return;
	}
	
	public void setGradeList(ArrayList<ComboBox<String>> listOfGrades){
		for(int i = 0; i < listOfGrades.size(); i++) {
			this.listOfGrades.add(listOfGrades.get(i).getValue());
		}
	}
	
	public void incrementSemesters() {
		this.numberOfSemesters++;
		return;
	}
	
	//Written by: Stephanie Whitworth
	public void removeRowData(int index) {
		listOfCredits.remove(listOfCredits.get(index));
		listOfGrades.remove(listOfGrades.get(index));
		return;
	}
	
	//Written bt:Stephanie Whitworth
	public void clearData(){
		listOfCredits.clear();
		listOfGrades.clear();
		return;
	}
	
	//Written by:Stephanie Whitworth
	/*Sets the cumulative gpa*/
	public void setCumGpa(double cumGpa) {
		this.cumGpa = cumGpa;
		return;
	}
	
	//Written by:Stephanie Whitworth
	/*Sets the current gpa*/
	public void setCurrGPA(double currGpa) {
		this.currGpa = currGpa;
		return;
	}
	
	//Written by:Stephanie Whitworth
	/*Sets the save flag*/
	public void setSaved(boolean flag) {
		this.isSaved = flag;
		return;
	}
	
	//Written by: Stephanie Whitworth
	/*Sets the save value*/
	public boolean isSaved() {
		if (this.isSaved == true) {
			return true;
		}
		//otherwise, the function will always return false
		return false;
	}
	
	/*Getter that returns the current gpa for the semester*/
	public double getCurrGpa() {
		return currGpa;
	}
	
	/*Getter that returns the cumulative gpa*/
	public double getCumGpa() {
		return cumGpa;
	}
}