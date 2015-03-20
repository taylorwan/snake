import static org.junit.Assert.*;
import org.junit.Test;


public class SnakeTest {

	@Test
	public void testSnake() throws InterruptedException {
		Game game = new Game( 1 );
		Snake snake = new Snake( game.getBoard() );
		
		assertNotNull( snake.getBoard() );
		Point snakeHead = snake.getBounds();
		assertEquals( snakeHead.getX(), 0, 0 );
		assertEquals( snakeHead.getY(), 0, 0 );
		assertEquals( snakeHead.getX(), 0, 0 );
		assertEquals( snake.getXDirection(), snake.getUnit(), 0 );
		assertEquals( snake.getYDirection(), 0, 0 );
		
		assertEquals( snake.getLength(), 1, 0 );
		assertEquals( snake.getBody().size(), 1, 0 );
		assertNotNull( snake.getBody() );
		
	}

}
