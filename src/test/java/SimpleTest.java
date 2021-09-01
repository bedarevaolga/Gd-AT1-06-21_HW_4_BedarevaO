import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.testng.Assert.*;

public class SimpleTest {

    @DataProvider
    public static Object[][] checkTriangle() {
        return new Object[][]{
                {-9, 9, 5},// check negative numbers
                {9, -4, 9},
                {5, 4, -9},
                {5, 67, 9},// check sums of sides of a triangle
                {5, 9, 109},
                {105, 9, 10},
        };
    }

    @Test(dataProvider = "checkTriangle")
    public void testAssertCheckTriangle(double a, double b, double c) {
        assertFalse(new Triangle(a, b, c).checkTriangle());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testAssertOutOfRange() {
        String str = "56611423635142524634664645";
        assertTrue(new Triangle(Integer.parseInt(str), 7, 14).checkTriangle());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testAssertDifferentTypesOfInputParameters() throws IOException {
        Scanner sc = new Scanner(Paths.get("TypeOfData.txt"));
        assertTrue(new Triangle(Integer.parseInt(sc.nextLine()), 4, 5).checkTriangle());
        sc.close();
    }

    @DataProvider
    public static Object[][] equilateralTriangle() {
        return new Object[][]{
                {5, 5, 5},
                {123456789, 123456789, 123456789},
                {987654.321, 987654.321, 987654.321},
                {Math.pow(3, 50), Math.pow(3, 50), Math.pow(3, 50)},
                {Math.pow(3, -50), Math.pow(3, -50), Math.pow(3, -50)},
        };
    }

    @Test(dataProvider = "equilateralTriangle")
    public void testAssertEquilateral(double a, double b, double c) {
        Assert.assertEquals(new Triangle(a, b, c).detectTriangle(), 1);
    }

    @DataProvider
    public static Object[][] isoscelesTriangle() {
        return new Object[][]{
                {9, 9, 5},
                {9, 4, 9},
                {5, 9, 9}
        };
    }

    @Test(dataProvider = "isoscelesTriangle")
    public void testAssertIsosceles(double a, double b, double c) {
        Assert.assertEquals(new Triangle(a, b, c).detectTriangle(), 2);
    }

    @DataProvider
    public static Object[][] rectangularTriangle() {
        return new Object[][]{
                {3, 4, 5},
                {3, 5, 4},
                {5, 4, 3}
        };
    }

    @Test(dataProvider = "rectangularTriangle")
    public void testAssertRectangular(double a, double b, double c) {
        Assert.assertEquals(new Triangle(a, b, c).detectTriangle(), 8);
    }

    @DataProvider
    public static Object[][] rectangularAndIsoscelesTriangle() {
        return new Object[][]{
                {Math.sqrt(128), 8, 8},
                {8, Math.sqrt(128), 8},
                {8, 8, Math.sqrt(128)}
        };
    }

    @Test(dataProvider = "rectangularAndIsoscelesTriangle")
    public void testAssertRectangularAndIsosceles(double a, double b, double c) {
        Assert.assertEquals(new Triangle(a, b, c).detectTriangle(), 10);
    }

    @DataProvider
    public static Object[][] ordinaryTriangle() {
        return new Object[][]{
                {9, 7, 5},
                {9, 4, 11},
                {5, 8, 9}
        };
    }

    @Test(dataProvider = "ordinaryTriangle")
    public void testAssertOrdinary(double a, double b, double c) {
        Assert.assertEquals(new Triangle(a, b, c).detectTriangle(), 4);
    }

    @DataProvider
    public static Object[][] checkSquareOfTriangle() {
        return new Object[][]{
                {4, 5, 7, 9.798},
                {4.5, 4.5, 4.5, 8.769},
                {Math.pow(3, 50), Math.pow(3, 50), Math.pow(3, 50), 2.231650127466815E47},
                {Math.pow(3, -50), Math.pow(3, -50), Math.pow(3, -50), 8.40185464971763E-49}
        };
    }

    @Test(dataProvider = "checkSquareOfTriangle")
    public void testAssertCheckSquareOfTriangle(double a, double b, double c, double result) {
        Assert.assertEquals(new Triangle(a, b, c).getSquare(), result);
    }

    @Test
    public void testAssertOutOfRangeSquare() {
        Triangle triangle = new Triangle(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        assertNotEquals(triangle.getSquare(), Double.POSITIVE_INFINITY);
    }

}

