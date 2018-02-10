import java.util.Arrays;
import java.util.List;

public final class Apriori {

	public static List<String> runApriori(List<List<String>> data, double minSupport, double minConfidence) {
		
		
		//TODO: Remove this dummy output
		List<String> dummyRules = Arrays.asList("(Support=0.29, Confidence=1.00) { outlook=overcast } ----> { PlayTennis=P }", "(Support=0.29, Confidence=0.67) { temperature=mild } ----> { Humidity=high }");
		return dummyRules;
	}
}
