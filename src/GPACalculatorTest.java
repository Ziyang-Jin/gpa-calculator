import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GPACalculatorTest {
	private static GPACalculator gpaCalculator;
	
	@BeforeClass
	public static void setUpGPACaculator() {
		gpaCalculator = new GPACalculator();
	}
	
	@Before
	public void setUpCourses() {
		Course JAPN100 = new Course("JAPN 100", 90, 3.0);
		Course CPSC320 = new Course("CPSC 320", 86, 3.0);
		Course PHYS119 = new Course("PHYS 119", 78, 1.0);
		Course ENGL110 = new Course("ENGL 110", 66, 3.0);
		Course ENGL112 = new Course("ENGL 112", 69, 3.0);
		Course CPSC312 = new Course("CPSC 312", 89, 3.0);
		Course CPSC221 = new Course("CPSC 221", 91, 4.0);
		Course CPSC213 = new Course("CPSC 213", 93, 4.0);
		Course ECON101 = new Course("ECON 101", 88, 3.0);
		Course CPSC210 = new Course("CPSC 210", 84, 4.0);
		Course STAT200 = new Course("STAT 200", 88, 3.0);
		Course MATH200 = new Course("MATH 200", 92, 3.0);
		Course CPSC121 = new Course("CPSC 121", 93, 4.0);
		Course CPSC110 = new Course("CPSC 110", 89, 4.0);
		gpaCalculator.registerCourse(JAPN100);
		gpaCalculator.registerCourse(CPSC320);
		gpaCalculator.registerCourse(PHYS119);
		gpaCalculator.registerCourse(ENGL110);
		gpaCalculator.registerCourse(ENGL112);
		gpaCalculator.registerCourse(CPSC312);
		gpaCalculator.registerCourse(CPSC221);
		gpaCalculator.registerCourse(CPSC213);
		gpaCalculator.registerCourse(ECON101);
		gpaCalculator.registerCourse(CPSC210);
		gpaCalculator.registerCourse(STAT200);
		gpaCalculator.registerCourse(MATH200);
		gpaCalculator.registerCourse(CPSC121);
		gpaCalculator.registerCourse(CPSC110);
	}

	@Test
	public void test() {
		System.out.println(gpaCalculator.calculateGPA());
		gpaCalculator.setScale(4.0);
		System.out.println(gpaCalculator.calculateGPA());
	}
	
    @Test
    public void test2() {
    	gpaCalculator.outputCoursesInfo();
    }

}
