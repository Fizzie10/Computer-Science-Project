package foodManagement;


/**
 * An IMMUTABLE class that represents a list of food and a name.  For example, 
 * a typical entree might be called "Big Bubba Breakfast" and might consist of
 * the list:  2 Egg, 3 Waffle, 1 Bacon, 1 Coffee
 */
public class Entree implements Listable {
	/*
	 * STUDENTS:  You may NOT add any other instance variables!
	 */
	private String name;
	private SortedListOfImmutables foodList;  // This list will contain Food objects
	
	/**
	 * Standard constructor.  To ensure that the Entree class remains immutable,
	 * this constructor will make a copy of the list that foodListIn refers to.
	 * (This is necessary because a SortedListOfImmutables is mutable.)
	 * 
	 * @param nameIn desired name for this Entree
	 * @param foodListIn desired list of Food for this Entree
	 */
	
	//basic constructor for the entree.
	public Entree(String nameIn, SortedListOfImmutables foodListIn) {
		name = nameIn;
		foodList = new SortedListOfImmutables (foodListIn);
	}
	
	/**
	 * Getter for name of Entree
	 * 
	 * @return reference to the name of Entree
	 */
	
	//returns the name.
	public String getName() {
		return name;
	}
	
	/**
	 *  Getter for FoodList for this entree.
	 *  
	 *  @return reference to a copy of the FoodList for this entree
	 */
	
	//creates a new foodlist and returns it.
	public SortedListOfImmutables getFoodList() {
		return new SortedListOfImmutables (foodList);
	}
	
	/**
	 * Returns the wholesale cost of the food in this entree
	 * 
	 * @return wholesale cost of the food in this entree
	 */
	
	//uses the wholesale cost method to get the price of the foodlist.
	public int getWholesaleCost() {
		return foodList.getWholesaleCost();
	}
	
	/**
	 * Returns the retail value of the food in this entree
	 * 
	 * @return retail value of the food in this entree
	 */
	
	//Uses the retail value method to get the retail value of the foodlist.
	public int getRetailValue() {
		return foodList.getRetailValue();
	}
	
	/**
	 * Compares the current object to the parameter and returns true if they
	 * have the same name.
	 * 
	 * @param other Entree to be compared to the current object
	 * @return true if the current object and the parameter have the same name, 
	 * false otherwise
	 */
	
	//uses the ".equals" method to see if the paramaters and current object are the same.
	public boolean equals(Entree other) {
		return name.equals(other.name);
	}
	
	/* We'll give you this one for free.  Do not modify this method or you will
	 * fail our tests!
	 */
	public String toString() {
		String retValue = "< ";
		for (int i = 0; i < foodList.getSize(); i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += foodList.get(i);
		}
		retValue += " >";
		return retValue;
	}
}
