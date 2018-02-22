CSCI 4144 Assignment 3
Greg Mailman B00695833
Michael Altair B00599791

# data-mining-apriori
Compiling) 
The program can be compiled using javac as per the example.
1) Place the data files alongside Main.java.
2) run javac Main.java in the terminal
3) run java Main 

Running the program) 
Once it runs, you will be prompted for the input file and values for the support and confidence.
input the local path to the file in question (data1 if it is in the same directory for example), and either a percentage or a decimal for the support and confidence.
The program will give errors in the console if the numbers are out of scope, or if it cannot find the file.

The program only has one supporting file for the main file, we used a black box approach in that the main file inputs data and gets data back, without doing any manipulating itself.
apriori.java has the following methods: 

public static List<String> runApriori(List<List<String>> data, double supportInput, double confidenceInput)
private static void runAssociation(List<KeyValue> freqTable)
private static List<KeyValue> genTables()
private static List<List<String>> expandItemSet(List<KeyValue> table, int n)
public static <T> List<List<T>> combination(List<T> values, int size)
private static List<String> convertTableToData(List<KeyValue> table)
private static double calcSupport(List<Item> itemsChecked)
private static List<KeyValue> buildFreq(List<KeyValue> cand)
private static List<KeyValue> buildCand(List<List<String>> itemSets)
private static List<KeyValue> buildFirstCand(List<Item> itemSets)
private static List<Item> findUniquesInData(List<List<String>> data)
private static List<String> removeDups(List<String> withDups)

Overall Flow) 
The input system reads the file line by line, using the first line to set up a tagging system, and adding the tags to each of the data values in the following lines.
An example of this would be "outlook=sunny". These data points are stored in a two dimensional list, which are then passed to the runApriori function.
runApriori goes through the given list, finds all unqiue itemsets and builds a candidate table from this. It loops using the apriori algorithm, checking supports for the candidates, pruning sets that dont match, then expanding the itemsets until there are no more items in the frequency table.
After this a list of candidate rules remains, which is passed into a rule generation algorithm, which generates all possible rules, then prunes based on the given confidence. Once this is done, it formats the rules for output and returns a new list containing the discovered rules.
This list is passed back to the main file, which exports it into a Rules rile along with the other information required, such as the number of rows, number of rules, support, and confidence.

Task partition) 
Michael: The entire Aprioi.java file, the structure of the program.
Greg: The file input and formatting, the file output and formatting, The README.