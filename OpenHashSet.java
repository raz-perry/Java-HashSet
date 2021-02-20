/**
 * This class is HashSet based on open hashing - if there is a collusion the value added to the linkedList at
 * that index.
 */
public class OpenHashSet extends SimpleHashSet{

	/*
	constants
	 */
	private static final String TYPE_OPEN_HASH_SET = "OpenHashSet";

	/*
	class attributes
	 */
	private OpenHashSetCell[] hashTable;

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
	 * factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet(){
		super();
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
	 * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
	 * and lower load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public OpenHashSet(String[] data){
		super();
		addData(data);
	}

	/*
	This method called from any constructors and init the hash table.
	 */
	protected void createHashTable(){
		hashTable = new OpenHashSetCell[capacity()];
	}

	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	@Override
	public int capacity() {
		if (hashTable == null){
			return super.capacity();
		}
		return hashTable.length;
	}

	/*
	This method create a new empty table in the asked size and moving each value from the hash table to
	that one by using hash function again (according to the new size).
	 */
	protected void copyHashTable(int newCapacity){
		OpenHashSetCell[] tempHashTable = new OpenHashSetCell[newCapacity];
		int index;
		for (OpenHashSetCell cell:
				hashTable) {
			if (cell != null){
				for (String value:
						cell.getList()) {
					index = value.hashCode()&(newCapacity-1);
					if (tempHashTable[index] == null){
						tempHashTable[index] = new OpenHashSetCell();
					}
					tempHashTable[index].add(value);
				}
			}
		}
		hashTable = tempHashTable;
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue - New value to add to the set
	 * @return False if newValue already exists in the set
	 */
	@Override
	public boolean add(String newValue) {
		if (contains(newValue)){
			return false;
		}
		resizeAdd();
		int index = clamp(newValue.hashCode());
		if (hashTable[index] == null){
			hashTable[index] = new OpenHashSetCell();
		}
		hashTable[index].add(newValue);
		counter++;
		return true;
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal - Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public boolean contains(String searchVal) {
		int index = clamp(searchVal.hashCode());
		if (hashTable[index] == null){
			return false;
		}
		return hashTable[index].contains(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 * @param toDelete - Value to delete
	 * @return True if toDelete is found and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		if (!contains(toDelete)){
			return false;
		}
		int index = clamp(toDelete.hashCode());
		if (hashTable[index].size() == 1){
			hashTable[index] = null;
		}
		else {
			hashTable[index].remove(toDelete);
		}
		counter--;
		resizeDel();
		return true;
	}

	/**
	 * @return The number of elements currently in the set
	 */
	@Override
	public int size() {
		return counter;
	}

	/**
	 * @return The type of this set
	 */
	@Override
	public String toString() {
		return TYPE_OPEN_HASH_SET;
	}
}
