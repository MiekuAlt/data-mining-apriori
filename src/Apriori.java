import java.util.Arrays;
import java.util.List;

public final class Apriori {

	private static List<List<String>> inputData;
	private static double minSupport, minConfidence;
	
	public static List<String> runApriori(List<List<String>> data, double supportInput, double confidenceInput) {
		inputData = data;
		minSupport = supportInput;
		minConfidence = confidenceInput;
		
		// Find all the unique itemsets in the data
		
		
		
		
		
		//TODO: Remove this dummy output
		List<String> dummyRules = Arrays.asList("(Support=0.29, Confidence=1.00) { outlook=overcast } ----> { PlayTennis=P }", "(Support=0.29, Confidence=0.67) { temperature=mild } ----> { Humidity=high }");
		return dummyRules;
	}
	
	// Finds all of the unique items in the data
	private static List<String> findUniques() {
		List<String> uniquesFound = null;
		
		
		
		return uniquesFound;
	}
	
} // end of the Apriori class








// A table row in both candidate and frequency tables
class KeyValue {
	List<String> itemSet;
	double support;
}
