/**
 * This class is HashSet based on closed hashing - jumps by quadratic probing and resizing the table
 * according to the factors.
 */
public class ClosedHashSet extends SimpleHashSet{
	/*
	constants
	 */
	private static final String DEL_VALUE = "EMPTY_CELL";
	private static final String TYPE_CLOSED_HASH_SET = "ClosedHashSet";

	/*
	class attributes
	 */
	private String[] hashTable;

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
	 * factor (0.75) and lower load factor (0.25).
	 */
	public ClosedHashSet(){
		super();
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
	 * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
	 * and lower load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public ClosedHashSet(String[] data){
		super();
		addData(data);
	}

	/*
	This method called from any constructors and init the hash table.
	 */
	protected void createHashTable(){
		hashTable = new String[capacity()];
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
		String[] tempHashTable = new String[newCapacity];
		for (String str:
				hashTable) {
			if (str != null && str != DEL_VALUE){
				for (int i = 0; i < newCapacity; i++){
					int index = (int)(str.hashCode()+(i + Math.pow(i, 2))/2)&(newCapacity-1);
					if (tempHashTable[index] == null){
						tempHashTable[index] = str;
						break;
					}
				}
			}
		}
		hashTable = tempHashTable;
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False if newValue already exists in the set
	 */
	@Override
	public boolean add(String newValue) {
		if (contains(newValue)){
			return false;
		}
		resizeAdd();
		for (int i = 0; i < capacity(); i++){
			int index = clamp((int)(newValue.hashCode()+(i + Math.pow(i, 2))/2));
			if (hashTable[index] == null || (hashTable[index] == DEL_VALUE)){
				hashTable[index] = newValue;
				counter++;
				return true;
			}
		}
		return false;
	}


	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public boolean contains(String searchVal) {
		for (int i = 0; i < hashTable.length; i++){
			int index = clamp((int)(searchVal.hashCode()+(i + Math.pow(i, 2))/2));
			if (hashTable[index] == null){
				return false;
			}
			if (hashTable[index].equals(searchVal) && hashTable[index] != DEL_VALUE){
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True if toDelete is found and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		for (int i = 0; i < hashTable.length; i++){
			int index = clamp((int)(toDelete.hashCode()+(i + Math.pow(i, 2))/2));
			if (hashTable[index] == null){
				return false;
			}
			if (hashTable[index].equals(toDelete) && hashTable[index] != DEL_VALUE){
				hashTable[index] = DEL_VALUE;
				counter--;
				resizeDel();
				return true;
			}
		}
		return false;
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
		return TYPE_CLOSED_HASH_SET;
	}
}
