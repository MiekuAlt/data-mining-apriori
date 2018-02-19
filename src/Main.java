import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private static String filename;
	private static double support;
	private static double confidence;
	
	public static void main(String[] args) {
		/* TODO: Remove this, using it to avoid input
		
		// Initial User Input - filename
		System.out.print("Welcome to Apriori Miner!\nPlease enter data file's name: ");
		Scanner in = new Scanner(System.in);
		filename = in.nextLine();
		
		// Initial User Input - support
		int reloop;
		do {
			reloop = 0;
			System.out.print("Please enter support percentage: ");
			in = new Scanner(System.in);
			try {
				support = in.nextDouble();
			} catch(Exception e) {
				System.out.println("Incorrect format");
				reloop++;
			}
		} while(reloop != 0);

		
		// Initial User Input - confidence	
		do {
			reloop = 0;
			System.out.print("Please enter confidence percentage: ");
			in = new Scanner(System.in);
			try {
				confidence = in.nextDouble();
			} catch(Exception e) {
				System.out.println("Incorrect format");
				reloop++;
			}
		} while(reloop != 0);

		// Just closing the scanner
		in.close();
		
		Remove me!! */ 
		
		
		//TODO: Remove this roughed out input and replace with a real one
		List<List<String>> fakeData = new ArrayList<List<String>>();
		// Setting up some faked data from DATA1 for Michael to work with
		fakeData.add(Arrays.asList("outlook", "temperature", "Humidity", "Windy", "PlayTennis"));
		fakeData.add(Arrays.asList("sunny", "hot", "high", "false", "N"));
		fakeData.add(Arrays.asList("sunny", "hot", "high", "true", "N"));
		fakeData.add(Arrays.asList("overcast", "hot", "high", "false", "P"));
		fakeData.add(Arrays.asList("overcast", "hot", "high", "false", "P")); // added this to check duplicates
		fakeData.add(Arrays.asList("rain", "mild", "high", "false", "P"));
		
		double minSupport = 0.5, minConfidence = 0.5;
		
		List<String> rules = Apriori.runApriori(fakeData, minSupport, minConfidence);
		System.out.println("Fake rules:\n" + rules);
		// End of the roughed out temporary Apriori utilization
	}

}
