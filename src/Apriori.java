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
		
		List<Item> uniqueItems;
		List<KeyValue> candTable;
		List<KeyValue> freqTable;
		// Find all the unique itemSets in the data
		uniqueItems = findUniquesInData(data);
		numUniques = uniqueItems.size();
		
		candTable = buildFirstCand(uniqueItems);
		
		freqTable = buildFreq(candTable);
		
		uniqueItems = expandItemSet(freqTable, 2);
		
		//TODO: Remove this dummy output
		List<String> dummyRules = Arrays.asList("(Support=0.29, Confidence=1.00) { outlook=overcast } ----> { PlayTennis=P }", "(Support=0.29, Confidence=0.67) { temperature=mild } ----> { Humidity=high }");
		return dummyRules;
	}
	
	// Expands the itemSets to the nth
	private static List<Item> expandItemSet(List<KeyValue> table, int n) {
		
		List<String> curData = convertTableToData(table);
		List<Item> expandedItemSets = new ArrayList<Item>();
		
		
		return null;
	}
	
	// Converts a table into the same datatype this is read on input
	private static List<String> convertTableToData(List<KeyValue> table) {
		
		List<String> newData = new ArrayList<String>();
		
		for(int r = 0; r < table.size(); r++) {
			for(int c = 0; c < table.get(r).itemSet.size(); c++) {
				newData.add(table.get(r).itemSet.get(c).value);
				System.out.print(newData.get(r));
			}
			System.out.println("");
		}
		
		return newData;
	}
	
	// Calculates the support value on an itemSet
	private static double calcSupport(List<Item> itemsChecked) {
		int numSupport = 0;

		for(int r = 0; r < inputData.size(); r++) {
			int amount = itemsChecked.size();
			for(int i = 0; i < itemsChecked.size(); i++) {
				Item curItem = itemsChecked.get(i);
				// Ensures that all the items in the set fit
				for(int c = 0; c < inputData.get(c).size() - 1; c++) {
					if(inputData.get(r).get(c).equals(curItem.value)) {
						amount--;
					}
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
	private static List<Item> findUniquesInData(List<List<String>> data) {
		List<String> allItems = new ArrayList<String>();
		
		for(int r = 0; r < data.size(); r ++) {
			for(int c = 0; c < data.get(r).size(); c++) {
				allItems.add(data.get(r).get(c));
			}
		}
		
		// This is derived from an example here https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
		Set<String> hs = new HashSet<>();
		hs.addAll(allItems);
		List<String> uniques = new ArrayList<String>();
		uniques.addAll(hs);
		
		List<Item> uniqueItems = new ArrayList<Item>();
		for(int i = 0; i < uniques.size(); i++) {
			uniqueItems.add(new Item(uniques.get(i)));
		}
		
		// TODO: For testing
		for(int i = 0; i < uniqueItems.size(); i ++) {
			System.out.println("Num: " + i + " | Value: " + uniqueItems.get(i).value);
		}
		
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
	String value;
	public Item(String value) {
		this.value = value;
	}
}
