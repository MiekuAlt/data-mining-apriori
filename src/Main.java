import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private static String filename;
	private static double support;
	private static double confidence;
	
	public static void main(String[] args) {

		
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
		

		
		
		//TODO: Remove this roughed out input and replace with a real one
		List<List<String>> fakeData = new ArrayList<List<String>>();
		// Setting up some faked data from DATA1 for Michael to work with
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=hot", "Humidity=high", "Windy=false", "PlayTennis=N"));
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=hot", "Humidity=high", "Windy=true", "PlayTennis=N"));
		fakeData.add(Arrays.asList("outlook=overcast", "temperature=hot", "Humidity=high", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=overcast", "temperature=hot", "Humidity=high", "Windy=false", "PlayTennis=P")); // added this to check duplicates
		fakeData.add(Arrays.asList("outlook=rain", "temperature=mild", "Humidity=high", "Windy=false", "PlayTennis=P"));

		double minSupport = .5, minConfidence = 0.1;
		
		List<String> rules = Apriori.runApriori(fakeData, minSupport, minConfidence);
		System.out.println("Fake rules:\n" + rules);
		// End of the roughed out temporary Apriori utilization
	}

}
