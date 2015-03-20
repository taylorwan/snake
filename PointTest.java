import static org.junit.Assert.*;
import org.junit.Test;


public class PointTest {
	
	@Test
	public void equalsTest() {
		
		// point a is not equal to point b
		Point a = new Point( 1, 4 );
		Point b = new Point( 5, 1 );
		assertTrue( ! a.equals(b) );
		
		// point a is equal to point c
		Point c = new Point( 1, 4 );
		assertTrue( a.equals(c) );

	}
	
}
