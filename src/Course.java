public class Course {
    private String courseID;
    private int grade;
    private double credits;

    public Course(String courseID, int grade, double credits) {
        this.courseID = UBCCourseFactory.validateCourseID(courseID);
	    this.grade = UBCCourseFactory.validateGrade(grade);
	    this.credits = UBCCourseFactory.validateCredits(credits);
    }

    public String getCourseID() {
        return this.courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public String printCourseInfo() {
    	String courseIDPart = "courseID: " + courseID;
        String yourGradePart = "your grade: " + grade;
    	String creditsPart = "credits: " + credits;
    	String s = courseID + ", " + grade + ", " + credits;
    	System.out.println(s);
    	return s;
    }
}
