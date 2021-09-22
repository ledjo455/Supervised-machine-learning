package feelingsproject.Algorithms;
import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import feelingsproject.Menu;
import feelingsproject.MyHashTable;

import java.util.List;
import java.util.function.BiConsumer;

public class CosineSimilarity extends Algorithm{


    public CosineSimilarity() {
        super("Cosine similarity");
    }


    @Override
    public void executeAlgorithm(List<FolderModel> models, FileModel mysteryFile) {

        // norm mystery
        double mysteryNorm = getNormOf(mysteryFile.getHistogram());

        for(FolderModel folder: models) {

            MyHashTable histogram = folder.getHistogram();

            double folderNorm = getNormOf(histogram);

            int dotProduct = mysteryFile.getHistogram()
                    .entrySet()
                    .stream()
                    .filter(entry -> histogram.get(entry.getKey()) != null) // filter values that are on both models (mystery and folder)
                    .mapToInt(entry -> histogram.get(entry.getKey()) * entry.getValue())
                    .sum();

            double cosineSimilarity = getCosineSimilarity(mysteryNorm, folderNorm, dotProduct);

            createResult(folder.getNameFolder(), cosineSimilarity);
        }

    }

    public double getCosineSimilarity(double mysteryNorm, double folderNorm, int dotProduct) {
        double vectorDistance = dotProduct /(mysteryNorm * folderNorm);

        // round 4 digits precision to avoid decimal trash i.e 1.0000000000000002
       vectorDistance = (double)Math.round(vectorDistance * 10000d) / 10000d;
        vectorDistance = Math.acos(vectorDistance);

        // cosine similarity was in radians
        return Math.toDegrees(vectorDistance);
    }

    public double getNormOf(MyHashTable histogram) {
        return Math.sqrt(histogram.values().stream()
                .mapToInt(i -> i * i)
                .sum());
    }

    @Override
    public BiConsumer<String, MyHashTable> getConsumer() {

        int nGram = new Menu().nGramMenu();  // get NGram

        return (fileText, model) -> {
            StringBuilder sb = new StringBuilder();

            fileText.chars().forEach(i -> { // n-gram
                Character c = (char) i;
                if(c.toString().matches("[A-Za-zÀ-ÖØ-öø-ÿ]")){ // if is letter
                    sb.append(c); // add letter
                    if(sb.length() >= nGram){
                        model.addNGram(sb.toString().toLowerCase());
                        sb.deleteCharAt(0);
                    }
                }else{
                    sb.setLength(0); // clean
                }
            });
        };

    }
}
