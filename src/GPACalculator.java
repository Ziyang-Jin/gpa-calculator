import java.util.ArrayList;

public class GPACalculator {
	private static final double DEFAULT_SCALE = 100;
    private ArrayList<Course> courses;
    private double scale;

    public GPACalculator() {
        courses = new ArrayList<Course>();
        scale = DEFAULT_SCALE;
    }

    public GPACalculator(ArrayList<Course> courses, double scale) {
        this.courses = courses;
	    this.scale = scale;
    }
    
    public void setScale(double scale) {
    	this.scale = scale;
    }
    
    public void registerCourse(Course c) {
    	courses.add(c);
    }
    
    public void registerCourses(ArrayList<Course> courses) {
    	this.courses = courses;
    }
    
    public void outputCoursesInfo() {
    	String header = "Course, Grade, Credits";
    	System.out.println(header);
    	for (Course c : courses) {
    		c.printCourseInfo();
    	}
    }

    public double calculateGPA() {
        double totalGrades = getTotalGrades();
        int totalCredits = getTotalCredits();
        return totalCredits != 0 ? totalGrades / totalCredits * scale : 0;
    }

    private int getTotalCredits() {
        int total = 0;
        for (int i = 0; i < courses.size(); i++) {
	    total += courses.get(i).getCredits();
	}
        return total;
    }

    private double getTotalGrades() {
        double total = 0.0;
	    for (int i = 0; i < courses.size(); i++) {
	        total += getSingleCourseGrade(courses.get(i));
	    }
	    return total;
    }
    
    private double getSingleCourseGrade(Course c) {
    	int grade = c.getGrade();
    	double result = 0.0;
        if (scale == 4.0) {
        	if (grade >= 90) {
        		result = 1.0;
        	} else {
                result = grade / 90.0;
        	}
        } else {
        	result = grade / 100.0;
        }
        result *= c.getCredits();
        return result;
    }
    
}
