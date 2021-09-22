package feelingsproject.Algorithms;

import feelingsproject.Algorithms.LogisticRegression;
import java.util.Hashtable;

public class AlgorithmFactory {

    private static Hashtable<Integer, Algorithm> algorithms;

    private AlgorithmFactory(){}

    public static Hashtable<Integer, Algorithm> getAlgorithms() {

        if(algorithms == null){
            algorithms = new Hashtable<>();

            algorithms.put(1, new CosineSimilarity());
            algorithms.put(2, new NaiveBayes());
            algorithms.put(3, new LogisticRegression());
            algorithms.put(4, new KNearestNeighbors());
        }

        return algorithms;
    }
}
