import org.junit.Assert;
import org.junit.Test;

/**
 * Lab 6
 *
 * Test class for shapes and their comparators.
 *
 * @author Stephen
 * @version 2019-02-18
 */
public class ShapeTest
{
	//==================================================================================================================
	// Basic Shape Testing:
	//==================================================================================================================
    /**
     * Tests for the square class.
     */
	@Test
	public void SquareTest()
	{
		Shape sqr = new Square("Square1", 3.0);
		Assert.assertEquals("Square area incorrect.", 9.0, sqr.getArea(), 0.0001);
		Assert.assertEquals("Square perimeter incorrect.", 12.0, sqr.getPerimeter(), 0.0001);
		Assert.assertEquals("Square type incorrect.", "Square", sqr.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Square1", sqr.getId());
	}

	/**
     * Tests for the rectangle class.
     */
	@Test
	public void RectangleTest()
	{
		Shape rect = new Rectangle("Rectangle1", 3.0, 4.0);
		Assert.assertEquals("Rectangle area incorrect.", 12.0, rect.getArea(), 0.0001);
		Assert.assertEquals("Rectangle perimeter incorrect.", 14.0, rect.getPerimeter(), 0.0001);
		Assert.assertEquals("Rectangle type incorrect.", "Rectangle", rect.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Rectangle1", rect.getId());
		
	}

	/**
     * Tests for the EquilateralTriangle class.
     */
	@Test
	public void TriangleTest()
	{
		Shape triangle = new EquilateralTriangle("Triangle1", 3.0);
		Assert.assertEquals("Triangle area incorrect.", 3.897, triangle.getArea(), 0.001); 
		Assert.assertEquals("Triangle perimeter incorrect.", 9.0, triangle.getPerimeter(), 0.001);
		Assert.assertEquals("Triangle type incorrect.", "EquilateralTriangle", triangle.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Triangle1", triangle.getId());
	}

	/**
     * Tests for the trapezoid class. Also test that IDs are done correctly.
     */
	@Test
	public void TrapezoidTest()
	{
		Shape trap = new Trapezoid("Trapezoid1", 3.0, 3.0, 5.0, 4.0);
		Assert.assertEquals("Rectangle area incorrect.", 13.311, trap.getArea(), 0.001); 
		Assert.assertEquals("Rectangle perimeter incorrect.", 15.0, trap.getPerimeter(), 0.001);
		Assert.assertEquals("Rectangle type incorrect.", "Trapezoid", trap.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Trapezoid1", trap.getId());
	}

	/**
     * Tests for the ellipse class. Also test that IDs are done correctly.
     */
	@Test
	public void EllipseTest()
	{
		Shape circ = new Ellipse("Ellipse1", 3.0, 3.0);
		Assert.assertEquals("Ellipse area incorrect.", Math.PI*3.0*3.0, circ.getArea(),0.0001);
		Assert.assertEquals("Ellipse perimeter incorrect.",
				2 * Math.PI * Math.sqrt((Math.pow(3, 2) + Math.pow(3, 2)) / 2),
				circ.getPerimeter(),0.0001);
		Assert.assertEquals("Ellipse type incorrect.", "Ellipse",circ.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Ellipse1", circ.getId());

		Shape circs = new Ellipse("Ellipse2", 5.0, 3.0);
		Assert.assertEquals("Ellipse area incorrect.", Math.PI*3*5, circs.getArea(),0.0001);
		Assert.assertEquals("Ellipse perimeter incorrect.",
				2 * Math.PI * Math.sqrt((Math.pow(5.0, 2) + Math.pow(3.0, 2)) / 2),
				circs.getPerimeter(),0.0001);
		Assert.assertEquals("Ellipse type incorrect.", "Ellipse",circs.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Ellipse2", circs.getId());
	}

	/**
     * Tests for the circle class. Also test that IDs are done correctly.
     */
	@Test
	public void CircleTest()
	{
		Shape circle = new Circle("Circle1", 3.0);
		Assert.assertEquals("Circle area incorrect.", Math.PI * 3.0 * 3.0, circle.getArea(), 0.0001);
		Assert.assertEquals("Circle perimeter incorrect.",
				2 * Math.PI * 3.0,
				circle.getPerimeter(),0.0001);
		Assert.assertEquals("Ellipse type incorrect.", "Circle",circle.getShapeType());
		Assert.assertEquals("Shape ID incorrect.", "Circle1", circle.getId());
	}

	/**
	 * Tests for Shape's toString().
	 */
	@Test
	public void ShapeToStringTest()
	{
		Shape sq = new Rectangle("Rectangle1", 3.0, 2.0);
		Assert.assertEquals("Incorrect String", "Rectangle:\t ID = Rectangle1\t area = 6.000\t perimeter = 10.000", sq.toString());
	}

	//==================================================================================================================
	// Comparisons:
	//==================================================================================================================
	/**
     * Tests for the Shape Area Comparator class.
     */
	@Test
	public void CompareAreaTest()
	{
	    // Test equals:
		Shape rect = new Rectangle("R1", 3.0,3.0);
		Shape sqr = new Square("S1", 3.0);
		ShapeAreaComparator sc = new ShapeAreaComparator();
		Assert.assertEquals("ShapeAreaComparator should find shapes equal.", 0, sc.compare(rect, sqr));
		Assert.assertTrue("ShapeAreaComparator should find shapes equal.", sc.equals(rect, sqr));

		// Test equal perimeter, different area:
        Shape rect2 = new Rectangle("R2", 1.0, 9.0); // Area 9
        Shape sqr2 = new Square("S2", 5.0); // Area 25
        Assert.assertEquals("ShapeAreaComparator gave incorrect ordering.", -1, sc.compare(rect2, sqr2));
        Assert.assertEquals("ShapeAreaComparator gave incorrect ordering.", 1, sc.compare(sqr2, rect2));
        Assert.assertFalse("ShapeAreaComparator incorrectly finds shapes equal.", sc.equals(rect2, sqr2));

        // Test unequal perimeter and area:
        Assert.assertEquals("ShapeAreaComparator gave incorrect ordering.", 1, sc.compare(sqr2, rect));
        Assert.assertEquals("ShapeAreaComparator gave incorrect ordering.", -1, sc.compare(rect, sqr2));
        Assert.assertFalse("ShapeAreaComparator incorrectly finds shapes equal.", sc.equals(sqr2, rect));
	}

	/**
     * Tests for the Shape Perimter Comparator class.
     */
	@Test
	public void ComparePerimeterTest()
	{
		// Test equals
		Shape rect = new Rectangle("R1", 3.0,3.0);
		Shape sqr = new Square("S1", 3.0);
		ShapePerimeterComparator sc = new ShapePerimeterComparator();
		Assert.assertEquals("ShapePerimeterComparator should find shapes equal.", 0, sc.compare(rect, sqr));
		Assert.assertTrue("ShapePerimeterComparator should find shapes equal.", sc.equals(rect, sqr));
		
		// Test Equal Area, different perimeter
		Shape rect2 = new Rectangle("R2", 2.0, 18.0); // Perimeter 40
	    Shape sqr2 = new Square("S2", 6.0); // Perimeter 24
	    Assert.assertEquals("ShapePerimeterComparator gave incorrect ordering.", 1, sc.compare(rect2, sqr2));
	    Assert.assertEquals("ShapePerimeterComparator gave incorrect ordering.", -1, sc.compare(sqr2, rect2));
	    Assert.assertFalse("ShapePerimeterComparator incorrectly finds shapes equal.", sc.equals(rect2, sqr2));
		
		// Test unequal Area and Perimeter
	    Shape rect3 = new Rectangle("R3", 5.0, 3.0); // Area 15, Perimeter 16
	    Shape sqr3 = new Square("S3", 5.0); // Area 25, Perimeter 20
	    Assert.assertEquals("ShapePerimeterComparator gave incorrect ordering.", -1, sc.compare(rect3, sqr3));
	    Assert.assertEquals("ShapePerimeterComparator gave incorrect ordering.", 1, sc.compare(sqr3, rect3));
	    Assert.assertFalse("ShapePerimeterComparator incorrectly finds shapes equal.", sc.equals(rect3, sqr3));
		
	}

	/**
	 * Tests the natural ordering of shapes (compareTo in shape)
	 */
	@Test
    public void NaturalCompareTest()
    {
		// Test equals
		Shape rect = new Rectangle("R1", 3.0,3.0);
		Shape sqr = new Square("S1", 3.0);
		Assert.assertEquals("Shape's method compareTo should find shapes equal.",0 , rect.compareTo(sqr));
		Assert.assertEquals("Shape's method compareTo should find shapes equal.",0 , sqr.compareTo(rect));
		
		// Test Equal Area, different perimeter
		Shape rect2 = new Rectangle("R2", 2.0, 18.0); // Perimeter 40
		Shape sqr2 = new Square("S2", 6.0); // Perimeter 24
		Assert.assertEquals("Shape's method compareTo gave incorrect ordering.", 1, rect2.compareTo(sqr2));
		Assert.assertEquals("Shape's method compareTo gave incorrect ordering.", -1, sqr2.compareTo(rect2));
		
		// Test Equal Perimeter, different area
		Shape rect3 = new Rectangle("R3", 10.0, 2.0); // Perimeter 24 , Area 20
		Shape sqr3 = new Square("S3", 6.0); // Perimeter 24 , Area 36
		Assert.assertEquals("Shape's method compareTo gave incorrect ordering.", -1, rect3.compareTo(sqr3));
		Assert.assertEquals("Shape's method compareTo gave incorrect ordering.", 1, sqr3.compareTo(rect3));
		
		// Test unequal Area and Perimeter
		// Test Area precedence in compareTo method
	    Shape rect4 = new Rectangle("R4", 10.0, 3.0); // Area 30, Perimeter 26
	    Shape sqr4 = new Square("S4", 6.0); // Area 36, Perimeter 24
	    // rect4 has greater perimeter yet less area than sqr4 and should return -1 when compared to sqr4 due to Area's precedence in compareTo method
	    Assert.assertEquals("Shape's method compareTo gave incorrect ordering.", -1, rect4.compareTo(sqr4));
		Assert.assertEquals("Shape's method compareTo gave incorrect ordering.", 1, sqr4.compareTo(rect4));
				
		
    }
}
