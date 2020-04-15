/*
 * Unit test demo code for TCSS 305
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import shapes.Point;
/* Since the Point class is not in the same package as this test class we need to import it.
 * Be careful to import the correct Point class since the Java language also contains
 * several classes called 'Point'.
 */

/**
 * Tests of the Point class.
 * 
 * @author Alan Fowler
 * @version 1.2
 */
public class PointTest {

    /** A tolerance (sometimes referred to as 'delta' or 'epsilon')
     *  used when comparing double or float values for equality.
     * 
     *  Primitive types double and float are stored as approximations because some values
     *  cannot be exactly represented in binary using ones and zeros.
     *  
     *  Example: System.out.println(".1 + .2 = " + (.1 + .2)); // will NOT produce .3 exactly
     *  
     *  Therefore, when comparing double or float values for equality it does not make sense
     *  to test for exact equality. Instead, check if two values are very close to each other
     *  (within whatever tolerance you wish to set).
     *  
     *  The JUnit documentation states:
     *  "delta - the maximum delta between expected and actual
     *  for which both numbers are still considered equal."
     *  
     *  Another way to think of the tolerance is that
     *  assertEquals(expected, actual, delta)
     *  will assert to true as long as Math.abs(expected - actual) < delta
     *  
     *  The JUnit documentation does not suggest a specific value to use.
     *  That depends on the situation and is up to the test developer to decide.
     *  
     *  
     *  
     *  FYI the upcoming JUnit 5 will make delta optional
     *  when calling assertEquals() with two doubles.
     *  The implementation (if you're interested) is:
     *  
     *  private static boolean doublesAreEqual(double expected, double actual) {
     *      return Double.doubleToLongBits(expected) == Double.doubleToLongBits(actual);
     *  }
     *  
     *  This implementation converts the 'expected' and 'actual' double values to
     *  their binary representations and then compares the binary representations for equality.
     */
    private static final double TOLERANCE = .000001;

    // The test fixture. The Objects I will use in the tests.
    /** A Point to use in the tests. */
    private Point myPoint;

    /**
     * A method to initialize the test fixture before each test.
     */
    @Before
    // This method runs before EACH test method.
    public void setUp() { // no need to throw any exceptions!
        myPoint = new Point();
    }
    
