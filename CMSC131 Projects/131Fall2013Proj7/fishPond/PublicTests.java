package fishPond;

import junit.framework.TestCase;
import cmsc131Utilities.Random131;

/**
 * Here we provide a couple of simple tests so that you can see how you might
 * write them on your own.  We are not grading your JUnit tests for this 
 * project, but it is always a good idea to write them so that you can test
 * out your work.
 * 
 * @author Evan Golub, Fawzi Emad
 *
 */
public class PublicTests extends TestCase {

	public void testTurnFish() {
		Random131.setDeterministic(1027);
		Model m = new Model(6, 6, 0, 0, 0);
		m.addFish(new Fish(1, 1, 2, Fish.LEFT));
		m.addFish(new Fish(3, 3, 2, Fish.DOWN));
		m.turnFish();
		assertEquals(Fish.RIGHT,m.getFish().get(0).getDirection());
		assertEquals(Fish.DOWN,m.getFish().get(1).getDirection());
	}

	public void testMoveFish() {
		Random131.setDeterministic(1027);
		Model m = new Model(5, 5, 0, 0, 0);
		m.addFish(new Fish(1, 1, 2, Fish.LEFT));
		m.turnFish();
		assertEquals(Fish.RIGHT,m.getFish().get(0).getDirection());
		m.addPlant(new Plant(1,2,4));
		m.moveFish();
		assertEquals(6,m.getFish().get(0).getSize());
	}

}
