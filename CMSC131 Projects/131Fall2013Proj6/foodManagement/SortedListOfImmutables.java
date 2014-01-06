package foodManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	/*
	 * STUDENTS:  You may NOT add any other instance variables to this class!
	 */
	private Listable[] items;


	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	//Creates a constructor called items which is an empty array
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	
	//A reference copy constructor
	public SortedListOfImmutables(SortedListOfImmutables other) {
		items = new Listable[other.getSize()];
		for(int i=0; i<items.length; i++){
			items[i] = other.items[i];
		}

	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	
	//returns the size of the items array
	public int getSize() {
		return items.length;
	}

	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	
	//returns the items at spot "i"
	public Listable get(int i) {
		return items[i];
	}

	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 * 
	 * In order to accomodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	
	/*add method. uses a for loop that uses the string "compareTo" method to to place the new item in alphabetically
	 *it has a counter variable that increases along with the for loop so that the new item can be added in.
	 *once the new item is in the correct alphabetical spot, the if statement slides it in and then moves the array back one
	 *it also has a checker that is an int, the purpose of this is to make sure something is even being added
	 */
	public void add(Listable itemToAdd) {
		Listable [] newitems = new Listable[items.length+1];
		int counter = 0;
		int checker = 1;
		for(int i=0; i<items.length;i++){
			if(checker == 1 && itemToAdd.getName().compareTo(items[i].getName())<0 ){
				newitems[counter] = itemToAdd;
				counter++;
				i--;
				checker = 2;
			}
			else{
				newitems[counter] = items[i];
				counter++;
			}
		}
		if(checker == 1){
			newitems[newitems.length-1]=itemToAdd;
			checker = 2;
		}
		items=newitems;
	}

	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	
	//Goes though the listToAdd and uses the add method on each item in the list.
	public void add(SortedListOfImmutables listToAdd) {
		for(int i=0; i<listToAdd.getSize();i++){
			add((listToAdd.get(i)));
		}
	}

	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	
	/*THe remove method has a variable that holds the spot of the item that is being removed
	 * the forloop goes through and finds the item that it wants to remove.
	 * once the spot of that item is found, the "remove spot variable" takes the value of that spot
	 * then two for loops are created with the new array completely skipping the "removespot" point, then the loop is broken.
	 */
	public void remove(Listable itemToRemove) {
		Listable[] newItem = new Listable[items.length-1];
		int removespot = 0;
		for(int i = 0; i<items.length; i++){
			if(items[i].getName().equals(itemToRemove.getName())){
				removespot = i;
				for(int a=0; a<removespot; a++){
					newItem[a] = items[a];
				}
				for(int b=removespot+1; b<items.length; b++){
					newItem[b-1] = items[b];
				}
				items = newItem;
				break;
			}
		}
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	
	//Goes through every item on the list that needs to be removed and uses the remove method on them.
	public void remove(SortedListOfImmutables listToRemove) {
		for(int i=0; i<listToRemove.getSize();i++){
			remove(listToRemove.get(i));
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	
	//creates a forloop that goes through and gets the value of each and every item and adds them.
	public int getWholesaleCost() {
		int totalcost;
		totalcost=0;
		for(int i=0; i<items.length;i++){
			totalcost+=items[i].getWholesaleCost();
		}
		return totalcost;
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	
	//for loop goes through and gets the retail value of every single item and adds them together.
	public int getRetailValue() {
		int totalvalue;
		totalvalue = 0;
		for(int i=0; i<items.length;i++){
			totalvalue+= items[i].getRetailValue();
		}
		return totalvalue;
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	
	//goes through a forloop in the "items" array and checks to see if the "itemToFind" is there.
	public boolean checkAvailability(Listable itemToFind) {
		for(int i=0; i<items.length; i++){
			if(items[i].getName().equals(itemToFind.getName())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	
	/*goes through every item in the list and checks in availability, if something's not available it's false
	 * then, in the event that there are doubles, it makes sure that the list to check size is smaller than
	 * or equals to the amount of items found.
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		int counter=0;
		for(int i=0; i<listToCheck.getSize(); i++){
			for(int k=0; k<items.length; k++){
				if(checkAvailability(listToCheck.get(i))==false){
					return false;
				}else if(listToCheck.items[i].getName().equals(items[k].getName())){
					counter++;
				}
			}
		}
			if(counter<listToCheck.getSize()){
				return false;
			}
			return true;
		}
	

	/*
	 * We'll give you this one for free.  Do not modify this method or you
	 * will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
