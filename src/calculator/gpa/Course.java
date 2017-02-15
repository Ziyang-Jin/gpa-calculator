package calculator.gpa;

public class Course {
    private String courseID;
    private int grade;
    private double credits;

    public Course(String courseID, int grade, double credits) {
        this.courseID = courseID;
        this.grade = grade;
        this.credits = credits;
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
        String s = courseID + ", " + grade + ", " + credits;
        System.out.println(s);
        return s;
    }
}
