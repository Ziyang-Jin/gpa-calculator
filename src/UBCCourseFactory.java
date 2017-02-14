public class UBCCourseFactory {
    private static final int COURSE_DOMAIN_LENGTH = 4;

    public static String validateCourseID(String courseID) {
        if (courseID.length() < 7) {
            msgInvalidCourseID("length too short");
        } else {
            String courseDomain = courseID.substring(0, COURSE_DOMAIN_LENGTH);
            courseDomain = validateCourseDomain(courseDomain);
            
            String courseNumber = courseID.substring(COURSE_DOMAIN_LENGTH).trim();
            courseNumber = validateCourseNumber(courseNumber);
            
            courseID = courseDomain + ' ' + courseNumber;
        }
        return courseID;
    }

    private static String validateCourseDomain(String courseDomain) {
        courseDomain = courseDomain.toUpperCase();
        for (int i = 0; i < courseDomain.length(); i++) {
            if (!Character.isLetter(courseDomain.charAt(i))) {
                msgInvalidCourseID("courseDomain=" + "courseDomain");
            }
        }
        return courseDomain;
    }
    
    private static String validateCourseNumber(String courseNumber) {
        String result = "";
        for (int i = 0; i < courseNumber.length(); i++) {
            char c = courseNumber.charAt(i);
            if (Character.isDigit(c)) {
                result = result + c;
            }
        }
        if (result.compareTo("000") < 0 || result.compareTo("999") > 0) {
            msgInvalidCourseID("courseNumer=" + result);
        }
        return result;
    }
    
    private static void msgInvalidCourseID(String s) {
        System.out.println("invalid course ID: " + s);
    }
    
    public static int validateGrade(int grade) {
        if (grade < 0 || grade > 100) {
            System.out.println("invalid grade");
            System.out.println("reset grade to 0");
            grade = 0;
        } else if (grade < 50) {
            System.out.println("course failed");
        }
        return grade;
    }
    
    public static double validateCredits(double credits) {
        if (credits < 0.0 || credits > 10.0) {
            System.out.println("invalid credits: " + credits);
            System.out.println("reset credits to 0.0");
            credits = 0.0;
        }
        return credits;
    }
}
