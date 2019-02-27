import org.junit.Assert;
import org.junit.Test;

/**
 * Lab 6
 *
 * Test class for ShapeSorter.
 * Implicitly tests that the comparators are implemented correctly.
 *
 * @author Stephen
 * @version 2019-02-18
 */
public class ShapeSorterTest
{
	/**
	 * Test that shapes are added correctly.
	 */
	@Test
	public void AddShapeTest()
	{
		ShapeSorter sorter = new ShapeSorter();

		Shape a = new Rectangle("test", 3, 3);
		Shape b = new EquilateralTriangle("test2", 4);
		Shape c = new Square("test3", 3);
		Shape d = new Circle("test4", 1.5);

		sorter.addShape(a);
		sorter.addShape(b);
		sorter.addShape(c);
		sorter.addShape(d);

		Assert.assertEquals("Shapes added incorrectly...", a, sorter.shapes.get(0));
		Assert.assertEquals("Shapes added incorrectly...", b, sorter.shapes.get(1));
		Assert.assertEquals("Shapes added incorrectly...", c, sorter.shapes.get(2));
		Assert.assertEquals("Shapes added incorrectly...", d, sorter.shapes.get(3));

	}

	/**
	 * Tests sorting via the default ordering.
	 */
	@Test
	public void SortShapesDefaultTest()
	{
		ShapeSorter sorter = new ShapeSorter();
		
		Shape a = new Rectangle("test", 4, 3);
		Shape b = new EquilateralTriangle("test2", 4);
		Shape c = new Square("test3", 3);
		Shape d = new EquilateralTriangle("testArea", 5.264296052); // Shape with equal area to "a" to test perimeter evaluation of Shape.compareTo()
		Shape e = new Square("largest area", 10);
		
		sorter.addShape(a);
		sorter.addShape(b);
		sorter.addShape(c);
		sorter.addShape(d);
		sorter.addShape(e);
		
		double areaA = 4.0 * 3.0; // areaA = 12
		double areaB = 4.0 * 4.0 * ( (Math.sqrt(3.0)) / 4.0); // areaB = 6.928
		double areaC = 3.0 * 3.0; //areaC = 9
		double areaD = 5.264296052 * 5.264296052 * ( (Math.sqrt(3.0)) / 4.0); // areaD = 12
		double areaE = 10.0 * 10.0; // areaE = 100
		double periA = (4.0 * 2.0) + (3.0 * 2.0); //periA = 14
		double periB = (4.0 * 3.0); //periB = 12;
		double periC = (3.0 * 4.0); //periC = 12;
		double periD = (5.264296052 * 3.0); // periD = 15.79289
		double periE = 4.0 * 10.0; //periE = 40
		
		
		sorter.sortShapes();
		
		/*
		 * According to Shape.compareTo():
		 * Shapes with least area at beginning
		 * Shapes with same area:
		 * Shapes with least perimeter at beginning
		 * 						
		 */
		
		Assert.assertEquals("Shapes out of order..." , b, sorter.shapes.get(0));
		Assert.assertEquals("Shapes out of order..." , c, sorter.shapes.get(1));
		Assert.assertEquals("Shapes out of order..." , a, sorter.shapes.get(2));
		Assert.assertEquals("Shapes out of order..." , d, sorter.shapes.get(3));
		Assert.assertEquals("Shapes out of order..." , e, sorter.shapes.get(4));
		
		
	}

