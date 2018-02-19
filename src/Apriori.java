import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Apriori {

	private static List<List<String>> inputData;
	private static double minSupport, minConfidence;
	
	private static double numUniques; // This is the number of unique items (Useful since we are doing support based on percent)
	
	public static List<String> runApriori(List<List<String>> data, double supportInput, double confidenceInput) {
		inputData = data;
		minSupport = supportInput;
		minConfidence = confidenceInput;
		
		List<KeyValue> candTable;
		List<KeyValue> freqTable;
		// Find all the unique itemSets in the data
		candTable = buildFirstCand(findUniques());
		
		freqTable = buildFreq(candTable);
		
		
		//TODO: Remove this dummy output
		List<String> dummyRules = Arrays.asList("(Support=0.29, Confidence=1.00) { outlook=overcast } ----> { PlayTennis=P }", "(Support=0.29, Confidence=0.67) { temperature=mild } ----> { Humidity=high }");
		return dummyRules;
	}
	
	
	// Calculates the support value on an itemset
	private static double calcSupport(List<Item> itemsChecked) {
		int numSupport = 0;

		for(int r = 0; r < inputData.size(); r++) {
			int amount = itemsChecked.size();
			for(int i = 0; i < itemsChecked.size(); i++) {
				Item curItem = itemsChecked.get(i);
				// Ensures that all the items in the set fit
				if(inputData.get(r).get(curItem.index).equals(curItem.value)) {
					amount--;
				}
			}
			// If everything matches, increase support
			if(amount == 0) 
				numSupport++;
		}
		
		
		
		return numSupport / numUniques;
	}
	
	// Builds the frequency table
	private static List<KeyValue> buildFreq(List<KeyValue> cand) {

		for(int r = cand.size() - 1; r >= 0; r--) {
			if(cand.get(r).support < minSupport) {
				cand.remove(r);
			}
		}
		
		System.out.print("Freq ");
		printTable(cand);
		
		return cand;
	}
	
	// Builds a candidate table
	private static List<KeyValue> buildFirstCand(List<Item> itemSets) {
		List<KeyValue> canTable = new ArrayList<KeyValue>();
		
		// Populating all the items
		for(int i = 0; i < itemSets.size(); i++) {
			List<Item> entry = new ArrayList<Item>();
			entry.add(itemSets.get(i));
 			canTable.add(new KeyValue(entry, calcSupport(entry)));
		}
		
		printTable(canTable);
		
		return canTable;
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
				uniqueItems.add(new Item(c, ls.get(i)));
			}			
		}
				
		
		// TODO: For testing
		for(int i = 0; i < uniqueItems.size(); i ++) {
			System.out.println("Num: " + i + " | Index: " + uniqueItems.get(i).index + " | Value: " + uniqueItems.get(i).value);
		}
		
		numUniques = uniqueItems.size();
		
		return uniqueItems;
	}
	
	// TODO: For testing, prints a table
	public static void printTable(List<KeyValue> tableInfo) {
		System.out.println("Table");
		for(int r = 0; r < tableInfo.size(); r++) {
			for(int c = 0; c < tableInfo.get(r).itemSet.size(); c++) {
				System.out.print("| ");
				System.out.print(tableInfo.get(r).itemSet.get(c).value + "\t| ");
				System.out.println(tableInfo.get(r).support + " |");
			}
		}
	}
} // end of the Apriori class


// A table row in both candidate and frequency tables
class KeyValue {
	List<Item> itemSet;
	double support;
	public KeyValue(List<Item> itemSet, double support) {
		this.itemSet = itemSet;
		this.support = support;
	}
}

// The item and its value
class Item {
	int index;
	String value;
	public Item(int index, String value) {
		this.index = index;
		this.value = value;
	}

	// Checks if this item is the same as one being checked
	public boolean isEqual(Item otherItem) {
		return index == (otherItem.index) && value.equals(otherItem.value);
	}
}
