package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {
	
	HashMap<String, Integer> semesters = new HashMap<String, Integer>();
	Integer i = 0;
	
	//TODO: This will be up to you guys. The model will contain the data. Since it is going to be stored in a graph, 
	//it might be preferable to add it as a pairing rather than a simple arrayList.
	
	//TODO: This should create the new data item or set of data that will contain the value. Once I have a clear view
	//of what should go into the view, I can update the controller accordingly.
	public void createSemester(){};
	//This method will retrieve the information from view using the controller and store in model
	public void setSemesterGPA(String gpaString)
	{
		i = i + 1;
		//parse the gpa text to an integer
		Integer gpa = Integer.parseInt(gpaString);
		//the key is the semester name
		String key = Integer.toString(i);
		//place it in the hashmap
		semesters.put(key, gpa);
	};
	
}