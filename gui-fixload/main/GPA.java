package main;

public class GPA {

	//instance variable
	public double GPA;
	
	//constructor
	public GPA(double gpa) {
		this.GPA = gpa;
	}
	
	//getter and setter
	public double getGPA() {
		return GPA;
	}
	
	public void setGPA(double newGPA) {
		GPA = newGPA;
	}
	
	//methods
	
	//gpaCalculator: calculates current GPA at end of each level
	//parameters: the current GPA, the total health of all combined team members,
	//the current combined health of all team members, the percentage value of the stage
	public double gpaCalculator(double currentGPA, double totalHealth, double currentHealth, double stageValue) {
		double newGPA = 4;
		double hlthPrcnt;
		//making sure to not divide by zero
		if (totalHealth != 0) {
			hlthPrcnt = currentHealth/totalHealth;
		} else {
			hlthPrcnt = 0;
		}
		newGPA = (newGPA * hlthPrcnt * stageValue) + currentGPA;
		//make sure that GPA isn't over 4.3 or under 0.0
		if (newGPA > 4.3) {
			newGPA = 4.3;
		} else if (newGPA < 0.0) {
			newGPA = 0.0;
		}
		return newGPA;
	}
}
