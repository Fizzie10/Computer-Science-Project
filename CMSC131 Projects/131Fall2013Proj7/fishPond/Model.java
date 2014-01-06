package fishPond;

import java.util.*;

import cmsc131Utilities.Random131;

/**
 * Model for the Fish Pond Simulation.   The model consists of a List of Fish,   
 * a List of Plants, and a two dimensional array of boolean values representing 
 * the pond (each element in the array is either ROCK, or WATER.)               
 * <p>                                                               
 * Each time the simulation is re-started a new Model object is created.        
 * <p>                                                                             
 * STUDENTS MAY NOT ADD ANY FIELDS.  ALSO, STUDENTS MAY NOT ADD ANY PUBLIC      
 * METHODS.  (PRIVATE METHODS OF YOUR OWN ARE OKAY.)                            
 *                                                                           
 * @author Fawzi Emad, Put Your Name Here             
 */
public class Model {

	/* State of this Model.  STUDENTS MAY NOT ADD ANY FIELDS! */
	private ArrayList<Fish> fish;
	private ArrayList<Plant> plants;
	private boolean[][] landscape;

	/** Value stored in landscape array to represent water */
	public static final boolean WATER = false;

	/** Value stored in landscape array to represent rock */
	public static final boolean ROCK = true;

	/* Minimum pond dimensions */
	private static final int MIN_POND_ROWS = 5;
	private static final int MIN_POND_COLS = 5;

