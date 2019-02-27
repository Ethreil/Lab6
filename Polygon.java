import java.util.ArrayList;

/**
 * Abstract shape class to define a polygon.
 *
 * @author Stephen
 * @version 2019-02-18
 */
public abstract class Polygon extends Shape
{
    /**
     * A list of the lengths of the sides of the polygons.
     */
    protected ArrayList<Double> sideLengths;

    /**
     * Initializes the sideLengths arraylist and sets the Shape's ID.
     *
     * @param id The created shape's name id.
     */
    public Polygon(String id)
    {
        super(id);
        sideLengths = new ArrayList<Double>();
    }

    /**
     * Calculates the perimeter of a polygon. That is, the sum of its side lengths.
     *
     * @return The perimeter of the polygon.
     */
    @Override
	public double getPerimeter()
    {
    	int size = sideLengths.size();
    	int count = 0;
    	double perimeter = 0.0;
    	System.out.println(sideLengths.size());
    	System.out.println(sideLengths.get(1));
    	
    	while (count < size)
    	{
    		perimeter += sideLengths.get(count);
    		++count;
    	}
    	return perimeter;
    	
    }
}
