
package gradesprogram;


public class GradesProgram {

	
	public static void main(String[] args) {
		Subject math = new Subject();
		math.addGrade(92);
		math.addGrade(96);
		math.addGrade(82.3);
		math.addGrade(100);
		math.removeGrade(82.3);
		math.calculateAverage();
		System.out.println(math.getAverage());
		System.out.println(math.listGrades());
	}
}