	/** THIS METHOD HAS BEEN WRITTEN FOR YOU!
	 * <p>
	 * If numRows is smaller than MIN_POND_ROWS, or if numCols is smaller than 
	 * MIN_POND_COLS, then this method will throw an IllegalPondSizeException.
	 * <p>
	 * The fields "rows" and "cols" are initilized with the values of 
	 * parameters numRows and numCols.
	 * <p>
	 * The field "landscape" is initialized as a 2-dimensional array of booleans.  
	 * The size is determined by rows and cols.  Every entry in the landscape array 
	 * is filled with WATER.  The border around the perimeter of the landscape array 
	 * (top, bottom, left, right) is then overwritten with ROCK.
	 * <p>
	 * Random rocks are placed in the pond until the number of rocks (in addition to 
	 * those in the border) reaches numRocks.
	 * <p>
	 * The "plants" ArrayList is instantiated.  Randomly placed Plant objects are put 
	 * into the List.  Their positions are chosen so that they are never above rocks or
	 * in the same position as another plant.  Plants are generated in this way until
	 * the list reaches size numPlants.
	 * <p>
	 * The "fish" ArrayList is instantiated.  Now randomly placed Fish objects are put 
	 * into the List.  Their directions are also randomly selected.  The positions are 
	 * chosen so that they are never above rocks, plants, or other fish.  Fish are 
	 * generated in this way until the list reaches size numFish.
	 * 
	 * @param numRows number of rows for pond
	 * @param numCols number of columns for pond
	 * @param numRocks number of rocks to be drawn in addition to rocks around border 
	 * of pond
	 * @param numFish number of fish to start with
	 * @param numPlants number of plants to start with
	 */
	public Model(int numRows, int numCols, int numRocks, int numFish, int numPlants) {

		if (numRows < MIN_POND_ROWS || numCols < MIN_POND_COLS)
			throw new IllegalPondSizeException(numRows, numCols);

		landscape = new boolean[numRows][numCols];
		plants = new ArrayList<Plant>();
		fish = new ArrayList<Fish>();

		/* Fill landscape with water and a border of rocks around the perimeter */
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++)
				landscape[i][j] = WATER;
			landscape[i][0] = ROCK;
			landscape[i][numCols - 1] = ROCK;
		}
		for (int j = 1; j < numCols - 1; j++) {
			landscape[0][j] = ROCK;
			landscape[numRows - 1][j] = ROCK;
		}

		/* Place random rocks */
		int rocksPlaced = 0;
		while (rocksPlaced < numRocks) {

			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			if (landscape[row][col] == WATER) {
				landscape[row][col] = ROCK;
				rocksPlaced++;
			}
		}

		/* Place random plants */
		for (int i = 0; i < numPlants; i++) {
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			try{
				addPlant(new Plant(row, col, Plant.PLANT_STARTING_SIZE));
			}
			catch(IllegalPlantPositionException e) {
				i--;
			}
		}

		/* Place random fish */
		for (int i = 0; i < numFish; i++) {
			int row = Random131.getRandomInteger(numRows);
			int col = Random131.getRandomInteger(numCols);
			int r = Random131.getRandomInteger(4);
			int dir;
			if (r == 0)                 
				dir = Fish.UP;
			else if (r == 1)
				dir = Fish.DOWN;
			else if (r == 2)
				dir = Fish.LEFT;
			else
				dir = Fish.RIGHT;

			Fish f = new Fish(row, col, Fish.FISH_STARTING_SIZE, dir);
			try {	
				addFish(f);
			}
			catch(IllegalFishPositionException e) {
				i--;
			}
		}
	}

	/** THIS METHOD HAS BEEN WRITTEN FOR YOU.
	 * <p>
	 * When a plant gets bigger than Plant.MAX_PLANT_SIZE, it will explode into
	 * 2 to 9 smaller plants, whose sizes add up to the size of the original plant.
	 * The smaller plants will be placed in the 9 regions of the landscape array
	 * that surround the original plant.  If there are rocks, fish, or other plants 
	 * already occupying these adjacent regions, then fewer than 9 plants are created.  
	 * If there are no available regions nearby, the plant will not explode.  */
	public void plantExplosions() {

		Iterator<Plant> i = plants.iterator();
		while(i.hasNext()) {
			Plant curr = i.next();
			if (curr.getSize() > Plant.MAX_PLANT_SIZE) {
				int count = 0;    // number of available slots for little plants
				boolean[] freeSpace = new boolean[9];  // true if just water in that region

				/* locations of 8 little plants are determined by these offsets to
				 * the coordinates of the plant that is exploding. */
				int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
				int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

				int r = curr.getRow();
				int c = curr.getCol();

				/* Look to see if space is available in all eight directions */
				for (int j = 0; j < 8; j++) {
					freeSpace[j] = isSpaceAvailable(r + dy[j], c + dx[j]);
					if (freeSpace[j])
						count++;
				}

				/* We'll split only if 1 or more spaces are available */
				if (count > 0) {
					i.remove();    // kill off original plant
					int newSize = curr.getSize() / (count + 1);   // truncates!

					/* Add little plants to the list -- iterator is now broken! */
					for (int j = 0; j < 8; j++)
						if (freeSpace[j])
							plants.add(new Plant(r + dy[j], c + dx[j], newSize));

					plants.add(new Plant(r, c, newSize));  // replace original

					/* Since we've modified the List, the original iterator
					 * is no longer useful.  Start iterating from the beginning. */
					i = plants.iterator();
				}	
			}
		}
	}

	/** THIS METHOD HAS BEEN WRITTEN FOR YOU!
	 * <p>
	 * When a fish gets bigger than Fish.MAX_FISH_SIZE, it will explode into
	 * 4 to 8 smaller fish, whose sizes add up to the size of the original fish.
	 * The smaller fish will be placed in the eight regions of the landscape array
	 * surrounding the original fish.  The little fish will be begin moving in
	 * directions that point away from the original location.  (Note that no little 
	 * fish is placed into the original location of the landscape array where the
	 * exploding fish was -- just in the surrounding squares.)  If there are rocks, 
	 * fish, or plants already occupying these adjacent squares, then fewer than 
	 * eight little fish are created. If there are not at least four available 
	 * surrounding squares, then the fish will not explode.*/
	public void fishExplosions() {

		Iterator<Fish> i = fish.iterator();
		while(i.hasNext()) {
			Fish curr = i.next();
			if (curr.getSize() > Fish.MAX_FISH_SIZE) {
				int count = 0;  // number of available squares for little fish
				boolean[] freeSpace = new boolean[8];  // true if just water in that region

				/* locations of the 8 little fish are determined by these offsets
				 * to the coordinates of the original fish that is exploding */
				int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
				int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

				/* directions for the 8 little fish */
				int[] newDir = {Fish.UP, Fish.UP, Fish.RIGHT, Fish.RIGHT, Fish.DOWN, 
						Fish.DOWN, Fish.LEFT, Fish.LEFT};

				int r = curr.getRow();
				int c = curr.getCol();

				/* Look to see if space is available in all directions */
				for (int j = 0; j < 8; j++) {
					freeSpace[j] = isSpaceAvailable(r + dy[j], c + dx[j]);
					if (freeSpace[j])
						count++;
				}

				/* We'll split only if 4 or more spaces are available */
				if (count > 3) {
					i.remove();  // remove original fish from List
					int newSize = curr.getSize() / count;

					/* Add little fish to the list -- iterator is now broken! */
					for (int j = 0; j < 8; j++)
						if (freeSpace[j])
							fish.add(new Fish(r + dy[j], c + dx[j], newSize, newDir[j]));

					/* Since we have modified the List, the original Iterator
					 * is no longer valid.  We'll start iterating again from the
					 * beginning. */
					i = fish.iterator();
				}	
			}
		}
	}

	/* Checks the specified location to see if it has a rock, fish, or plant in it.
	 * If so, returns false; if it is just water, returns true. */

	//Just checks to see if there is anything in the coordinates given
	public boolean isSpaceAvailable(int r, int c) {
		if(landscape[r][c] == ROCK){
			return false;
		}
		for(Fish thisfish:fish){
			if(thisfish.getRow()==r && thisfish.getCol()==c){
				return false;
			}
		}
		for(Plant thisplant:plants){
			if(thisplant.getRow()==r && thisplant.getCol()==c){
				return false;
			}
		}
		return true;
	}

	/** Copy Constructor.
	 * <p>
	 * Since landscape is immutable, it is be copied with just a reference copy.
	 * Fish and Plants are mutable, so they must be copied with a DEEP copy!
	 * (WARNING:  Each fish and each plant must be copied.)
	 */

	//Just a basic copy constructor. Deep copy for fish and plants, reference with landscape.
	public Model(Model other) {
		landscape = new boolean[other.landscape.length][other.landscape[0].length];
		fish = new ArrayList<Fish>();
		plants = new ArrayList<Plant>();
		for(Fish newFish:other.fish){
			fish.add(new Fish(newFish));
		}
		for(Plant newPlant:other.plants){
			plants.add(new Plant(newPlant));
		}
		for(int i=0; i<landscape.length;i++){
			for(int j=0;j<landscape[0].length;j++){
				landscape[i][j]=other.landscape[i][j];
			}
		}

	}

	/** Fish f eats a portion of plant p.  The amount eaten is either 
	 * Plant.PLANT_BITE_SIZE or the current size of the plant, whichever is smaller.  
	 * The fish's size is increased by this amount and the plant's size is decreased 
	 * by this amount. */

	/* Uses if statements to see if the bite size is greater than or equal to the plant size
	 * if it is it will eat the entire plant
	 * if not it takes out a bite size.
	 */
	public static void fishEatPlant(Fish f, Plant p) {
		if(Plant.PLANT_BITE_SIZE>=p.getSize()){
			f.eat(p.getSize());
			p.removeBite(p.getSize());
		}else{
			f.eat(Plant.PLANT_BITE_SIZE);
			p.removeBite(Plant.PLANT_BITE_SIZE);
		}
	}

	/** returns number of rows in landscape array */
	//simple getter
	public int getRows() {
		int numrows = landscape.length;
		return numrows;
	}

	/** returns number of columns in landscape array */
	//simple getter
	public int getCols() {
		int numcols = landscape[0].length;
		return numcols;
	}

	/** Iterates through fish list.  For each fish that isAlive, shrinks the fish by
	 * invoking it's "shrink" method. */
	//Iterates through the list and if the fish is alive it shrinks it
	public void shrinkFish() {
		for(Fish newFish : fish){
			if(newFish.isAlive()){
				newFish.shrink();
			}
		}
	}

	/** Iterates through the plants list, growing each plant by invoking it's "grow"
	 * method. */
	//Iterates through the list and if the plant is alive it grows
	public void growPlants() {
		for(Plant newPlant : plants){
			if(newPlant.isAlive()){
				newPlant.grow();
			}
		}
	}

	/** Iterates through the list of Fish.  Any fish that is no longer alive is removed
	 *  from the list. */
	//Iterates through the list and if the "isAlive" method is false it removes it from the list
	public void removeDeadFish() {
		Iterator<Fish> nextFish = fish.iterator();
		while(nextFish.hasNext()){
			Fish newFish = nextFish.next();
			if(newFish.isAlive() == false){
				nextFish.remove();
				nextFish = fish.iterator();
			}
		}
	}

	/** Iterates through the list of Plants.  Any plant that is no longer alive is removed
	 * from the list. */

	//Iterates through the list of plants and if the plant isn't alive it removes it from the list
	public void removeDeadPlants() {
		Iterator<Plant> nextPlant = plants.iterator();
		while(nextPlant.hasNext()){
			Plant newPlant = nextPlant.next();
			if(newPlant.isAlive() == false){
				nextPlant.remove();
				nextPlant = plants.iterator();
			}
		}
	}

	/**Checks if the fish f is surrounded ON FOUR SIDES (above, below, left, right)
	 * by rocks.  If so, return true.  If there is at least one side without a rock,
	 * then return false. */

	/*Created multiple variables that hold different locations
	 * Then based upon those locations it checks to see if there is any water there
	 * if there is water it returns false
	 */
	private boolean fishIsSurroundedByRocks(Fish f) {
		int row = f.getRow();
		int col = f.getCol();
		int above = row-1;
		int below = row+1;
		int right = col+1;
		int left = col-1;
		if(landscape[above][col] == WATER || landscape[below][col] == WATER || 
				landscape[row][right] == WATER || landscape[row][left] == WATER){
			return false;
		}
		return true;
	}

	/**
	 * Iterate through list of Fish.  FOR EACH FISH THAT IS ALIVE, do the following:
	 * <p>
	 * 1. If this fishIsSurroundedByRocks, DO NOTHING, and move on to the next fish.
	 *     (This fish will not turn.)
	 * <p>
	 * 2.  If this fish's direction is not equal to one of the codes UP, DOWN, LEFT, or
	 *     RIGHT, then throw an IllegalFishDirectionException, passing this fish's 
	 *     direction to the constructor of the IllegalFishDirectionException.
	 * <p>    
	 * 3.  Check whether or not this fish is about to hit a rock if it moves in it's 
	 *     current direction.  If it is about to hit a rock, call the fish's 
	 *     setRandomDirection method.  Repeat this step until the fish is no longer
	 *     about to hit a rock.  Do not make any EXTRA calls to setRandomDirection or 
	 *     you will fail our tests!
	 */

	/* Goes through every fish on the list
	 * if the fish is alive and not surrounded by rocks AND is in a legal direction
	 * and if the fish can't move in a direction it sets a new random direction
	 */
	public void turnFish() {
		for(Fish nextFish : fish){
			if(nextFish.isAlive()){
				if(fishIsSurroundedByRocks(nextFish)==false){
					if(nextFish.getDirection()!=Fish.UP &&
							nextFish.getDirection()!=Fish.DOWN &&
							nextFish.getDirection()!=Fish.LEFT &&
							nextFish.getDirection()!=Fish.RIGHT){
						throw new IllegalFishDirectionException(nextFish.getDirection());
					}
				}
				while(canIMove(nextFish)==false){
					nextFish.setRandomDirection();
				}
			}
		}
	}
	// Method I created. Basically this method checks to see if fish can move anywhere
	private boolean canIMove(Fish a){
		int row = a.getRow();
		int col = a.getCol();
		if(a.getDirection() == Fish.UP){
			if(landscape[row-1][col]==ROCK){
				return false;
			}
		}else if(a.getDirection() == Fish.DOWN){
			if(landscape[row+1][col]==ROCK){
				return false;
			}
		}else if(a.getDirection() == Fish.LEFT){
			if(landscape[row][col-1]){
				return false;
			}
		}else if(a.getDirection() == Fish.RIGHT){
			if(landscape[row][col+1]){
				return false;
			}
		}
		return true;
	}
	/**
	 * Note:  This method assumes that each live fish that is not surrounded by
	 * rocks is already facing a direction where there is no rock!  (Typically the
	 * call to this method should immediately follow a call to "turnFish", which
	 * ensures that these conditions are satisfied.)
	 * <p>
	 * This method iterates through the list of fish.  FOR EACH FISH THAT IS ALIVE,
	 * do the following:
	 * <p>
	 * 1.  Check to see if this fishIsSurroundedByRocks.  If so, DO NOTHING and move
	 *     along to the next fish in the list.  (This fish does not move, does not
	 *     eat, does not fight.)
	 * <p>        
	 * 2.  Move this fish by calling it's "move" method.
	 * <p>
	 * 3.  Check if there is a plant that isAlive and is located in the same position
	 *     as this fish.  If so, have the fish eat part of the plant by calling
	 *     fishEatPlant.
	 * <p>    
	 * 4.  Check if there is another fish (distinct from this fish) that is in the same
	 *     location as this fish.  If so, have the two fish fight each other by calling
	 *     the fight method.  IMPORTANT -- the fight method is not symmetrical.  You 
	 *     must use THIS fish as the current object, and pass the OTHER fish as the 
	 *     parameter (otherwise you will not pass our tests.)
	 */
	/*This method tells a fish to move
	 * if the fish can move it will move to a spot in the water
	 * if there's a plant or fish there it will eat or be eaten
	 */
	public void moveFish() {
		for(Fish nextFish : fish){
			if(nextFish.isAlive()){
				if(fishIsSurroundedByRocks(nextFish)==false){
					nextFish.move();
					for(Plant nextPlant : plants){
						if(nextPlant.isAlive()){
							if(nextFish.getRow()==nextPlant.getRow() && nextFish.getCol()==nextPlant.getCol()){
								fishEatPlant(nextFish,nextPlant);
							}
						}
					}
					for(Fish meanFish : this.fish){
						if((meanFish.isAlive()) && (nextFish != meanFish)){
							if(nextFish.getRow()==meanFish.getRow() && nextFish.getCol()==meanFish.getCol()){
								nextFish.fight(meanFish);
							}
						}
					}
				}
			}
		}
	}

	/** Attempts to add the plant p to plant list, if possible.
	 * <p>
	 * First checks if the landscape in the plant's location is equal to ROCK.  If it 
	 * is, then does not add the plant to the list.  Instead throws an 
	 * IllegalPlantPositionException, passing 
	 * IllegalPlantPositionException.PLANT_OVER_ROCK to the constructor.
	 * <p>
	 * Now checks for another plant (distinct from the parameter) that is in the 
	 * same location as the parameter.  If one is found, then does not add the plant
	 * to the list.  Instead throws an IllegalPlantPositionException,
	 * passing IllegalPlantPositionException.TWO_PLANTS_IN_ONE_PLACE to the 
	 * constructor.
	 * <p>
	 * Otherwise, adds the plant to the list "plants".
	 */

	/* sets variables for the row and col
	 * if there is a rock there it throws an exception
	 * if there is a plant there it throws an exception
	 */
	public void addPlant(Plant p) {
		int row = p.getRow();
		int col = p.getCol();
		if(landscape[row][col]==ROCK){
			throw new IllegalPlantPositionException(IllegalPlantPositionException.PLANT_OVER_ROCK);
		}
		for(Plant newPlant : plants){
			if(newPlant.getRow()==p.getRow()&& newPlant.getCol()==p.getCol()){
				throw new IllegalPlantPositionException(IllegalPlantPositionException.TWO_PLANTS_IN_ONE_PLACE);
			}
		}
		plants.add(p);
	}

	/**Adds the fish f to the fish list, if possible.
	 * <p>
	 * First checks if the landscape in the fish's location is equal to ROCK.  
	 * If it is, then the fish is not added to the list.  Instead, throws an 
	 * IllegalFishPositionException, passing 
	 * IllegalFishPositionException.FISH_OVER_ROCK to the constructor.
	 * <p>
	 * Next checks for another fish (distinct from the parameter) that is in the 
	 * same location as the parameter.  If one is found, then the fish is not
	 * added to the list.  Instead throws an IllegalFishPositionException,
	 * passing IllegalFishPositionException.TWO_FISH_IN_ONE_PLACE to the constructor.
	 * <p>
	 * Otherwise, adds the parameter to the fish list.
	 */
	/* Same concept as the plant method
	 * if there is a rock there it throws an exception
	 * if there is another fis there is throws an exception
	 */
	public void addFish(Fish f){
		int row = f.getRow();
		int col = f.getCol();
		if(landscape[row][col]==ROCK){
			throw new IllegalFishPositionException(IllegalFishPositionException.FISH_OVER_ROCK);
		}
		for(Fish newFish : fish){
			if(newFish.getRow()==row && newFish.getCol()==col){
				throw new IllegalFishPositionException(IllegalFishPositionException.TWO_FISH_IN_ONE_PLACE);
			}
		}
		fish.add(f);
	}

	/** Returns a COPY of the fish list.  Hint:  Use the ArrayList<Fish> copy 
	 * constructor, otherwise you will fail our tests! */
	//returns a simple copy of the fish list
	public ArrayList<Fish> getFish() {
		return new ArrayList<Fish>(fish);
	}

	/** Returns a COPY of the plants list.  Hint:  Use the ArrayList<Plant> 
	 * copy constructor, otherwise you will fail our tests! */ 
	//returns a simple copy of the plant list
	public ArrayList<Plant> getPlants() {
		return new ArrayList<Plant>(plants);
	}

	/** Returns the specified entry of the landscape array (either WATER or ROCK). */
	/*first it runs an if statement to make sure that nothing is out of bounds
	 * if nothing is out of bounds then it just puts in the coordinates into landscape
	 */
	public boolean getShape(int row, int col) {
		if(row<0||row>landscape.length || col<0 || col>landscape.length){
			return false;
		}
		return landscape[row][col];
	}
}
