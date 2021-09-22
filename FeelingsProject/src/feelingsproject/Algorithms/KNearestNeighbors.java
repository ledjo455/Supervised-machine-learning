package feelingsproject.Algorithms;
import feelingsproject.FileModel;
import feelingsproject.FolderModel;
import feelingsproject.MyHashTable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class KNearestNeighbors extends Algorithm{

    int k; // k must be an odd number to avoid draws

    public KNearestNeighbors() {
        super("K-nearest neighborsr");
        k = 3;
    }

    @Override
    public void executeAlgorithm(List<FolderModel> models, FileModel mysteryFile) {

        int x0, y0, x1, y1;
        ArrayList<String> distanceClass = new ArrayList<>(); // all the points of a class
        ArrayList<DistanceStructure> distancePoint = new ArrayList<>(k); // 3 points of a word

        for (String wordMystery: mysteryFile.getHistogram().keySet()) { // for all words in the mystery file

            for(FolderModel folder: models) {

                x0 = getIndexOfWord(wordMystery);
                y0 = mysteryFile.getHistogram().get(wordMystery);

                for (String wordFolder: folder.getHistogram().keySet()) { // for all words in the current folder

                    x1 = getIndexOfWord(wordFolder);
                    y1 = folder.getHistogram().get(wordFolder);

                    double distance = getDistance(x0, y0, x1, y1);

                    insert(distance, folder.getNameFolder(), distancePoint);
                }

            }

            distanceClass.add(getMajority(distancePoint)); // add to distanceClass the class with the majority of appearances
            distancePoint.clear();
        }

        for(FolderModel folder: models) {
            long count = distanceClass.stream().filter(s -> s.equals(folder.getNameFolder())).count();
            createResult(folder.getNameFolder(), count);
        }

    }

    public String getMajority(ArrayList<DistanceStructure> ls){

        Map<String, Long> collect = ls.stream() // count how many times a class is repeated
                .collect(Collectors.groupingBy(distanceStructure -> distanceStructure.nameClass, Collectors.counting()));

        return collect.entrySet().stream().max((t0, t1) -> {
            if (t0.getValue().equals(t1.getValue())) return 0;
            return t0.getValue() > t1.getValue() ? 1 : -1;
        }).get().getKey();
    }

    // inserts the new point into the list if it is closer to all the points that where compared before
    private void insert(double distance, String nameClass, ArrayList<DistanceStructure> distancePoint) {

        if(distancePoint.size() < k) {
            distancePoint.add(new DistanceStructure(distance, nameClass));
            return;
        }

        for (int i = 0; i < k; i++) { // compare if the new point is closest than the old points
            if(distancePoint.get(i).distance > distance){
                distancePoint.set(i, new DistanceStructure(distance, nameClass)); // if it is closer then replace it
                return;
            }
        }

    }

    @Override
    public BiConsumer<String, MyHashTable> getConsumer() {
        return bagOfWords;
    }

    public double getDistance(int x0, int y0, int x1, int y1) {
        //euclidean distance
        double powX = Math.pow((x0 - x1), 2);
        double powY = Math.pow((y0 - y1), 2);
        return Math.sqrt(powX + powY);
    }

    public static class DistanceStructure { // String=class or folder, Double=distance
        public Double distance;
        public String nameClass;

        public DistanceStructure(Double distance, String nameClass) {
            this.distance = distance;
            this.nameClass = nameClass;
        }

        @Override
        public String toString() {
            return "DistanceStructure{" +
                    "distance=" + distance +
                    ", nameClass='" + nameClass + '\'' +
                    '}';
        }
    }

}

