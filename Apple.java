import java.awt.Graphics2D;
import java.util.ArrayList;

// This class controls the placement of the Apple and looks for collisions
public class Apple {
	
	private static final int UNIT = 10;

	// Coordinates for the Apple
	private int x;
	private int y;

	// Board object - Apple needs to know where it is
	private Board board;

	/** constructor - map the data board data member to the board that
	 * has the Apple.
	 * 
	 * @param Board
	 * @return none
	 * 
	 */
	public Apple(Board board) {
		this.board = board;
		newApple();
	}
	

	/** newApple - find a new location for the apple; for a board with
	 * width n (units), produce a random number between the units of 1 and n-1
	 * @param none
	 * @return none
	 * 
	 */
	public void newApple() {
		int width = board.snake.getUnit();
		int adjustor = width;
		int offset = 0 ;
		
		if ( board.getLevel() > 1 ) {
			adjustor = 2 * width;
			offset = width / 2;
		}
			
		x = (int) ( Math.random() * ( board.getWidth() - adjustor ) / width ) * width + offset;
		y = (int) ( Math.random() * ( board.getHeight() - adjustor ) / width ) * width + offset;
		
	}
	
	/** move - if there's a collision, find a new location for the apple. 
	 * if not, keep the snake moving
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void move() {
		if ( collision() ) {
			
			// generate new apple location
			newApple();
			
			// make sure this new location doesn't collide with current snake
			while ( totalCollision() )
				newApple();
			
			int chances = 3;
			if ( ( board.getLevel() == 3 && (int) ( Math.random() * chances ) % chances == 0 ) ||
					board.getLevel() > 3 ) {
				board.barriers.addBarrier();
			}
		
			// increment score & length
			board.incrementScore();
			board.snake.incrementLength();

		} else {
			// if no collision, get rid of the oldest square visited by the snake
			board.snake.deleteTail();
		}
	}

	/** collision - check to see if the location of the Apple and Snake intersect
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	private boolean collision() {
		return getBounds().equals( board.snake.getBounds() );
	}
	
	/** totalCollision - check to see if the Apple and Snake intersect at any point
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	private boolean totalCollision() {
		Point cur = getBounds();
		ArrayList<Point> snakeBounds = board.snake.snakeBounds();
		
		// true if any part of the snake's body intersects with the apple
		for ( int i = 0; i < snakeBounds.size(); i++ )
			if ( cur.equals( snakeBounds.get(i) ) )
				return true;
		
		// if not, return false
		return false;
	}

	
	/** getBounds() - determine the rectangular boundary of the Apple
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public Point getBounds() {
		return new Point( x, y );
	}
	
	
	/** getBoard - get the board
	 * 
	 * @param none
	 * @return board
	 * 
	 */
	public Board getBoard() { return board; }
	

	/** paint - paint the Apple by filling a circle
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void paint(Graphics2D myApple) {
		myApple.fillOval( x, y, UNIT, UNIT );
	}

}