import java.awt.Graphics2D;
import java.util.ArrayList;

public class Barriers {

	private final static int UNIT = 10;
	
	// list of barriers
	private ArrayList<Point> barriers;
	
	private static Point[] movement = { new Point( 0, 0 ), new Point ( 0, 0 ), new Point( 0, 1 ), new Point( 0, 1 ),
		new Point( 0, 2 ), new Point( 0, 2 ), new Point( 0, 3 ), new Point( 0, 3 ),
		new Point( 0, 2 ), new Point ( 0, 2 ), new Point( 0, 1 ), new Point( 0, 1 ) };
	private int movementIndex = 0;
	
	// Board
	private Board board;
	
	/** constructor - set the private board data member to the board parameter &
	 * initialize all private data members
	 * 
	 * @param Board
	 * @return none
	 * 
	 */
	public Barriers( Board board ) {
		this.board = board;
		barriers = new ArrayList<Point>();
	}
	
	/** addBarrier - add a barrier to our list
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void addBarrier() {
						
		int width = board.snake.getUnit();
		int adjustor = width;
		int offset = 0 ;
		
		if ( board.getLevel() > 1 ) {
			adjustor = 2 * width;
			offset = width / 2;
		}
			
		int x = (int) ( Math.random() * ( board.getWidth() - adjustor ) / width ) * width + offset;
		int y = (int) ( Math.random() * ( board.getHeight() - adjustor ) / width ) * width + offset;
		
		Point newBar = new Point( x, y );
		
		// if this point intersects with current snake, pick a different point
		if ( collision( newBar, board.snake.snakeBounds() ) ) {
			addBarrier();
		} else {
			barriers.add( newBar );
		}
	}
	
	/** collision - check to see if the given point intersects Snake
	 * 
	 * @param none
	 * @return whether they intersect
	 * 
	 */
	public boolean collision( Point cur, ArrayList<Point> bounds ) {
		
		for ( int i = 0; i < bounds.size(); i++ )
			if ( cur.equals( bounds.get(i) ) )
				return true;
		
		return false;
	}
	
	/** move - change the movementIndex
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void move() {
		if ( board.getLevel() == 5 )
			movementIndex = ( movementIndex + 1 ) % movement.length;
	}
	
	/** paint() - paint the barriers. if we are on level 5, they move!
	 * 
	 * @param Graphics2D object
	 * @return none
	 * 
	 */
	public void paint(Graphics2D snakeGraphic) {
		for ( int i = 0; i < barriers.size(); i++ ) {
			
			int x = barriers.get(i).getX();
			int y = barriers.get(i).getY();
			
			if ( board.getLevel() == 5 ) {
				x += movement[movementIndex].getX() * UNIT;
				y += movement[movementIndex].getY() * UNIT;
			}
			
			snakeGraphic.fillRect( x, y, UNIT, UNIT );
		}
	}
	
	/** getBarriers() - return the current barriers
	 * 
	 * @param none
	 * @return barriers
	 * 
	 */
	public ArrayList<Point> getBarriers() { return barriers; }
	
}
