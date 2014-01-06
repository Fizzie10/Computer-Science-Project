package fishPond;

/**
 * The state of a plant includes it's position (row and column) and it's     
 * size.                                                                     
 * <p>                                                       
 * A Plant grows over time.  Plants are eaten by fish that pass over them.   
 *                                                                        
 * @author Put Your Name Here                                              
 */

public class Plant {

	/** Initial size of plants in beginning of simulation */
	public static final int PLANT_STARTING_SIZE = 150;

	/** Maximum size for a plant */
	public static final int MAX_PLANT_SIZE = 1000;

	/** Portion of plant that is eaten by a fish passing over it */
	public static final int PLANT_BITE_SIZE = 75;

	/* State of this Plant.  YOU MAY NOT ADD ANY FIELDS! */
	private int row, col, size;

	/** Standard constructor that merely initializes the fields from the parameters */
	//Just a standard constructor for plant
	public Plant(int rowIn, int colIn, int size) {
		row = rowIn;
		col = colIn;
		this.size = size;
	}

	/** Standard copy constructor that merely copies the fields */
	// basic Copy constructor for plant
	public Plant(Plant other) {
		row = other.row;
		col = other.col;
		size = other.size;
	}

	/** returns true if size is bigger than zero, false otherwise. */
	//If the size is greater than zero it returns true
	public boolean isAlive() {
		boolean alive = false;
		if(size>0){
			alive = true;
		}
		return alive;
	}

	/** returns row */
	//gets the row coordinates
	public int getRow() {
		return row;
	}

	/** returns column */
	//gets the cols coordinates
	public int getCol() {
		return col;
	}

	/** returns size */
	//gets the size of the plant
	public int getSize() {
		return size;
	}

	/** increments size by one unit */
	//Grows the size by one unit whenever this method is called
	public void grow() {
		size+=1;
	}

	/** decreases size by biteSize units */
	//removes the size by the amount of the bite
	public void removeBite(int biteSize) {
		size-=biteSize;
	}
}
