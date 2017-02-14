public class InputParser {
    public static boolean parse(String input, String[] result, String splitter) {
		String[] rowDataRaw = input.split(splitter);
		if (rowDataRaw.length > 2) {
			String courseID = rowDataRaw[0].trim();
			courseID = UBCCourseFactory.validateCourseID(courseID);
			
			String gradeString = getIntString(rowDataRaw[1].trim());
			int grade = Integer.parseInt(gradeString);
			grade = UBCCourseFactory.validateGrade(grade);
			
			String creditsString = getDoubleString(rowDataRaw[2].trim());
			double credits = Double.parseDouble(creditsString);
			credits = grade >= 50 ? UBCCourseFactory.validateCredits(credits) : 0;
			
			result[0] = courseID;
			result[1] = String.valueOf(grade);
			result[2] = String.format("%.1f", credits);
			return true;
		} else {
			return false;
		}
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
