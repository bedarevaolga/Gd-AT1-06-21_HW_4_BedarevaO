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
        assert new Triangle(a, b, c).checkTriangle();
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testAssertOutOfRange() {
        String str = "56611423635142524634664645";
        assertTrue( new Triangle(Integer.parseInt(str), 7, 14).checkTriangle());
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testAssertDifferentTypesOfInputParameters() throws IOException {
        Scanner sc = new Scanner(Paths.get("TypeOfData.txt"));
        assertTrue(new Triangle(Integer.parseInt(sc.nextLine()), 4, 5).checkTriangle());
        sc.close();
    }


    @Test
    public void testAssertEquilateral() {
        assertEquals(new Triangle(5, 5, 5).detectTriangle(), 1);
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

    @Test
    public void testAssertCheckSquareOfTriangleInt() {
        assert (compareDoubles(9.798, new Triangle(4, 5, 7).getSquare(), 0.001));
    }

    @Test
    public void testAssertCheckSquareOfTriangleDouble() {
        assert (compareDoubles(8.769, new Triangle(4.5, 4.5, 4.5).getSquare(), 0.001));
    }

    public static boolean compareDoubles(double x, double y, double delta) {
        return Math.abs(x - y) < delta;
    }

    @Test
    public void testAssertCheckSquareOfTriangleBigNumbers() {
        assertEquals(new Triangle(Math.pow(3, 50), Math.pow(3, 50), Math.pow(3, 50)).getSquare(),
                2.231650127466815E47);
    }

    @Test
    public void testAssertCheckSquareOfTriangleSmallNumbers() {
        assertEquals(new Triangle(Math.pow(3, -50), Math.pow(3, -50), Math.pow(3, -50)).getSquare(),
                8.40185464971763E-49);
    }
}

