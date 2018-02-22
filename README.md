CSCI 4144 Assignment 3
Greg Mailman B00695833
Michael Altair B00599791

# data-mining-apriori
Compiling) 


Running the program) 
Once it runs, you will be prompted for the input file and values for the support and confidence.
input the local path to the file in question (data1 if it is in the same directory for example), and a number between 0 and 100 for the support and confidence.
The program will give errors in the console if the numbers are out of scope, or if it cannot find the file.

Overall Flow) 
The input system reads the file line by line, using the first line to set up a tagging system, and adding the tags to each of the data values in the following lines.
An example of this would be "outlook=sunny". These data points are stored in a two dimensional list, which are then passed to the runApriori function.
runApriori goes through the given list, finds all unqiue itemsets and builds a candidate table from this. It loops using the apriori algorithm, checking supports for the candidates, pruning sets that dont match, then expanding the itemsets until there are no more items in the frequency table.
After this a list of candidate rules remains, which is passed into a rule generation algorithm, which generates all possible rules, then prunes based on the given confidence. Once this is done, it formats the rules for output and returns a new list containing the discovered rules.
This list is passed back to the main file, which exports it into a Rules rile along with the other information required, such as the number of rows, number of rules, support, and confidence.

Task partition) 
Michael: The entire Aprioi.java file, the structure of the program.
Greg: The file input and formatting, the file output and formatting, The README.