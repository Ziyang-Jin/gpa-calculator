package calculator.gpa;

public class InvalidInputException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String msg) {
    	super("Invalid Input - " + msg);
    }
}
