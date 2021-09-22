package feelingsproject.Algorithms;

import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import feelingsproject.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This class is an abstraction of the four algorithms
 * */
public abstract class Algorithm {

    private final String algorithmName;
    private final StringBuilder results;
    private ArrayList<String> quantifiedWords;


    public Algorithm(String algorithmName) {
        this.algorithmName = algorithmName;
        this.results = new StringBuilder();

    }

    public abstract void executeAlgorithm(List<FolderModel> models, FileModel mysteryFile);
    public abstract BiConsumer<String, MyHashTable> getConsumer();

    public void printResults() {
        if(results.length() == 0){
            System.out.println("\n\n-------- there are no results --------");
            return;
        }

        System.out.printf("\n-------- results of %s --------%n", algorithmName);

        System.out.println(results.toString());

        System.out.println("-------- end of results --------");
        results.setLength(0);

    }

    protected void createResult(String name, double value){
        // create result message
        results.append('\n').append(name).append(":\t").append(value);
    }

    public StringBuilder getResults() {
        return results;
    }


    // algoritms to extrat the text

    public static  BiConsumer<String, MyHashTable> bagOfWords = (fileText, model) -> { // separate all words with more than 2 characters
            Arrays.stream(fileText.split("\\s+")) // split by spaces
                    .filter(s -> s.matches("[A-Za-zÀ-ÖØ-öø-ÿ]{3,}")) // filter non alphabetic words
                    .forEach(s -> model.addNGram((s.toLowerCase()))); // add all to model
    };

    public ArrayList<String> getQuantifiedWords() {
        if(quantifiedWords == null) quantifiedWords = new ArrayList<>();
        return quantifiedWords;
    }

    public int getIndexOfWord(String word) {

        // quantify the word (each word has a unique value on the x axes)
        if(!getQuantifiedWords().contains(word)) getQuantifiedWords().add(word);
        return getQuantifiedWords().indexOf(word);
    }
}
