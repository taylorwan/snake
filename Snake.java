import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//This class controls the movement for the snake and looks for collisions
public class Snake {
		
	private static final int UNIT = 10;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	
	// Head of the snake
	private int x;
	private int y;
	
	// Direction of the snake
	private int xDirection;
	private int yDirection;
	
	// helps prevent the snake from bumping into itself when it tries to
	// turn the opposite direction from the direction it is currently facing 
	private int curDirection;
	private int nextDirection;
	
	// Length of the snake
	private int length;
	
	// snake body parts
	private ArrayList<Point> body;
	
	// Board
	private Board board;

	/** constructor - set the private board data member to the board parameter &
	 * initialize all private data members
	 * 
	 * @param Board
	 * @return none
	 * 
	 */
	public Snake( Board board ) {
		
		// board
		this.board = board;
		
		// current position
		x = 0;
		y = 0;
		if ( board.getLevel() > 1 ) {
			x = UNIT / 2;
			y = UNIT / 2;
		}
		
		// current direction
		xDirection = 1 * UNIT;
		yDirection = 0;
		curDirection = RIGHT;
		nextDirection = RIGHT;
		
		// current length
		length = 1;
		
		// snake body
		body = new ArrayList<Point>();
		body.add( new Point( x, y ) );
		
	}


	/** move - move the snake; 
	 *  level 1: if it hits the sides, it wraps around; 
	 *  level 2: if it hits the sides, game over; 
	 *  level 3: we've got barriers!; 
	 *  level 4: barriers, barriers everywhere; 
	 * 
	 * @param none
	 * @return none
	 * @throws InterruptedException 
	 * 
	 */
	public void move() throws InterruptedException {
		
		// make sure snake doesn't collide with itself
		if ( collision() ) {
			Thread.sleep(1000);
			board.gameOver( "You seem to have collided with yourself. Huh." );
		}
		
		// if we're on level one, the snake can move through walls
		if ( board.getLevel() == 1 ) {
			
			// if the snake hits the left, move it to the right
			if ( yDirection == 0 && x + xDirection < 0 ) {
				x = board.getWidth();
			}
			
			// if the snake hits the right, move it to the left
			else if ( yDirection == 0 && x + xDirection > board.getWidth() - UNIT ) {
				x = -UNIT;
			}
			
			// if the snake hits the top, move it to the bottom
			else if ( xDirection == 0 && y + yDirection <= 0 - UNIT ) {
				y = board.getHeight();
			}
			
			// if the snake hits the bottom, move it to the top
			else if ( xDirection == 0 && y + yDirection >= board.getHeight() ) {
				y = -UNIT;
			}
			
		}
		
		// if we're on higher levels, no more passing through walls!
		else if ( board.getLevel() > 1 ) {
			
			// if snake hits a wall, game over
			if ( x + xDirection < UNIT/2 || x + xDirection > board.getWidth() - UNIT - UNIT/2 ||
			 y + yDirection <= UNIT/2 - UNIT || y + yDirection >= board.getHeight() - UNIT/2 )
				board.gameOver( "You collided with a wall. Oops!" );

			// if snake hits a barrier, game over
			if ( board.getLevel() > 2 && hitBarrier() ) {
				Thread.sleep(1000);
				board.gameOver( "Ouch! Don't hit the barriers." );
			}
		}

		// if all is well, keep mosey-ing along
		x += xDirection;
		y += yDirection;
		curDirection = nextDirection;
		
		// add new location to snake's body
		body.add( new Point( x, y ) );
		
	}
	

	/** paint() - paint the entire snake
	 * 
	 * @param Graphics2D object
	 * @return none
	 * 
	 */
	public void paint(Graphics2D snakeGraphic) {
		for ( int i = 0; i < length; i++ ) {
			snakeGraphic.fillRect( body.get(i).getX(), body.get(i).getY(), UNIT, UNIT );
		}
	}
	

	/** keyPressed - when the key is pressed, if it is a right or left, up or down arrow,
	 * move in the specified direction, unless it's already moving in the direction or exactly
	 * opposite from the specified direction.
	 * 
	 * @param KeyEvent
	 * @return none
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		
		// turn left if the snake isn't already going left or right
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				&& curDirection != LEFT && curDirection != RIGHT ) {
			xDirection = -1 * UNIT;
			yDirection = 0;
			nextDirection = LEFT;
		}
		
		// turn right if the snake isn't already going left or right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				&& curDirection != LEFT && curDirection != RIGHT ) {
			xDirection = 1 * UNIT;
			yDirection = 0;
			nextDirection = RIGHT;
		}
		
		// turn up if the snake isn't already going up or down
		if (e.getKeyCode() == KeyEvent.VK_UP
				&& curDirection != UP && curDirection != DOWN ) {
			yDirection = -1 * UNIT;
			xDirection = 0;
			nextDirection = UP;
		}
		
		// turn down if the snake isn't already going up or down
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				&& curDirection != UP && curDirection != DOWN ) {
			yDirection = 1 * UNIT;
			xDirection = 0;
			nextDirection = DOWN;
		}
	}
	
	
	/** getUnit - return the unit width of the snake
	 * 
	 * @param none
	 * @return unit width
	 * 
	 */
	public int getUnit() {
		return UNIT;

	}
	
	/** getBounds - determine the bounds of the head of the snake
	 * 
	 * @param none
	 * @return bounds of the snake's head
	 * 
	 */
	public Point getBounds() {
		return new Point( x, y );

	}
	
	/** snakeBounds() - determine the bounds of the snake
	 * 
	 * @param none
	 * @return snake
	 * 
	 */
	public ArrayList<Point> snakeBounds() {
		return body;
	}
	
	/** collision - check to see if the Apple and Snake intersect at any point
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	private boolean collision() {
		Point cur = new Point( x+xDirection, y+yDirection );
		
		// true if any part of the snake's body intersects with the new point
		for ( int i = 0; i < length; i++ )
			if ( cur.equals( body.get(i) ) )
				return true;
		
		// if not, return false
		return false;
	}
	
	/** hitBarrier - check to see if we hit a barrier
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public boolean hitBarrier() {
		Point cur = new Point( x+xDirection, y+yDirection );
		ArrayList<Point> barriers = board.barriers.getBarriers();
		// true if any part of the snake's body intersects with the new point
		for ( int i = 0; i < barriers.size(); i++ )
			if ( cur.equals( barriers.get(i) ) )
				return true;
		
		// if not, return false
		return false;
	}
	/** incrementLength - increment the length & add a turn to each existing point in body
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void incrementLength() {
		length++;
	}
	
	/** deleteTail - delete the last visited point from body
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void deleteTail() {
		body.remove(0);
	}
	
	/** getBoard - get the board
	 * 
	 * @param none
	 * @return board
	 * 
	 */
	public Board getBoard() { return board; }
	
	/** getXDirection - get the x direction
	 * 
	 * @param none
	 * @return xDirectin
	 * 
	 */
	public int getXDirection() { return xDirection; }
	
	/** getYDirection - get the y direction
	 * 
	 * @param none
	 * @return yDirection
	 * 
	 */
	public int getYDirection() { return yDirection; }
	
	/** getLength - get the length
	 * 
	 * @param none
	 * @return length
	 * 
	 */
	public int getLength() { return length; }
	
	
	/** getBody - get the body
	 * 
	 * @param none
	 * @return body
	 * 
	 */
	public ArrayList<Point> getBody() { return body; }
	



}