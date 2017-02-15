package calculator.gpa;

public class InputParser {
    public static boolean parse(String input, String[] result, String splitter) throws InvalidInputException {
        String[] rowDataRaw = input.split(splitter);
        if (rowDataRaw.length > 2) {
            String courseID = parseCourseID(rowDataRaw[0]);
            int grade = parseGrade(rowDataRaw[1]);
            double credits = parseCredits(rowDataRaw[2], grade);
            writeResult(courseID, grade, credits, result);
            return true;
        } else {
            return false;
        }
    }
    
    private static String parseCourseID(String s) throws InvalidInputException {
    	String courseID = s.trim();
    	return UBCCourseFactory.validateCourseID(courseID);
    }
    
    private static int parseGrade(String s) throws InvalidInputException {
        String gradeString = getIntString(s.trim());
        int grade = Integer.parseInt(gradeString);
        return UBCCourseFactory.validateGrade(grade);
    }
    
    private static double parseCredits(String s, int grade) throws InvalidInputException {
        String creditsString = getDoubleString(s.trim());
        double credits = Double.parseDouble(creditsString);
        credits = UBCCourseFactory.validateCredits(credits);
        return grade >= 50 ? credits : 0;
    }
    
    private static void writeResult(String courseID, int grade, double credits, String[] result) {
        result[0] = courseID;
        result[1] = String.valueOf(grade);
        result[2] = String.format("%.1f", credits);
    }
    
    private static String getIntString(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                result = result + c;
            }
        }
        return result;
    }
    
    private static String getDoubleString(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                result = result + c;
            }
        }
        return result;
    }
}
