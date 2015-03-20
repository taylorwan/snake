/**
 * @author Taylor Wan
 * 
 * This class creates the structure and functions for a Point in either
 * - a ( x, y ) pair, or
 * - an ( x, y, z ) triplet.
 * 
 */

public class Point {

	private int x, y;
	 
	/**
	 * Parameterized constructor
	 * - Creates a point with initial values of ( x, y )
	 * 
	 * @param initialX
	 * @param initialY
	 */
	public Point( int initialX, int initialY ) {
		x = initialX;
		y = initialY;
	}

	/**
	 * Get method for private data member x
	 * 
	 * @return x
	 */	
	public int getX() { return x; }
	
	/**
	 * Get method for private data member y
	 * 
	 * @return y
	 */	
	public int getY() { return y; }

	/**
	 * Set method for x
	 * 
	 * @param newX - new x value
	 */
	public void setX( int newX ) { x = newX; }
	
	/**
	 * Set method for y
	 * 
	 * @param newY - new y value
	 */
	public void setY( int newY ) { y = newY; }
	
	/**
	 * Increments y
	 * 
	 * @param newY - new y value
	 */
	public void incrementY( int unit ) { y += unit; }
	
	
	/**
	 * Defines string representation of a Point
	 * 
	 * @return A string representation of a Point
	 */
	public String toString() {
		
		String output = "( " + getX();
		output += ", " + getY() + " )";
//		output += "\nRemaining Turns: " + turnsLeft;
		
		return output;
	}

	
	/**
	 * Determines whether the current point is equivalent in position to some point b
	 * if both are an ( x, y ) pair
	 * 
	 * @param b - Point b
	 * @return
	 */
	public boolean equals( Point b ) {
		
		// if x coordinates don't match, false
		if ( this.getX() != b.getX() )
			return false;
		
		// if y coordinates don't match, false
		if ( this.getY() != b.getY() )
			return false;
		
		// otherwise true
		return true;
	}

	
	/**
	 * Determines the Euclidean distance between current point and some point b 
	 * for ( x, y ) pairs
	 * 
	 * @param b - Point b
	 * @return Distance between current point and Point b
	 */
	public double euclideanDistance( Point b ) {
		int xValues = this.getX() - b.getX();
		int yValues = this.getY() - b.getY();
		int distance = ( xValues * xValues ) + ( yValues * yValues );

		return Math.sqrt( distance );
	}
	
}