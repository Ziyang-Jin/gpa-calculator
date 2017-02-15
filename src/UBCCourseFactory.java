public class UBCCourseFactory {
    private static final int COURSE_DOMAIN_LENGTH = 4;
    private static final String COURSE_FAILED = "Failed course cannot be used to calculate GPA";

    public static String validateCourseID(String courseID) throws InvalidInputException {
        if (courseID.length() > 6) {
            String courseDomain = courseID.substring(0, COURSE_DOMAIN_LENGTH);
            courseDomain = validateCourseDomain(courseDomain);
            
            String courseNumber = courseID.substring(COURSE_DOMAIN_LENGTH).trim();
            courseNumber = validateCourseNumber(courseNumber);
            
            courseID = courseDomain + ' ' + courseNumber;
        } else {
        	throw new InvalidInputException("courseID=" + courseID);
        }
        return courseID;
    }

    private static String validateCourseDomain(String courseDomain) throws InvalidInputException {
        courseDomain = courseDomain.toUpperCase();
        for (int i = 0; i < courseDomain.length(); i++) {
            if (!Character.isLetter(courseDomain.charAt(i))) {
                throw new InvalidInputException("courseDomain=" + courseDomain);
            }
        }
        return courseDomain;
    }
    
    private static String validateCourseNumber(String courseNumber) throws InvalidInputException {
        String result = "";
        for (int i = 0; i < courseNumber.length(); i++) {
            char c = courseNumber.charAt(i);
            if (Character.isDigit(c)) {
                result = result + c;
            }
        }
        if (result.compareTo("000") < 0 || result.compareTo("999") > 0) {
            throw new InvalidInputException("courseNumber=" + courseNumber);
        }
        char last = courseNumber.charAt(courseNumber.length()-1);
        if (Character.isLetter(last)) {
        	result = result + Character.toUpperCase(last);
        }
        return result;
    }
    
    public static int validateGrade(int grade) throws InvalidInputException {
        if (grade < 0 || grade > 100) {
            throw new InvalidInputException("grade=" + grade);
        } else if (grade < 50) {
            throw new InvalidInputException(COURSE_FAILED);
        }
        return grade;
    }
    
    public static double validateCredits(double credits) throws InvalidInputException {
        if (credits < 0.0 || credits > 10.0) {
            throw new InvalidInputException("invalid credits: " + credits);
        }
        return credits;
    }
}