    /**
     * Test of the default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        /*
         *  Ideally a unit test should only contain a single assertion
         *  or it should only test a single exception. However, the following two assertions
         *  are so closely related that it is reasonable to put them both in the same test.
         */
        assertEquals(0, myPoint.getX(), TOLERANCE);
        assertEquals(0, myPoint.getY(), TOLERANCE);
        
        
        // Alternatively you could code the same tests this way:
        // (Using the public constant in the Point class as the expected value)
//        assertEquals(Point.DEFAULT_X, myPoint.getX(), TOLERANCE);
//        assertEquals(Point.DEFAULT_X, myPoint.getY(), TOLERANCE);
    }
    
    /**
     * Test of the overloaded constructor.
     */
    @Test
    public void testOverloadedConstructor() {
        
        final Point p1 = new Point(10, -10);
        
        /*
         *  Ideally a unit test should only contain a single assertion
         *  or it should only test a single exception. However, the following two assertions
         *  are so closely related that it is reasonable to put them both in the same test.
         */
        assertEquals(10, p1.getX(), TOLERANCE);
        assertEquals(-10, p1.getY(), TOLERANCE);
    }
    
    /**
     * Test of the copy constructor.
     */
    @Test
    public void testCopyConstructor() {
        
        final Point p1 = new Point(myPoint);
        
        /*
         *  Ideally a unit test should only contain a single assertion
         *  or it should only test a single exception. However, the following 3 assertions
         *  might be closely enough related to put all in the same test.
         *  
         *  However, some engineers might argue that
         *  the third assertion should be in a separate test.
         */
        
        assertEquals(0, p1.getX(), TOLERANCE);
        assertEquals(0, p1.getY(), TOLERANCE);
        
        // make sure that p1 is actually a new point and not just a reference to myPoint
        assertNotSame(myPoint, p1);
        
        
        // The first two assertions could alternatively be coded like this:
//        assertEquals(myPoint.getX(), p1.getX(), TOLERANCE);
//        assertEquals(myPoint.getY(), p1.getY(), TOLERANCE);       
    }

    /**
     * Test of the copy constructor when the parameter is null.
     */
    @Test(expected = NullPointerException.class)
    public void testCopyConstructorNull() {
        // No assertion is needed when testing an expected exception.
        // Just call code which should result in the expected exception.
        new Point(null);
    }

    /**
     * Test method for {@link Point#calculateDistance(Point)}.
     */
    @Test
    public void testCalculateDistance() {
        
        /*
         *  Ideally a unit test should only contain a single assertion
         *  or it should only test a single exception. However, the following assertions
         *  are closely related, so it might be reasonable to put them in the same test.
         *  Some software engineers would recommend three separate tests instead.
         */
        
        assertEquals("testCalculateDistance failed!", 5,
                     myPoint.calculateDistance(new Point(3, 4)), TOLERANCE);

        assertEquals("testCalculateDistance failed!", 5,
                     myPoint.calculateDistance(new Point(-3, -4)), TOLERANCE);

        assertEquals("testCalculateDistance failed!", 0,
                     myPoint.calculateDistance(new Point(myPoint)), TOLERANCE);
    }

    /**
     * Test method for calculateDistance(Point) when the parameter is null}.
     */
    @Test(expected = NullPointerException.class)
    public void testCalculateDistanceNull() {
        // No assertion is needed when testing an expected exception.
        // Just call code which should result in the expected exception.
        myPoint.calculateDistance(null);
    }

    /**
     * Test method for {@link Point#setX(double)}.
     */
    @Test
    public void testSetX() {
        myPoint.setX(42);

        // this is NOT the correct way to test:
        // assertTrue("setX failed!", myPoint.getX() == 42);

        // double and float are stored imprecisely
        // test double and float using a tolerance:

        // a better way to do the test
        assertEquals("setX failed to set the x value correctly!",
                     42, myPoint.getX(), TOLERANCE);

        // You might also check that myY did not change (it should not).
        assertEquals("setX changed the Y value!", 0, myPoint.getY(), TOLERANCE);
    }

    /**
     * Test method for {@link Point#setY(double)}.
     */
    @Test
    public void testSetY() {
        myPoint.setY(-42);
        assertEquals("setY failed!", -42, myPoint.getY(), TOLERANCE);
        assertEquals("setY failed!", 0, myPoint.getX(), TOLERANCE);
    }

    /**
     * Test method for {@link Point#translate(double, double)}.
     */
    @Test
    public void testTranslate() {
        myPoint.translate(10, -5);
        assertEquals("translate failed!", 10, myPoint.getX(), TOLERANCE);
        assertEquals("translate failed!", -5, myPoint.getY(), TOLERANCE);
    }

    /**
     * Test method for {@link Point#toString()}.
     */
    @Test
    public void testToString() {
        
        assertEquals("toString() produced an unexpected result!", "Point (0.00, 0.00)",
                     myPoint.toString());
        
    }
    
    
    
    
    
    
    
    // The following tests are not necessary to test the Point class
    // and just here for instructional purposes:
    
    
    /**
     * Test method to show various ways to code the 'tolerance'
     * for equality tests of primitive doubles and floats.
     */
    @Test
    public void testTolerance() {
        
        // First, let's use an invalid hard coded tolerance.
        // If you comment this test in and run it it will fail.
        //assertEquals(.3, .1 + .2, 0);
        
        assertEquals(.3, .1 + .2, .0001); // a valid hard coded tolerance
        
        assertEquals(.3, .1 + .2, 1e-6); // another valid hard coded tolerance
        
        // If the tolerance is too small, the test fails.
        //assertEquals(.3, .1 + .2, Double.MIN_VALUE);
        
        // If the tolerance is too small, the test fails.
        // 4.9E-324 is equivalent to Double.MIN_VALUE
        //assertEquals(.3, .1 + .2, 4.9E-324);
        
    }

    /**
     * Test method to demonstrate some other assertions.
     */
    @Test
    public void testOther() {
        /*
         * Here are some additional (unnecessary) tests just to demonstrate some
         * other assertions:
         */
        
        /*
         * These tests are NOT very closely related and should NOT all be dumped into
         * a single test like this. I am only dumping all of these into one test
         * in order to demonstrated various assertion types.
         */

        assertSame("These are the same object!", myPoint, myPoint);
        assertNotSame("These are NOT the same object!", myPoint, new Point(myPoint));

        Point point2 = null;
        assertNull("point2 should be null!", point2);
        point2 = new Point();
        assertNotNull("point2 should not be null!", point2);

        myPoint.setX(42);
        assertTrue("This expression should be true!", myPoint.getX() > 0);
        assertFalse("This expression should be false!", myPoint.getX() < 0);
    }

}
