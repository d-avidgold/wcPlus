import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class wcPlus {

  // Input: any string of letters, dashes, or other characters.
  // Output: the same string with all letters made to lowercase, dashes preserved, and all other characters removed.
  public static String strip(String word) {
  	char[] arr = word.toCharArray();
  	String word2 = "";
  	for (int i = 0; i < arr.length; i ++) {
  		if (arr[i] == '-' || (arr[i] >= 97 && arr[i] <= 122) ) {
  			word2 += arr[i];
  		}
  		if (arr[i] >= 65 && arr[i] <= 90){
  			word2 += (char) (arr[i] + 32);
  		}
  	}
  	return word2;
  }


  public static void main(String[] args){
    
    // Initialize our list of words and the number of times they appear.
  	int[] topTenCounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  	String[] topTenWords = {"", "", "", "", "", "", "", "", "", ""};

  	// Helper variable initialization
  	Trie trie = new Trie();
  	String word;
  	int count;
  	boolean already;
  	int index;
  	int wc = 0;

  	try {
    	File testFile = new File(args[0]);
    	Scanner myReader = new Scanner(testFile);
	
    	while (myReader.hasNextLine()) {

    		// Get line of text and create list of words
    	    String data = (myReader.nextLine()).trim();
    	    String[] words = data.split(" "); 

    	    for (int i = 0; i < words.length; i ++) {

    	    	// Remove whitespace and don't count empty words
    	    	word = strip(words[i]).replace("\n", "").replace("\r", "");
    	    	if (word.length() != 0)
    	    	{
    	    		wc++;
    	    	}

    	    	// Add the word to our Trie or update its count. Then check if it's already in our list. 
    	    	count = trie.addWord(word);
    	    	already = false;
    	    	index = 9;
    	    	for (int j = 0; j <= 9; j++) {
    	    		if (word.equals(topTenWords[j])) {
    	    			topTenCounts[j] ++;
    	    			already = true;
    	    			index = j;
    	    			break;
    	    		}
    	    	}

    	    	// If it's not, see if it's common enough to overtake the last element.
    	    	if (!already 
    	    			&& (count > topTenCounts[9] 
    	    				|| ( count == topTenCounts[9]
    	    					&& topTenWords[9].compareTo(word) > 0 ))) 
    	    	{
    	    		topTenCounts[9] = count;
    	    		topTenWords[9] = word;
    	    	}

    	    	// Then, go through the list element by element to see if it's common enough to move up further.
    	    	// Do this either if it's more common than the preceding word OR if it precedes it alphabetically
    	    	// and is equally common. 
    	    	for (int j = index; j >= 1 
    	    							&& ((topTenCounts[j - 1] < topTenCounts[j]) 
    	    								|| (topTenCounts[j - 1] == topTenCounts[j] 
    	    									&& (topTenWords[j - 1]).compareTo(topTenWords[j]) > 0)) ; j--)
    	    	{
    	    		topTenCounts[j] = topTenCounts[j - 1];
    	    		topTenWords[j] = topTenWords[j - 1];
    	    		topTenCounts[j - 1] = count;
    	    		topTenWords[j - 1] = word;
    	    	}
    		}
    	}

    	myReader.close();

    	// Log the top ten words and word count
    	for (int k = 0; k < topTenWords.length; k++) {
    		System.out.println(topTenWords[k] + " " + topTenCounts[k]);
    	}
    	System.out.println("Total Word Count: " + wc);
  	}

	catch (FileNotFoundException e) {
    	System.out.println("File not found.");
     	e.printStackTrace();
    }

    // Now we'll go through each sentence and try to find the top word:
    String topWord = topTenWords[0];

  	try {
    	File testFile = new File(args[0]);
    	Scanner myReader = new Scanner(testFile);
    	String finalSentence = "";

    	while (myReader.hasNextLine()) {


    		// Split each line of the file into sentences...
    	    String data = (myReader.nextLine()).trim();
    	    String[] sentences = data.split("\\.");

    	    for (int i = 0; i < sentences.length; i ++) {
				
    	    	// ... and split each sentence into words
    	    	String[] currSentence = ((sentences[i]).trim().split(" "));
    	    	for (int j = 0; j < currSentence.length; j ++) { 
    	    		if (topWord.equals(strip(currSentence[j]))) {

    	    			// If the word shows up in the sentence, store it for printing later.
    	    			finalSentence = sentences[i].trim();
    	    			break;
    	    		}
    	    	}
    	    }
    	}

    	// Log the sentence with the most common word.
    	System.out.println("Final sentence with top word: \"" + finalSentence + ".\"");
    }

    catch (FileNotFoundException e) {
    	System.out.println("File not found.");
     	e.printStackTrace();
    }

  }



}