	/**
	 * Tests sorting by area ordering.
	 */
	@Test
	public void SortShapesAreaTest()
	{
		ShapeSorter sorter = new ShapeSorter();
		
		Shape a = new Rectangle("test", 4, 3);
		Shape b = new EquilateralTriangle("test2", 4);
		Shape c = new Square("test3", 3);
		Shape d = new EquilateralTriangle("test4", 5.264296052); // Shape with equal area to "a" to test perimeter evaluation of Shape.compareTo()
		Shape e = new Square("largest area", 10);
		
		sorter.addShape(a);
		sorter.addShape(b);
		sorter.addShape(c);
		sorter.addShape(d);
		sorter.addShape(e);
		
		double areaA = 4.0 * 3.0; // areaA = 12
		double areaB = 4.0 * 4.0 * ( (Math.sqrt(3.0)) / 4.0); // areaB = 6.928
		double areaC = 3.0 * 3.0; //areaC = 9
		double areaD = 5.264296052 * 5.264296052 * ( (Math.sqrt(3.0)) / 4.0); // areaD = 12
		double areaE = 10.0 * 10.0; // areaE = 100
		
		sorter.sortShapes(new ShapeAreaComparator());
		
		/*
		 * According to ShapeAreaComparator.compare():
		 * Shapes with least area at beginning
		 * Shapes with same area in the middle
		 * Shapes with most area at the end
		 * 						
		 */
		

		/* 
		 * Shapes sorted the by ShapeAreaComparator are put together if the shapes have the same area
		 * Since the Collections.sort() sorts in ascending order and these shapes with the same area are assigned 0 for sorting
		 * Shapes with the same area will be together in the order in which they are added (Tested Statement)
		 */
		
		Assert.assertEquals("Shapes out of order..." , b, sorter.shapes.get(0));
		Assert.assertEquals("Shapes out of order..." , c, sorter.shapes.get(1));
		Assert.assertEquals("Shapes out of order..." , a, sorter.shapes.get(2));
		Assert.assertEquals("Shapes out of order..." , d, sorter.shapes.get(3));
		Assert.assertEquals("Shapes out of order..." , e, sorter.shapes.get(4));
		
	}

	/**
	 * Tests sorting by perimeter ordering.
	 */
	@Test
	public void SortShapesPerimeterTest()
	{
		ShapeSorter sorter = new ShapeSorter();
		
		Shape a = new Rectangle("test", 5, 4);
		Shape b = new EquilateralTriangle("test2", 4);
		Shape c = new Square("test3", 3);
		Shape d = new EquilateralTriangle("test4", 5.264296052);
		Shape e = new Square("test5", 10);
		
		sorter.addShape(a);
		sorter.addShape(b);
		sorter.addShape(c);
		sorter.addShape(d);
		sorter.addShape(e);
		
		double periA = (5.0 * 2.0) + (4.0 * 2.0); //periA = 18
		double periB = (4.0 * 3.0); //periB = 12;
		double periC = (3.0 * 4.0); //periC = 12;
		double periD = (5.264296052 * 3.0); // periD = 15.79289
		double periE = 4.0 * 10.0; //periE = 40
		double periF = (4.0 * 3.0); //periF = 12;
		
		sorter.sortShapes(new ShapePerimeterComparator());
		
		/* 
		 * According to ShapePerimeterComparator.compare
		 * Shapes with least perimeter in the beginning
		 * Shapes with equal perimeter together in between
		 * Shapes with greatest perimeter at the end.
		 */
		

		/* 
		 * Shapes sorted the by ShapePerimeterComparator are put together if the shapes have the same perimeter
		 * Since the Collections.sort() sorts in ascending order and these shapes with the same perimeter are assigned 0 for sorting
		 * Shapes with the same perimeter will be together in the order in which they are added (Tested Statement)
		 */
		
		Assert.assertEquals("Shapes out of order..." , b, sorter.shapes.get(0));
		Assert.assertEquals("Shapes out of order..." , c, sorter.shapes.get(1));
		Assert.assertEquals("Shapes out of order..." , d, sorter.shapes.get(2));
		Assert.assertEquals("Shapes out of order..." , a, sorter.shapes.get(3));
		Assert.assertEquals("Shapes out of order..." , e, sorter.shapes.get(4));
	}

	/**
	 * Tests the toString.
	 */
	@Test
	public void ToStringTest()
	{
		ShapeSorter sorter = new ShapeSorter();
		Shape a = new Rectangle("test", 4, 3);
		Shape b = new Rectangle("test2", 3, 2);
		
		sorter.addShape(a);
		sorter.addShape(b);
		
		Assert.assertEquals("Wrong string given for this shape...", "Rectangle:\t ID = test\t area = 12.000\t perimeter = 14.000\n"
				+ "Rectangle:\t ID = test2\t area = 6.000\t perimeter = 10.000\n", sorter.toString());
		
	}
}
