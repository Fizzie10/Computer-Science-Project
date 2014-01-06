package fishPond;

import cmsc131Utilities.Random131;

/**
 * The state of a fish consists of its position in the pond (row and         
 * column), it's size, and the direction in which it is moving (UP, DOWN,    
 * LEFT, or RIGHT).                                                          
 * <p>                                                                 
 * A fish moves, eats other fish, eats plants, and shrinks over time.        
 * <p>                                                                          
 * STUDENTS MAY NOT ADD ANY FIELDS OR PUBLIC METHODS!  (You may add private  
 * methods of your own, if you wish.)                                        
 *                                                                           
 * @author Put Your Name Here          
 */

public class Fish {

	/** Initial size of each fish when simulation begins */
	public static final int FISH_STARTING_SIZE = 100;

	/** Maximum size for a fish */
	public static final int MAX_FISH_SIZE = 1000;

	/** Code for "Left" fish direction */
	public static final int LEFT = 10;

	/** Code for "Right" fish direction */
	public static final int RIGHT = 11;

	/** Code for "Up" fish direction */
	public static final int UP = 12;

	/** Code for "Down" fish direction */
	public static final int DOWN = 13;

	/*State of this fish.  YOU MAY NOT ADD ANY FIELDS! */
	private int row, col, size, direction;

	/** Simply initializes the state of the fish with these parameters */

	//Simple constructor for Fish
	public Fish(int rowIn, int colIn, int size, int direction) {		
		this.row = rowIn;
		this.col = colIn;
		this.size = size;
		this.direction = direction;
	}

	/** Standard copy constructor -- just copies the fields */

	//Simple copy constructor
	public Fish(Fish other) {
		row = other.getRow();
		col = other.getCol();
		size = other.getSize();
		direction = other.getDirection();
	}

	/** Fish size increased by nutritionalValue. */

	//Incerease size by nutritioinalValue whenever this method is called
	public void eat(int nutritionalValue) {
		size+=nutritionalValue;
	}

	/** Returns true if size is greater than zero, false otherwise */

	//if Size is greater than zero, it returns true
	public boolean isAlive() {
		if(size>0){
			return true;
		}
		return false;


	}

	/** Size is decreased by TWO.  */

	//Shrinks size by 2 whenver this method is called
	public void shrink() { 
		size-=2;
	}

	/* This fish eats the other fish.  I.e. This fish's size is increased by
	 * the size of the fish "other".  The size of "other" is set to 0. */

	//adds the size of the other fish and then sets the other fishes size to zero.
	private void eat(Fish other) {
		size+=other.size;
		other.size=0;
	}

	/** Implement this however you want -- it's for your own purposes while debugging */

	//Uses switch statements for different directions then uses the direction in the string
	public String toString() {
		String WhereAmI;
		String facing;
		switch(direction){
		case UP: facing = "up";
		break;
		case DOWN: facing = "down";
		break;
		case LEFT: facing = "left";
		break;
		case RIGHT: facing = "right";
		break;
		default: facing = "somewhere else!";
		break;
		}
		WhereAmI = "Coordinates ("+row+", "+col+") Facing: "+facing+" Size: "+size+"\n";
		return WhereAmI;
	}

	/** The current object battles the parameter (other).  Whichever one is larger
	 * eats the other by calling the private "eat" method.  In cases where the sizes
	 * of the two fish are exactly equal, have the current object win. */

	//makes fish fight, and depending on which is bigger, one fish will eat the other.
	public void fight(Fish other) {
		if(size>=other.size){
			this.eat(other);
		}else{
			other.eat(this);
		}
	}

	/**The fish's location (row or col) is adjusted by ONE unit, depending on the 
	 * fish's current direction.  For example, if the current direction is "UP", then
	 * the fish's row should be decremented.
	 * <p>
	 * If the fish's current direction is not equal to one of the static constants UP, 
	 * DOWN, LEFT, or RIGHT, then this method will throw an 
	 * IllegalFishDirectionException, passing the fish's direction to the constructor. 
	 * 
	 * Students:  You are required to use a switch statement when implementing
	 * this method.
	 */

	//uses switch statements to adjust the coordinates of the fish depending on where it moves!
	public void move() {
		switch(direction){
		case UP: row--;
		break;
		case DOWN: row++;
		break;
		case LEFT: col--;
		break;
		case RIGHT: col++;
		break;
		default: throw new IllegalFishDirectionException(direction);
		}
	}

	/**The fish's direction is randomly determined (UP, DOWN, LEFT or RIGHT).  
	 * Sometimes the resulting direction will be the same as the original one.
	 * <p>
	 * YOU MUST FOLLOW THE INSTRUCTIONS BELOW OR YOU WILL NOT PASS OUR TESTS!
	 * <p>
	 * Call Random131.getRandomInteger(4).
	 * <p>
	 * If the value is 0, set the direction to UP.  If 1, set to DOWN.  If 2, set to 
	 * LEFT.  If 3, set to RIGHT.  IMPORTANT:  DO NOT SET THE DIRECTION TO THE VALUES 
	 * 0, 1, 2, OR 3 -- directions must be set using the static constants (UP, DOWN, 
	 * LEFT, RIGHT). */

	//Uses a random number generator to pick a direction for the fish to move
	public void setRandomDirection() {
		int randomNum = Random131.getRandomInteger(4);
		switch(randomNum){
		case 0: direction = UP;
		break;
		case 1: direction = DOWN;
		break;
		case 2: direction = LEFT;
		break;
		case 3: direction = RIGHT;
		break;
		default: throw new IllegalFishDirectionException(direction);
		}

	}

	/** Returns size */

	//gets the size of a fish
	public int getSize() {
		return size;
	}

	/** Returns row */

	//gets the row coordinate of a fish
	public int getRow() {
		return row;
	}

	/** Returns column */

	//gets the col coordinates of a fish
	public int getCol() {
		return col;
	}

	/** Returns direction (UP, DOWN, LEFT, or RIGHT) */

	//gets the direction of a fish
	public int getDirection() {
		return direction;
	}
}
