package mikew79.wordcount;

import java.util.*;

/**
 * Parses words from an input stream, counting occurence of each word until the end of the stream. any special characters
 * are ignored, and it is possible to set the cases sensitivity so that capitalised and no capitalised words are treated
 * differently. This object also probides a menthod to print or return the output as a soreted list, on the count
 */
public class WordCounter {
    /** Map to maintain all words found in the input */
    private Map<String, Integer> wordsMap = new HashMap<String,Integer>();

    /** If true then words are counted in a case-sensitive manner, so "AND", "And", "and" would be three different words */
    private boolean caseSensitive = false;

    /**
     * Default Constructor
     */
    public WordCounter () {
    }

    /**
     * Create WordCoutner Setting the case sensitivity option
     * @param isCaseSensitive
     */
    public WordCounter(boolean isCaseSensitive) {
        this.caseSensitive = isCaseSensitive;
    }

    /**
     * Get the current case sensitivity of this object
     * @return - True if words are added in a case-sensitive manner
     */
    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    /**
     * Set the case sensitivity of this Word Counter
     * @param v -value
     */
    public void setCaseSensitive(boolean v) {
        this.caseSensitive = v;
    }

    /**
     * Check if this word counter contains a particular word
     * @param word - The word to search for
     * @return - True if this word has been counted
     */
    public boolean hasWord(String word) {
        return this.wordsMap.containsKey(isCaseSensitive()? word : word.toUpperCase());
    }


    /**
     * Pasre words from an input stream counting occurences
     * @param input - input stream
     * @return - count of all words parsed
     */
    public int parse (Scanner input) {
        int count = 0;
        while (input.hasNext()) {
            String word  = input.next();
            word = word.replaceAll("[^a-zA-Z0-9]", ""); // remove any special characters
            addWord(word);
            count = count + 1;
        }
        return count;
    }

    /**
     * Print the list of words counts using the default descending order
     */
    public void printCounts() {
        printCounts(false);
    }

    public LinkedHashMap<String, Integer> getSortedCounts(boolean ascending) {     ArrayList<Integer> list = new ArrayList<>();
        LinkedHashMap<String, Integer> sortedWordsMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : this.wordsMap.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        // default sort is descending so reverse if we want ascending
        if (!ascending)
            Collections.reverse(list);

        for (int num : list) {
            for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedWordsMap.put(entry.getKey(), num);
                }
            }
        }
        return sortedWordsMap;
    }

    /**
     * Print the list of words counts in either ascending or descending order
     * @param ascending - If True counts will be printed lowest to highest, otherwise default high to low is used
     */
    public void printCounts(boolean ascending) {
        // first Sort our hashmap into ascending or descending order
        LinkedHashMap<String, Integer> sortedWordsMap = getSortedCounts(ascending);

        // now print our sorted map out in the format (word : count) with each word on a new line
        for (Map.Entry<String, Integer> entry : sortedWordsMap.entrySet()){
            System.out.println(entry.getKey().toLowerCase() + ":" + entry.getValue());
        }
    }

    /**
     * Add A Word to the map of this object, if the word already exists the count is incremented
     * @param wordToAdd - the word to add to this map
     */
    private void addWord (String wordToAdd) {
        if (hasWord(wordToAdd)) {
            this.wordsMap.put(isCaseSensitive()? wordToAdd : wordToAdd.toUpperCase(), this.wordsMap.get(isCaseSensitive()? wordToAdd : wordToAdd.toUpperCase())+1);
        } else {
            this.wordsMap.put(isCaseSensitive()? wordToAdd : wordToAdd.toUpperCase(), 1);
        }
    }
}
