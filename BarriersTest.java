import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

@RunWith(value=Parameterized.class)
public class BarriersTest {
	
	//variables
	private Point input;
	private Boolean expected;
	private ArrayList<Point> snake;

	private Barriers barriers; // what we'll use to do our testing
	
	private static ArrayList<Point> theSnake;


   /**
    * BarriersTest()
    * 
    * Constructor. Each test will have 3 values
    * 
    * @param expected
    * @param input
    * @param snake
    * 
    */
   public BarriersTest( Boolean expected, Point input, ArrayList<Point> snake) {
	  this.expected = expected;
      this.input = input;
      this.snake = snake;
   }

   /**
    * getTestParameters()
    * 
    * A method for specifying all of the parameters to test. 
    * For each test, the point, an array of the points that make
    * up the snake, and the expected result are given.
    * 
    * @param none
    * @return a list of parameters
    * 
    */   @Parameterized.Parameters
   public static Collection<Object[]> getTestParameters() {
	   return Arrays.asList(new Object[][] {
    	  { false, new Point(5,6), theSnake },
   		  { true, new Point(0,6), theSnake },
   		  { false, new Point(5,0), theSnake },
   		  { false, new Point(0,10), theSnake },
   		  { true, new Point(0,2), theSnake }
      });
   }

   /**
    * testCollision()
    * 
    * Test collision method
    * @throws InterruptedException 
    * 
    */
   @Test
   public void testCollision() throws InterruptedException {
	   
		Game game = new Game( 1 );
		barriers = new Barriers( game.getBoard() );

		theSnake = new ArrayList<Point>();
		for ( int i = 0; i < 10; i++ )
			theSnake.add( new Point ( 0, i ) );

		System.out.println("Parameterized Number is : " + input);
		assertEquals( expected, barriers.collision( input, snake ) );
	}
}
