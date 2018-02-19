import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class Apriori {

	private static List<List<String>> inputData;
	private static double minSupport, minConfidence;
	
	public static List<String> runApriori(List<List<String>> data, double supportInput, double confidenceInput) {
		inputData = data;
		minSupport = supportInput;
		minConfidence = confidenceInput;
		
		List<KeyValue> freqTable = genTables();
		
		printTable(freqTable, "Frequency"); // TODO: Remove me!
		
		// Association aspect of the algorithm
		runAssociation(freqTable);
		
		
		//TODO: Remove this dummy output
		List<String> dummyRules = Arrays.asList("(Support=0.29, Confidence=1.00) { outlook=overcast } ----> { PlayTennis=P }", "(Support=0.29, Confidence=0.67) { temperature=mild } ----> { Humidity=high }");
		return dummyRules;
	}
	
	// Begins the Association aspect of the algorithm
	private static void runAssociation(List<KeyValue> freqTable) {
		
	}
	
	// Generates the tables until there are none left
	private static List<KeyValue> genTables() {
		
		List<List<String>> curDataSets;
		List<Item> uniqueItems;
		List<KeyValue> candTable;
		List<KeyValue> freqTable;
		
		uniqueItems = findUniquesInData(inputData);
		candTable = buildFirstCand(uniqueItems);
		freqTable = buildFreq(candTable);
		
		List<KeyValue> prevFreqTable = new ArrayList<KeyValue>();
		
		int iteration = 2;
		while(!freqTable.isEmpty()) {
			prevFreqTable = freqTable;
			curDataSets = expandItemSet(freqTable, iteration);
			candTable = buildCand(curDataSets);
			freqTable = buildFreq(candTable);
			iteration++;
		}
		
		return prevFreqTable;
	}
	
	// Expands the itemSets to the nth based on an example from https://stackoverflow.com/questions/5162254/all-possible-combinations-of-an-array
	private static List<List<String>> expandItemSet(List<KeyValue> table, int n) {
		
		List<String> curData = convertTableToData(table);
		List<List<String>> expandSet = new LinkedList<List<String>>();

	    expandSet.addAll(combination(curData, n));
		return expandSet;
	}
	
	// This is based on an example from https://stackoverflow.com/questions/5162254/all-possible-combinations-of-an-array
	public static <T> List<List<T>> combination(List<T> values, int size) {

	    if (0 == size) {
	        return Collections.singletonList(Collections.<T> emptyList());
	    }

	    if (values.isEmpty()) {
	        return Collections.emptyList();
	    }

	    List<List<T>> combination = new LinkedList<List<T>>();
	    T actual = values.iterator().next();
	    List<T> subSet = new LinkedList<T>(values);
	    subSet.remove(actual);
	    List<List<T>> subSetCombination = combination(subSet, size - 1);

	    for (List<T> set : subSetCombination) {
	        List<T> newSet = new LinkedList<T>(set);
	        newSet.add(0, actual);
	        combination.add(newSet);
	    }

	    combination.addAll(combination(subSet, size));
	    return combination;
	}
	// Converts a table into the same dataType this is read on input
	private static List<String> convertTableToData(List<KeyValue> table) {
		
		List<String> newData = new ArrayList<String>();
		
		for(int r = 0; r < table.size(); r++) {
			for(int c = 0; c < table.get(r).itemSet.size(); c++) {
				newData.add(table.get(r).itemSet.get(c).value);
			}
		}	
		
		return removeDups(newData);
	}
	
	// Calculates the support value on an itemSet
	private static double calcSupport(List<Item> itemsChecked) {
		int numSupport = 0;

		for(int r = 0; r < inputData.size(); r++ ) {
			int numCheck = itemsChecked.size();
			for(int i = 0; i < itemsChecked.size(); i++) {
				for(int c = 0; c < inputData.get(r).size(); c++) {
					if(itemsChecked.get(i).value.equals(inputData.get(r).get(c))) {
						numCheck--;
					}
				}
				if(numCheck == 0) {
					numSupport++;
				}
			}
		}
		
		return numSupport / ((double)inputData.size());
	}
	
	// Builds the frequency table
	private static List<KeyValue> buildFreq(List<KeyValue> cand) {

		for(int r = cand.size() - 1; r >= 0; r--) {
			if(cand.get(r).support < minSupport) {
				cand.remove(r);
			}
		}
			
		return cand;
	}
	
	// Builds the typical candidate table
	private static List<KeyValue> buildCand(List<List<String>> itemSets) {
		List<KeyValue> canTable = new ArrayList<KeyValue>();
		
		// Populating the items
		for(int i = 0; i < itemSets.size(); i++) {
			List<Item> entry = new ArrayList<Item>();
			for(int j = 0; j < itemSets.get(i).size(); j++) {
				entry.add(new Item(itemSets.get(i).get(j)));
			}
			canTable.add(new KeyValue(entry, calcSupport(entry)));
		}
		return canTable;
	}
	
	// Builds the first candidate table
	private static List<KeyValue> buildFirstCand(List<Item> itemSets) {
		List<KeyValue> canTable = new ArrayList<KeyValue>();
		
		// Populating all the items
		for(int i = 0; i < itemSets.size(); i++) {
			List<Item> entry = new ArrayList<Item>();
			entry.add(itemSets.get(i));
 			canTable.add(new KeyValue(entry, calcSupport(entry)));
		}
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
		
		// Removing duplicates
		List<String> uniques = removeDups(allItems);

		List<Item> uniqueItems = new ArrayList<Item>();
		for(int i = 0; i < uniques.size(); i++) {
			uniqueItems.add(new Item(uniques.get(i)));
		}
		
		return uniqueItems;
	}
	
	// TODO: For testing, prints a table
	public static void printTable(List<KeyValue> tableInfo, String name) {
		System.out.println("==================== " + name + " Table ====================");
		for(int r = 0; r < tableInfo.size(); r++) {
			for(int c = 0; c < tableInfo.get(r).itemSet.size(); c++) {
				System.out.print(tableInfo.get(r).itemSet.get(c).value + "\t| ");
			}
			System.out.print(tableInfo.get(r).support + " |");
			System.out.println("");
		}
		System.out.println("=========================================================\n");
	}
	
	// Removes duplicates from a list of strings
	private static List<String> removeDups(List<String> withDups) {
		// This is derived from an example here https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
		Set<String> hs = new HashSet<>();
		hs.addAll(withDups);
		List<String> uniques = new ArrayList<String>();
		uniques.addAll(hs);
		
		return uniques;
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
