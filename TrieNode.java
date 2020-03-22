

public class TrieNode {
	
	private TrieNode[] children = new TrieNode[27];
	private boolean isWord;
	private int wordCount;

	// Initialize node with null children.
	public void TrieNode() {
		for (int i = 0; i < 27; i++) {
			children[i] = null;
		}
		isWord = false;
		wordCount = 0;
		System.out.println("hi");
	}

	// Convert char to int (consolidate capital and lowercase, set - as the 27th letter).
	public int getIndex(char letter) {
		if (letter == '-') {
			return 26;
		}
		if (letter >= 65 && letter <= 90) {
			return letter - 65;
		}
		if (letter >= 97 && letter <= 122) {
			return letter - 97;
		}
		return -1;
	}

	// Return the Trie that is the child corresponding to a certain letter.
	public TrieNode getChild(char child) {
		int index = getIndex(child);
		if (index != -1) {
			return children[getIndex(child)];
		}
		else {
			return null;
		}
	}

	// Make a new child given a Trie and a letter.
	public TrieNode setChild(char child, TrieNode newNode) {
		int index = getIndex(child);
		if (index != -1) {
			children[getIndex(child)] = newNode;
			return newNode;
		}
		else {
			return this;
		}
	}

	public boolean check() {
		return isWord;
	}

	public int increase(int n) {
		wordCount += n;
		return wordCount;
	}

	public int makeWord() {
		isWord = true;
		wordCount++;
		return 1;
	}
}