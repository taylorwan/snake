import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	private final static int barHeight = 14;
	private final static int scoreHeight = 20;
	private static int SPEED;
	
	private Board board;
	private int score;
	private int level;
	
	private JLabel scoreKeeper;
	
	public Game() throws InterruptedException {

		// Start score & level
		score = 0;
		level = 1;

		// create board
		board = new Board( this );

		// listeners
		initListeners();

		// Setup GUI
		initGUI();

		// level picker
		initLevel();

		// create board
		initBoard();

	}
	
	/** constructor for testing 
	 * 
	 * @param none
	 * @return none
	 * @throws InterruptedException 
	 * 
	 */
	public Game( int level ) throws InterruptedException {
		this.level = level;
		board = new Board( this );
	}
	
	public void initListeners() {
		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				board.snake.keyPressed(e);
			}
		};
		addKeyListener(listener);
		
		// set the focus to the focused component
		setFocusable(true);

	}
	
	/** initGUI - Initialize the GUI
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void initGUI() throws InterruptedException {

		// Setup the game JFrame
		setSize( board.getWidth(), board.getHeight() + barHeight + scoreHeight );
		setVisible( true );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	
	/** initLevel - choose a level
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void initLevel() {
		Object[] options = { "Level 5", "Level 4", "Level 3", "Level 2", "Level 1" };
		int userChoice = JOptionPane.showOptionDialog( this, "Choose Your Level", "Welcome to Snake!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null );
		level = 5 - userChoice;


		// set level speeds
		if ( this.level == 1 )
			SPEED = 150;
		if ( this.level == 2 )
			SPEED = 125;
		else
			SPEED = 100;
		
	}

	/** initBoard - add board & start the game
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void initBoard() throws InterruptedException {

		board = new Board( this );
		setLayout( new BorderLayout() );

		scoreKeeper = new JLabel( "Score: 0", SwingConstants.CENTER );
		scoreKeeper.setOpaque( true );
		scoreKeeper.setSize(board.getWidth(), scoreHeight );
		scoreKeeper.setBackground( new Color( 150, 150, 150 ) );
		scoreKeeper.setForeground( Color.WHITE );
		add( scoreKeeper, BorderLayout.SOUTH );
		
		// countdown
		JLabel readyGo = new JLabel( ".", SwingConstants.CENTER );
		readyGo.setFont( new Font( "Arial", 0, 36 ) );
		readyGo.setFocusable( false );
		readyGo.setForeground( new Color( 150, 150, 150 ) );
		add( readyGo, BorderLayout.CENTER );

		Thread.sleep(100);

		readyGo.setText( "Ready?");
		Thread.sleep(1000);

		readyGo.setText( "Set...");
		Thread.sleep(1000);

		readyGo.setText( "Go!");
		Thread.sleep(1000);

		// remove countdown
		remove( readyGo );

		// create board
		add( board, BorderLayout.CENTER );
		setVisible( true );
		
		Thread.sleep(100);

		// Play the game
		while(true) {
			board.play();
			repaint();
			
			Thread.sleep( SPEED );
		}
		
	}

	/** getLevel - get the level
	 * 
	 * @param none
	 * @return level
	 * 
	 */
	public int getLevel() { return level; }
	
	/** getBoard - get the board
	 * 
	 * @param none
	 * @return board
	 * 
	 */
	public Board getBoard() { return board; }
	
	/** gameOver - When the game comes to an end, have a popup that says it is over and
	 * shows the score before exiting.
	 * 
	 * @param none
	 * @return none
	 * @throws InterruptedException 
	 * 
	 */
	public void gameOver( String reason ) throws InterruptedException {
		String message = "Game Over!\n";
		message += reason;
		message +="\nScore = "+score;
		
		Object[] options = { "Replay", "Quit" };
		int userChoice = JOptionPane.showOptionDialog( this, message, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0] );
		
		if ( userChoice == 0 ) {
			new Game();
		}
		else {
			System.exit( ABORT );
		}
	}
	
	/** incrementScore - Increase the score by 1
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void incrementScore() {
		score++;
		scoreKeeper.setText( "Score: " + score );
	}
	
}