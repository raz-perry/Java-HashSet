/**
 * This class is an abstract class implementing SimpleSet
 */
public abstract class SimpleHashSet implements SimpleSet{
	protected static float DEFAULT_HIGHER_CAPACITY = (float)0.75; // Describes the higher load factor of a
	// newly created hash set.
	protected static float DEFAULT_LOWER_CAPACITY = (float)0.25; // Describes the lower load factor of a newly
	// created hash set.
	protected static int INITIAL_CAPACITY = 16; // Describes the capacity of a newly created hash set.

	// class attributes
	private final float lowerFactor;
	private final float upperFactor;
	private final int init_capacity;
	protected int counter = 0; // count of items in the hash table

	/**
	 * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
	 * DEFAULT_HIGHER_CAPACITY.
	 */
	protected SimpleHashSet(){
		lowerFactor = DEFAULT_LOWER_CAPACITY;
		upperFactor = DEFAULT_HIGHER_CAPACITY;
		init_capacity = INITIAL_CAPACITY;
		createHashTable();
	}

	/**
	 * Constructs a new hash set with capacity INITIAL_CAPACITY.
	 * @param upperLoadFactor - the upper load factor before rehashing
	 * @param lowerLoadFactor - the lower load factor before rehashing
	 */
	protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
		lowerFactor = lowerLoadFactor;
		upperFactor = upperLoadFactor;
		init_capacity = INITIAL_CAPACITY;
		createHashTable();
	}

	/**
	 * This method adds data array to the hash table
	 * @param data array to add
	 */
	protected void addData(String[] data){
		for (String str:
			 data) {
			add(str);
		}
	}

	/**
	 * @return the current capacity (number of cells) of the table
	 */
	public int capacity(){
		return init_capacity;
	}

	/**
	 * Clamps hashing indices to fit within the current table capacity (see the exercise description for
	 * details)
	 * @param index - the index before clamping
	 * @return an index properly clamped
	 */
	protected int clamp(int index){
		return index&(capacity()-1);
	}

	/**
	 * @return the lower load factor of the table
	 */
	protected float getLowerLoadFactor(){
		return lowerFactor;
	}

	/**
	 * @return the higher load factor of the table
	 */
	protected float getUpperLoadFactor(){
		return upperFactor;
	}

	/*
	This method is an abstract method that should create a new empty table in the asked size and moving
	each value from the hash table to that one by using hash function again (according to the new size).
	 */
	protected abstract void copyHashTable(int newCapacity);

	/*
	This method checks if the hash table should be increase. If so it resizes the table by using
	copyHashTable method.
	 */
	protected void resizeAdd(){
		if ((int)(getUpperLoadFactor()*capacity()) <= size()){
			copyHashTable(capacity()*2);
		}
	}

	/*
	This method checks if the hash table should be decrease. If so it resizes the table by using
	copyHashTable method.
	 */
	protected void resizeDel(){
		if ((int)(getLowerLoadFactor()*capacity()) > size() && capacity() > 1){
			copyHashTable(capacity()/2);
		}
	}

	/*
	This method is an abstract method that should create the hash table of each inherited hash set object
	 */
	protected abstract void createHashTable();
}
