package com.tuesdays.scrabble;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


class ScrabbleService  implements  scrabble{

    private static final Logger LOGGER = Logger.getLogger(ScrabbleService.class.getName());

    private  Set<String> dictionary;
    private final String fileName = "/Users/re/Desktop/wordsEn.txt";
    private Map<Character, Integer> score;
    private Map<String, Integer> wordScores;

    public ScrabbleService (){
        wordScores = new HashMap<>();
        score = new HashMap<>();
            score.put('A', 1);
            score.put('B', 3);
            score.put('C', 3);
            score.put('D', 2);
            score.put('E', 1);
            score.put('F', 2);
            score.put('G', 2);
            score.put('H', 4);
            score.put('I', 1);
            score.put('J', 8);
            score.put('K', 5);
            score.put('L', 1);
            score.put('M', 3);
            score.put('N', 1);
            score.put('O', 1);
            score.put('P', 3);
            score.put('Q', 10);
            score.put('R', 1);
            score.put('S', 1);
            score.put('T', 1);
            score.put('U', 1);
            score.put('V', 4);
            score.put('W', 4);
            score.put('X', 8);
            score.put('Y', 4);
            score.put('Z', 10);
    }

    /**
     * Returns a list of words that can be spelled from the given set of letters.
     * It is sorted by its Scrabble point value.
     *
     * @param letters The letters to form words from
     * @return A sorted set of words
     */
    @Override
    public List<String> getWords(String letters) {
        List<String> words = new ArrayList<>();
        setUpDictionary();
        int length = 1;
        char[] word;
        while(length < letters.length()){
            word = new char[length];
            iterate(letters.toCharArray(), length, word, 0);
            length++;
        }
        substring("", letters);
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordScores.entrySet());
        list.sort(new ValueThenKeyComparator<>());

        list.stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(s -> words.add(s.getKey()));

        return words;
    }

    /**
     * For the given String length we want to make sure
     * we found appropriate words that can be formed from the give
     * characters.
     * @param prefix decriment until n=0 while building a word from the prefix
     * @param str the given characters as a string.
     */
    private void substring(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            if (dictionary.contains(prefix)){
                wordScores.put(prefix, getTotal(prefix));
            }
        }
        else {
            for (int i = 0; i < n; i++)
                substring(prefix + str.charAt(i),
                        str.substring(0, i) + str.substring(i+1, n));
        }
    }

    /**
     * Set up the dictionary to look up for all possible
     * words from the given letters.
     */
    private void setUpDictionary()  {
        dictionary = new TreeSet<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName),
                Charset.defaultCharset())) {
            stream.forEach(s -> dictionary.add(s));
        } catch (IOException e){

            LOGGER.error("File path not fount, ", e);
        }
    }


    /**
     * given the array of all characters, the length of the word
     * we want to form, and the position where to start,
     * find all possible words. (Heap Algorithm)
     * find for all length except String.length() to avoid
     * characters repeated word formation.
     * @param chars letters as a chars array
     * @param len length of the word we want to form
     * @param word all possible words we find
     * @param pos the position of the array
     */
    private void iterate(char[] chars, int len, char[] word, int pos) {
        if (pos == len) {
            String newWord = new String(word);
            if (dictionary.contains(newWord)){
                wordScores.put(newWord, getTotal(newWord));
            }
            return;
        }

        for (char aChar : chars) {
            word[pos] = aChar;
            iterate(chars, len, word, pos + 1);
        }
    }

    /**
     *
     * @param word input word that will have a whaigh value
     *             as a return.
     * @return a weight total value for the given word.
     */
    private int getTotal(String word){
        int total = 0;
        for (int i=0; i<= word.length()-1; i++){
            if (score.containsKey(Character.toUpperCase(word.charAt(i)))){
                total = total + score.get(Character.toUpperCase(word.charAt(i)));
            }
        }
        return total;
    }

    /**
     * A class that is used to compare the hash values.
     * If the Hash values are the same, then we compare the keys.
     * @param <K> HashMap Key
     * @param <V> HashMap Value
     */
    class ValueThenKeyComparator<K extends Comparable<? super K>,
            V extends Comparable<? super V>>
            implements Comparator<Map.Entry<K, V>> {
        public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
            if (a.getValue().compareTo(b.getValue()) > 0) {
                return -1;
            }else if (a.getValue().compareTo(b.getValue()) < 0){
                return  1;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        }
    }
}
