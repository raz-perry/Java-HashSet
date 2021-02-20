import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class holds different types of collections as one object that implements SimpleSet - that is for
 * use any collection with the sets i created as SimpleSet objects.
 */
public class CollectionFacadeSet implements SimpleSet{

	/*
	constants
	 */
	private static final String TYPE_LINKED_LIST = "LinkedList";
	private static final String TYPE_TREE_SET = "TreeSet";
	private static final String TYPE_HASH_SET = "HashSet";

	/*
	class attributes
	 */
	private final Collection<String> myCollection;

	/**
	 * Creates a new facade wrapping the specified collection.
	 * @param collection - The Collection to wrap.
	 */
	public CollectionFacadeSet(Collection<String> collection){
		myCollection = collection;
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False if newValue already exists in the set
	 */
	@Override
	public boolean add(String newValue) {
		return myCollection.add(newValue);
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public boolean contains(String searchVal) {
		return myCollection.contains(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True if toDelete is found and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		return myCollection.remove(toDelete);
	}

	/**
	 * @return The number of elements currently in the set
	 */
	@Override
	public int size() {
		return myCollection.size();
	}

	/**
	 * @return The type of this collection
	 */
	@Override
	public String toString() {
		if (myCollection instanceof LinkedList){
			return TYPE_LINKED_LIST;
		}
		else if (myCollection instanceof TreeSet){
			return TYPE_TREE_SET;
		}
		else if (myCollection instanceof HashSet){
			return TYPE_HASH_SET;
		}
		else {
			return "CollectionFacadeSet{" +
				   "myCollection=" + myCollection +
				   '}';
		}
	}
}
