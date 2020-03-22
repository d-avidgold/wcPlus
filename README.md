# wcPlus.java
Word Counter that also provides top 10 words in a file with more information

# Compilation/Runtime Instructions
Save all java files to a single folder and compile with

> javac \*.java

If file.txt contains the text you want analysed, run with

> java wcPlus file.txt

# Description
Program stores top ten words (at any given moment) as a sorted array, and also keeps track of their counts. Every new word is searched for in a continuously updated Trie that stores counts, and if its count exceeds the smallest of the top ten, it overtakes it. 

Once all ten words are chosen, the top word is searched for in every sentence of the file. When it is found, the sentence is stored until overwritten or until the file ends.
