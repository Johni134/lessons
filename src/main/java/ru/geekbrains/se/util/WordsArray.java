package ru.geekbrains.se.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Work with words
 */
public final class WordsArray {

    private ArrayList<String> wordsArray;

    public WordsArray(String[] words) {
        wordsArray = new ArrayList<>(Arrays.asList(words));
    }

    public ArrayList<String> getList() {
        return wordsArray;
    }

    /**
     * Get unique words from array of words
     *
     * @return hash set, that contains unique words
     */
    public HashSet<String> getUniqueWords() {
        return new HashSet<>(wordsArray);
    }

    /**
     * Get words count from array of words
     *
     * @return hash map, that contains word->count
     */
    public HashMap<String, Integer> getWordsCount() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : wordsArray) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }

        return hashMap;
    }
}
