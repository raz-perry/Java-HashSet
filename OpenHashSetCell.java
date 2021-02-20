import java.util.LinkedList;

/**
 * This class instance is a cell in the OpenHashSet hashTable. This cell is a linked list with basic list
 * methods
 */
public class OpenHashSetCell {
	/*
	class attributes
	 */
	private LinkedList<String> list;

	/**
	 * This constructor creates the linked list of this cell
	 */
	OpenHashSetCell(){
		list = new LinkedList<String>();
	}

	/**
	 * This method checks if the value is in the list
	 * @param value to check if in the list
	 * @return true if the value in the list
	 */
	public boolean contains(String value){
		return list.contains(value);
	}

	/**
	 * This method adds the value to the list
	 * @param value to add
	 */
	public void add(String value){
		list.add(value);
	}

	/**
	 * This method removes the value from the list
	 * @param value to remove
	 */
	public void remove(String value){
		list.remove(value);
	}

	/**
	 * @return the list
	 */
	public LinkedList<String> getList() {
		return list;
	}

	/**
	 * @return the size of the list
	 */
	public int size(){
		return list.size();
	}
}
