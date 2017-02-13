import java.util.ArrayList;
import java.util.HashMap;

public class GPACalculator {
	public static final double DEFAULT_SCALE = 100;
	public static final double SCALE433 = 4.33;
	public static final double SCALE40 = 4.0;
	public static final double SCALE40L = 4.1;
	private static ArrayList<Double> supportedScales;
    private ArrayList<Course> courses;
    private double scale;
    private HashMap<Integer, Double> gpaMap433;

    public GPACalculator() {
        courses = new ArrayList<Course>();
        scale = DEFAULT_SCALE;
        initGpaMap();
        initSupportedScales();
    }

    public GPACalculator(ArrayList<Course> courses, double scale) {
        this.courses = courses;
	    this.scale = scale;
	    initGpaMap();
	    initSupportedScales();
    }
    
    private void initSupportedScales() {
    	supportedScales = new ArrayList<Double>();
    	supportedScales.add(DEFAULT_SCALE);
    	supportedScales.add(SCALE433);
    	supportedScales.add(SCALE40);
    	supportedScales.add(SCALE40L);
    }
    
    private void initGpaMap() {
    	gpaMap433 = new HashMap<Integer, Double>();
    	gpaMap433.put(90, 4.33);
    	gpaMap433.put(89, 4.30);
    	gpaMap433.put(88, 4.20);
    	gpaMap433.put(87, 4.10);
    	for (int i = 0; i <= 5; i++) {
    		gpaMap433.put(86 - i, 4.00 - i * 0.05);
    	}
    	for (int j = 0; j <= 7; j++) {
    		gpaMap433.put(80 - j, 3.70 - j * 0.10);
    	}
    	gpaMap433.put(72, 2.95);
    	gpaMap433.put(71, 2.90);
    	gpaMap433.put(70, 2.80);
    	gpaMap433.put(69, 2.70);
    	gpaMap433.put(68, 2.65);
    	gpaMap433.put(67, 2.60);
    	gpaMap433.put(66, 2.55);
    	for (int i = 0; i <= 15; i++) {
    		gpaMap433.put(65 - i, 2.50 - i * 0.10);
    	}
    }
    
    public void setScale(double scale) {
    	if (supportedScales.contains(scale)) {
    	    this.scale = scale;
    	}
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
        return totalCredits != 0 ? totalGrades / totalCredits : 0;
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
    	double result = 0.0;
    	int grade = c.getGrade();
    	if (grade > 49) {
    		if (scale == SCALE40) {
    			result = getSingleCourseGrade40(grade);
    		} else if (scale == SCALE40L) {
    			result = getSingleCourseGrade40L(grade);
    		} else if (scale == SCALE433) {
    			result = getSingleCourseGrade433(grade);
    		} else {
    			result = getSingleCourseGrade100(grade);
    		}
            result *= c.getCredits();
    	}
        return result;
    }
    
    private double getSingleCourseGrade40(int grade) {
    	double result = 0.0;
    	if (grade > 89) {
    		result = scale;
    	} else {
            result = grade * scale / 90.0;
    	}
        return result;
    }
    
    private double getSingleCourseGrade40L(int grade) {
    	if (grade >= 85) { // A and A+
    		return 4.0;
    	} else if (grade > 79) { // A-
    		return 3.7;
    	} else if (grade > 75) { // B+
    		return 3.3;
    	} else if (grade > 71) { // B
    		return 3.0;
    	} else if (grade > 67) { // B-
    		return 2.7;
    	} else if (grade > 63) { // C+
    		return 2.3;
    	} else if (grade > 59) { // C
    		return 2.0;
    	} else if (grade > 54) { // C-
    		return 1.7;
    	} else { // D
    		return 1.0;
    	}
    }
    
    private double getSingleCourseGrade433(int grade) {
    	double result = 0.0;
        if (grade > 89) {
        	result = scale;
        } else {
        	result = gpaMap433.get(grade);
        }
        return result;
    }
    
    private double getSingleCourseGrade100(int grade) {
        return grade;
    }
    
}
