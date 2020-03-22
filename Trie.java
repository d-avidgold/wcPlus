

public class Trie {
	private TrieNode rootNode = new TrieNode();

	// Initialize a Trie with a single node with all null children
	public void Trie() {
		rootNode = new TrieNode();
	}

	// Either add new connections to introduce a new word or update the count for an existing word
	public int addWord(String word) {

		TrieNode currentNode = rootNode;

		char[] arr = word.toCharArray();

		// Move letter by letter through the Trie
		for (int i = 0; i < arr.length; i ++) {

			if (arr[i] == '-' || (arr[i] >= 65 && arr[i] <= 90) || (arr[i] >= 97 && arr[i] <= 122)) {
				if (currentNode.getChild(arr[i]) != null) {
					currentNode = currentNode.getChild(arr[i]);

				}

				// If child is null, make a new trie for this letter.
				else {
					TrieNode child = new TrieNode();
					currentNode.setChild(arr[i], child);
					currentNode = child;
				}
			}
		}

		if (currentNode.check()) {
			return currentNode.increase(1);
		}

		else {
			return currentNode.makeWord();
		}
	}

	// Go through the Trie and see if the word is in it. Returns 0 if not, and the count if it is.
	public int getCount(String word) {

		TrieNode currentNode = rootNode;

		char[] arr = word.toCharArray();

		for (int i = 0; i < arr.length; i ++) {
			if (currentNode.getChild(arr[i]) != null) {
				currentNode = currentNode.getChild(arr[i]);
			}
			else {
				return 0;
			}
		}

		return currentNode.increase(0);
	}
}