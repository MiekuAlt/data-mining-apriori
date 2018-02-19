import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Apriori {

	private static List<List<String>> inputData;
	private static double minSupport, minConfidence;
	
	public static List<String> runApriori(List<List<String>> data, double supportInput, double confidenceInput) {
		inputData = data;
		minSupport = supportInput;
		minConfidence = confidenceInput;
		
		// Find all the unique itemsets in the data
		findUniques();
		
		
		
		
		//TODO: Remove this dummy output
		List<String> dummyRules = Arrays.asList("(Support=0.29, Confidence=1.00) { outlook=overcast } ----> { PlayTennis=P }", "(Support=0.29, Confidence=0.67) { temperature=mild } ----> { Humidity=high }");
		return dummyRules;
	}
	
	// Finds all of the unique items in the data
	private static List<Item> findUniques() {
		
		List<Item> uniqueItems = new ArrayList<Item>();
		
		// Grabbing all the possible items
		List<List<String>> invertedTable = new ArrayList<List<String>>(); // the original is row, col. This is col, row
		// Initializing each column
		for(int c = 0; c <inputData.get(0).size(); c++) {
			invertedTable.add(new ArrayList<String>());
		}
		// Filling the columns with all possible values
		for(int r = 1; r < inputData.size(); r ++) {
			for(int c = 0; c < inputData.get(r).size(); c++) { // Skipping first row, since they are the names
				invertedTable.get(c).add(inputData.get(r).get(c));
			}
		}
		// Removing duplicates from each column
		for(int c = 0; c < invertedTable.size(); c++) {
			Set<String> hs = new HashSet<>();
			hs.addAll(invertedTable.get(c));
			List<String> ls = new ArrayList<String>();
			ls.addAll(hs);
			
			// Inserting the unique entries
			for(int i = 0; i < hs.size(); i++) {
				uniqueItems.add(new Item(inputData.get(0).get(c), ls.get(i)));
			}			
		}
				
		
		// TODO: For testing
		for(int i = 0; i < uniqueItems.size(); i ++) {
			System.out.println("Num: " + i + " | Name: " + uniqueItems.get(i).name + " | Value: " + uniqueItems.get(i).value);
		}
		
		
		return null;
	}
	
} // end of the Apriori class


// A table row in both candidate and frequency tables
class KeyValue {
	List<Item> itemSet;
	double support;
}

// The item and its value
class Item {
	String name, value;
	public Item(String name, String value) {
		this.name = name;
		this.value = value;
	}

	// Checks if this item is the same as one being checked
	public boolean isEqual(Item otherItem) {
		return name.equals(otherItem.name) && value.equals(otherItem.value);
	}
}
