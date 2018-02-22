import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;;
import java.io.File;

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
				support = in.nextDouble()/100;
				while (support>1 || support<0) {
					System.out.print("Invalid number, please enter a percentage between 0 and 100: ");
					support = in.nextDouble()/100;
				}
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
				confidence = in.nextDouble()/100;
				while (confidence>1 || confidence<0) {
					System.out.print("Invalid number, please enter a percentage between 0 and 100: ");
					confidence = in.nextDouble()/100;
				}
			} catch(Exception e) {
				System.out.println("Incorrect format");
				reloop++;
			}
		} while(reloop != 0);

		// Just closing the scanner
		in.close();

		////////////////////////
		//used to count the rows
		int numRows = 0;
		List<List<String>> Data = new ArrayList<List<String>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("DataInput/" + filename));
			// seperating the first line and setting up the tags to add to the data
			String line = br.readLine();
			String[] tags = line.split(" +");


			//loops through the file, reading line by line
			//splits the line, then adds the tags to the corresponding value
			//adds the split line into the data array
			line = br.readLine();
			while (line != null) {
				numRows++;
				String[] split = line.split(" +");
				for (int i=0; i < split.length; i++) {
					split[i] = tags[i] + "=" + split[i];
				}

				Data.add(Arrays.asList(split));
				line = br.readLine();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("No file found.");
			return;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			}
			catch (Exception e) {
				System.out.println("Error closing the reader");
			}
		}
		///////////////////////////

		/*
		//TODO: Remove this roughed out input and replace with a real one
		List<List<String>> fakeData = new ArrayList<List<String>>();
		// Setting up some faked data from DATA1 for Michael to work with
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=hot", "Humidity=high", "Windy=false", "PlayTennis=N"));
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=hot", "Humidity=high", "Windy=true", "PlayTennis=N"));
		fakeData.add(Arrays.asList("outlook=overcast", "temperature=hot", "Humidity=high", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=rain", "temperature=mild", "Humidity=high", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=rain", "temperature=cool", "Humidity=normal", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=rain", "temperature=cool", "Humidity=normal", "Windy=true", "PlayTennis=N"));
		fakeData.add(Arrays.asList("outlook=overcast", "temperature=cool", "Humidity=normal", "Windy=true", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=mild", "Humidity=high", "Windy=false", "PlayTennis=N"));
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=cool", "Humidity=normal", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=rain", "temperature=mild", "Humidity=normal", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=sunny", "temperature=mild", "Humidity=normal", "Windy=true", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=overcast", "temperature=mild", "Humidity=high", "Windy=true", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=overcast", "temperature=hot", "Humidity=normal", "Windy=false", "PlayTennis=P"));
		fakeData.add(Arrays.asList("outlook=rain", "temperature=mild", "Humidity=high", "Windy=true", "PlayTennis=N"));
*/
		double minSupport = .25, minConfidence = 0.5;

		List<String> rules = Apriori.runApriori(Data, support, confidence);
		//System.out.println("Fake rules:\n" + rules);
		// End of the roughed out temporary Apriori utilization

		//outputs the summary and discovered rules to a file named "Rules"
		//tries to open a file, and creates one if its not found.
		BufferedWriter bw = null;
		try {
			File outPut = new File("Rules");
			if (!outPut.exists()) {
				outPut.createNewFile();
			}

			FileWriter fw = new FileWriter(outPut);
			bw = new BufferedWriter(fw);

			bw.write("Summary: \n");
			bw.write("Total rows in the original set: " + numRows + "\n");
			bw.write("Total rules discovered: " + rules.size() + "\n");
			bw.write("The selected measures: Support=" + support + ", Confidence=" + confidence + "\n");
			bw.write("----------------------------\n");
			bw.write("Discovered rules:\n\n" + rules);
		} catch(Exception e) {
			System.out.println("Error outputting the rules.");
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch(Exception e) {
				System.out.println("Error closing the writer.");
			}

		}
	}

}
