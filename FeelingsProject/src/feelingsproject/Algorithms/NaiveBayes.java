package feelingsproject.Algorithms;
import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import feelingsproject.MyHashTable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class NaiveBayes extends Algorithm{

    public NaiveBayes() {
        super("Naive Bayes");
    }

    @Override
    public void executeAlgorithm(List<FolderModel> models, FileModel mysteryFile) {
        System.out.println("Naive: ");


        double totalTexts = models.stream().mapToInt(FolderModel::getAmountSubTexts).sum(); // total txts in all folders

        MyHashTable mysteryHistogram = mysteryFile.getHistogram();

        for(FolderModel folder: models) {

            // P(F) // Prior probability = amountTexts of this folder / (total txt in all folders)
            double prior = folder.getAmountSubTexts() / totalTexts;

            MyHashTable histogram = folder.getHistogram();

            int totalElements = getSumOfElements(folder); // amount of elements
            if(totalElements == 0) continue;

            // for each element of the mystery histogram
            // prior * p(element0 | F) * p(element1 | F) ...

            BigDecimal probability = mysteryHistogram.keySet().stream().map(element ->
                    //p(element | F)
                     // if there is no element then 1 to solve the 0 appearance problem
                    new BigDecimal((double)Optional.ofNullable(histogram.get(element)).orElse(1) / (double) totalElements)
            ).reduce(BigDecimal::multiply).orElse(BigDecimal.valueOf(0));

            //create message
            createResult(folder.getNameFolder(), probability.doubleValue()*100);
        }

    }

    private int getSumOfElements(FolderModel mysteryFile) {
        return mysteryFile.getHistogram().values().stream().mapToInt(i -> i+1).sum(); // each element +1 to solve the 0 appearance problem
    }

    @Override
    public BiConsumer<String, MyHashTable> getConsumer() {
        return bagOfWords;
    }
}
