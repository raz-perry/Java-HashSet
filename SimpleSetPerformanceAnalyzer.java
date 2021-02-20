import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class analyzes sets and linked list performance like adding values and checking contains.
 */
public class SimpleSetPerformanceAnalyzer {
	/*
	constants
	 */
	private static final String DATA1_FILENAME = "data1.txt";
	private static final String DATA2_FILENAME = "data2.txt";
	private static final int SETS_AMOUNT = 5;
	private static final long ITERATION_GENERAL = 70000;
	private static final long ITERATION_LINKED_LIST = 7000;
	private static final long TO_MILLISECONDS = 1000000;
	private static final String CONTAINS_DIF_DATA1 = "hi";
	private static final String CONTAINS_SAME_DATA1 = "-13170890158";
	private static final String CONTAINS_IN_DATA2 = "23";
	private static final String CONTAINS_DIF_DATA2 = "hi";
	private static final String TYPE_LINKED_LIST = "LinkedList";

	/*
	class attributes
	 */
	private final String[] data; // array data to analyze
	private final SimpleSet[] lists = new SimpleSet[SETS_AMOUNT]; // lists and sets to analyze
	private long timeBefore; // start timer

	/**
	 * This constructor reads the data file and save it in the attribute.
	 * @param fileName - the name of the data file to read
	 */
	public SimpleSetPerformanceAnalyzer(String fileName){
		data = Ex4Utils.file2array(fileName);
	}

	/*
	This method creates the lists with default constructor
	 */
	private void createDefaultLists(){
		lists[0] = new OpenHashSet();
		lists[1] = new ClosedHashSet();
		lists[2] = new CollectionFacadeSet(new TreeSet<String>());
		lists[3] = new CollectionFacadeSet(new LinkedList<String>());
		lists[4] = new CollectionFacadeSet(new HashSet<String>());
	}

	/*
	This method goes over each list and add the data, each word one by one, and analyze the time it took to
	each data structure.
	 */
	private void addCount() {
		for (SimpleSet list:
				lists) {
			System.out.println("Start " + list);
			timeBefore = System.nanoTime();
			for (int i = 0; i < data.length; i++){
				if (i%20000 == 0){
					System.out.println(i / 1000 + "%");
				}
				list.add(data[i]);
			}
			long differenceMilliSeconds = (System.nanoTime() - timeBefore) / TO_MILLISECONDS;
			System.out.println(list + ": " + differenceMilliSeconds);
		}
	}

	/*
	This method goes over each list and checks if the input value is in it and analyzes the time it took to
	each data structure.
	 */
	private void containsCheck(String searchValue) {
		for (SimpleSet list:
				lists) {
			System.out.println("Start " + list);
			long differenceNanoSeconds;
			if (list.toString().equals(TYPE_LINKED_LIST)){
				timeBefore = System.nanoTime();
				for (int i = 0; i < ITERATION_LINKED_LIST; i++){
					if (i%1400 == 0){
						System.out.println(i / 70 + "%");
					}
					list.contains(searchValue);
				}
				differenceNanoSeconds = (System.nanoTime() - timeBefore) / ITERATION_LINKED_LIST;
			}
			else {
				System.out.println("warming up");
				for (int i = 0; i < ITERATION_GENERAL; i++){
					if (i%14000 == 0){
						System.out.println(i / 700 + "%");
					}
					list.contains(searchValue);
				}
				System.out.println("start measuring");
				timeBefore = System.nanoTime();
				for (int i = 0; i < ITERATION_GENERAL; i++){
					if (i%14000 == 0){
						System.out.println(i / 700 + "%");
					}
					list.contains(searchValue);
				}
				differenceNanoSeconds = (System.nanoTime() - timeBefore) / ITERATION_GENERAL;
			}
			System.out.println(list + ": " + differenceNanoSeconds);
		}
	}

	/**
	 * This is the main driver which runs the analyze. It creates analyze object for each data file, first
	 * add the data one by one (tests 1+2) and after make the contains checks by order.
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleSetPerformanceAnalyzer analyzerData1 = new SimpleSetPerformanceAnalyzer(DATA1_FILENAME);
		SimpleSetPerformanceAnalyzer analyzerData2 = new SimpleSetPerformanceAnalyzer(DATA2_FILENAME);
		analyzerData1.createDefaultLists();
		analyzerData2.createDefaultLists();
		System.out.println("Start adding data1");
		analyzerData1.addCount();
		System.out.println("Start adding data2");
		analyzerData2.addCount();
		System.out.println("Start contains check - hi in data1");
		analyzerData1.containsCheck(CONTAINS_DIF_DATA1);
		System.out.println("Start contains check -13170890158 in data1");
		analyzerData1.containsCheck(CONTAINS_SAME_DATA1);
		System.out.println("Start contains check - 23 in data2");
		analyzerData2.containsCheck(CONTAINS_IN_DATA2);
		System.out.println("Start contains check - hi in data2");
		analyzerData2.containsCheck(CONTAINS_DIF_DATA2);
	}
}
