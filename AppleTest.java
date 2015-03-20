import static org.junit.Assert.*;
import org.junit.Test;

public class AppleTest {


	@Test
	public void testApple() throws InterruptedException {
		Game game = new Game( 1 );
		Apple apple = new Apple( game.getBoard() );
		
		assertNotNull( apple.getBounds() );
		assertNotNull( apple.getBoard() );
	}
	
   @Test
   public void testNewApple() throws InterruptedException {
	   
	   Game game = new Game( 1 );
	   Apple apple = new Apple( game.getBoard() );
	   
	   Point oldXY = apple.getBounds(); 
	   apple.newApple();
	   Point newXY = apple.getBounds();
	   assertNotEquals( oldXY, newXY );
   }

}

