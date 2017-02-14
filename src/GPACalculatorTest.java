import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GPACalculatorTest {
    private static final String TESTMSG = "Test GPA on a scale of ";
    private static final String FAILMSG = "GPA calculation is wrong";
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
    public void testDefaultScale() {
        System.out.println("Test default scale: " + GPACalculator.DEFAULT_SCALE);
        calculateGPAAndPrint();
    }
    
    @Test
    public void testScale100() {
        System.out.println(TESTMSG + "100");
        gpaCalculator.setScale(GPACalculator.SCALE100);
        double gpa = calculateGPAAndPrint();
        if (gpa < 86.25 || gpa >= 86.35) {
            fail(FAILMSG + "(Scale100)");
        }
    }
    
    @Test
    public void testScale433() {
        System.out.println(TESTMSG + "4.33");
        gpaCalculator.setScale(GPACalculator.SCALE433);
        double gpa = calculateGPAAndPrint();
        if (gpa < 3.95 || gpa >= 4.05) {
            fail(FAILMSG + "(Scale4.33)");
        }
    }
    
    @Test
    public void testScale40L() {
        System.out.println(TESTMSG + "4.0(Letter)");
        gpaCalculator.setScale(GPACalculator.SCALE40L);
        double gpa = calculateGPAAndPrint();
        if (gpa < 3.75 || gpa >= 3.85) {
            fail(FAILMSG + "(Scale4.0[letter])");
        }
    }
    
    @Test
    public void testScale40() {
        System.out.println(TESTMSG + "4.0(Grade)");
        gpaCalculator.setScale(GPACalculator.SCALE40);
        double gpa = calculateGPAAndPrint();
        if (gpa < 3.75 || gpa >= 3.85) {
            fail(FAILMSG + "(Scale4.0[grade])");
        }
    }
    
    @Test
    public void testOutPut() {
        gpaCalculator.outputCoursesInfo();
    }
    
    private double calculateGPAAndPrint() {
        double gpa = gpaCalculator.calculateGPA();
        System.out.println("GPA="+gpa);
        return gpa;
    }

}
