public class InputParser {
    public static boolean parse(String input, String[] result, String splitter) {
		String[] rowDataRaw = input.split(splitter);
		if (rowDataRaw.length > 2) {
			String courseID = rowDataRaw[0].trim();
			courseID = UBCCourseFactory.validateCourseID(courseID);
			int grade = Integer.parseInt(rowDataRaw[1].trim());
			grade = UBCCourseFactory.validateGrade(grade);
			double credits = Double.parseDouble(rowDataRaw[2].trim());
			credits = UBCCourseFactory.validateCredits(credits);
			if (grade < 50) {
				credits = 0;
			}
			result[0] = courseID;
			result[1] = String.valueOf(grade);
			result[2] = String.format("%.1f", credits);
			return true;
		} else {
			return false;
		}
    }
}
