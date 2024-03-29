import static org.junit.Assert.*;

import org.junit.Test;


public class PublicTests{
	
	@Test
	public void testBasicConstructorsAndGetters() {
	
		MyDouble a = new MyDouble(0), b = new MyDouble(0);
		MyDouble d = new MyDouble(555.729);
		
		ComplexNumber x = new ComplexNumber(a,b);
		System.out.println(x.toString());
		assertTrue(x.getReal().compareTo(a) == 0 && 
				x.getImag().compareTo(b) == 0);
		
		ComplexNumber z = new ComplexNumber(d);
		assertTrue(z.getReal().compareTo(d) == 0 && 
				z.getImag().compareTo(new MyDouble(0)) == 0);
	}
	
	@Test
	public void testCopyConstructor() {
		
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);
		
		ComplexNumber x = new ComplexNumber(a, b);
		ComplexNumber y = new ComplexNumber(x);
		assertTrue(x != y);     // Check to be sure they are not aliased!
		assertTrue(y.getReal().compareTo(a) == 0 && 
				y.getImag().compareTo(b) == 0);
	}
}