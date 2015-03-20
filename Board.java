import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private final static int WIDTH = 350;
	private final static int HEIGHT = 350;
		
	private Game game;

	// snake and apples
	Snake snake;
	Apple apple;
	Barriers barriers;
	
	/** Board constructor - Initialize the variables and listeners
	 * 
	 * @param Game object
	 * @return none
	 * @throws InterruptedException 
	 * 
	 */
	public Board(Game game) throws InterruptedException {
		this.game = game;
		
		// Initialize the snake and apple
		snake = new Snake(this);
		apple = new Apple(this);
		barriers = new Barriers(this);
		
	}
		
	/** play - play the game
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void play() throws InterruptedException {
		snake.move();
		apple.move();
		barriers.move();
		repaint();
	}

	/** paint - paints the snake and apple
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	@Override
	public void paint(Graphics theGraphic) {
		Graphics2D the2DGraphic = (Graphics2D) theGraphic;
		the2DGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		the2DGraphic.setColor( new Color( 60, 60, 60 ) );
		snake.paint(the2DGraphic);
		apple.paint(the2DGraphic);
		
		the2DGraphic.setColor( new Color( 150, 150, 150 ) );
		barriers.paint(the2DGraphic);
		
		if ( getLevel() > 1 ) {
			int borderWidth = snake.getUnit() / 2;
			the2DGraphic.fillRect( 0, 0, borderWidth, HEIGHT );
			the2DGraphic.fillRect( 0, 0, HEIGHT, borderWidth );
			the2DGraphic.fillRect( 0, HEIGHT-borderWidth, WIDTH, borderWidth );
			the2DGraphic.fillRect( WIDTH-borderWidth, 0, borderWidth, HEIGHT );
		}
	}

	/** getWidth - return length of snake
	 * 
	 * @param none
	 * @return width
	 * 
	 */
	public int getWidth() { return WIDTH; }
	
	/** getHeight - return length of snake
	 * 
	 * @param none
	 * @return height
	 * 
	 */
	public int getHeight() { return HEIGHT; }

	/** getLevel - get the level
	 * 
	 * @param none
	 * @return level
	 * 
	 */
	public int getLevel() { return game.getLevel(); }

	/** gameOver - Call the gameOver() method of the Game object
	 * 
	 * @param none
	 * @return none
	 * @throws InterruptedException 
	 * 
	 */
	public void gameOver( String reason ) throws InterruptedException {
		game.gameOver( reason );
	}

	/** incrementScore - Call the incrementScore() method of the Game object
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void incrementScore() {
		game.incrementScore();
	}

}
