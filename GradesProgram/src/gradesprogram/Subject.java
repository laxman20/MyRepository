
package gradesprogram;

import java.util.ArrayList;


public class Subject {
	
	ArrayList<Double> grades = new ArrayList<Double>();
	double sumOfGrades, averageGrade;
	
	public Subject() {
		
	}
	
	public void addGrade(double mark) {
		grades.add(mark);
		
	}
	
	public void removeGrade(double mark) {
		grades.remove(mark);
	}
	
	public double calculateAverage() {
		for (int x = 0;x < grades.size(); x++) {
			sumOfGrades = sumOfGrades + grades.get(x);
		}
		averageGrade = sumOfGrades/grades.size();
		return averageGrade;
	}
	
	public String listGrades() {
		return grades.toString();
	}
	
	public double getAverage() {
		return averageGrade;
	}
	
}
