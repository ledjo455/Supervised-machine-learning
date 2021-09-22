package feelingsproject.Algorithms;

import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import feelingsproject.MyHashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class LogisticRegression extends Algorithm{

    /** the learning rate */
    private double rate;

    /** the weight to learn */
    private double[] weights;

    /** the number of iterations */
    private int ITERATIONS = 3000;

    public LogisticRegression() {
        super("Logistic Regression");
    }

    public void train(List<Instance> instances) {
        for (int n=0; n<ITERATIONS; n++) {
            for (int i=0; i<instances.size(); i++) {
                int[] x = instances.get(i).x;
                double predicted = classify(x);
                int label = instances.get(i).label;
                for (int j=0; j<weights.length; j++) {
                    weights[j] = weights[j] + rate * (label - predicted) * x[j];
                }
            }
        }
    }

    private double classify(int[] x) {
        double logit = .0;
        for (int i=0; i<weights.length;i++)  {
            logit += weights[i] * x[i];
        }
        return sigmoid(logit);
    }

    public static class Instance {
        public int label;
        public int[] x;

        public Instance(int label, int[] x) {
            this.label = label;
            this.x = x;
        }
    }

    private static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }


    private int[] histogramToVector(int size, Set<String> words) {
        int[] data = new int[size];
        int index = 0;

        for(String word: words) {
            index = getIndexOfWord(word);
            if (index > 0 && index < size) data[index] = 1;
        }

        return data;
    }

    @Override
    public void executeAlgorithm(List<FolderModel> models, FileModel mysteryFile) {
        int totalAmountOfWords = models.stream().mapToInt(f -> f.getHistogram().size()).sum();

        // getting the data
        List<Instance> dataset = new ArrayList<>(2);
        int label = 0;

        for(FolderModel folderModel: models){

            int[] data = histogramToVector(totalAmountOfWords, folderModel.getHistogram().keySet());

            dataset.add(new Instance(label, data));
            label = 1;
        }

        // training
        weights = new double[totalAmountOfWords];
        rate = 0.0001;

        train(dataset);

        // classifying
        int[] x = histogramToVector(totalAmountOfWords, mysteryFile.getHistogram().keySet());

        double classify = classify(x);
        createResult(models.get(classify > 0.5 ? 1 : 0).getNameFolder(), classify);
    }

    @Override
    public BiConsumer<String, MyHashTable> getConsumer() {
        return bagOfWords;
    }

}
