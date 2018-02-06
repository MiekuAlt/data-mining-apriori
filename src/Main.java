import java.util.Scanner;

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
		
		// TODO: Just for testing user input
		System.out.println("You entered...\nfilename: " + filename + "\nsupport: " + support + "\nconfidence: " + confidence);
	}

}
