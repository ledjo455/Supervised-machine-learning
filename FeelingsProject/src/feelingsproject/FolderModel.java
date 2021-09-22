package feelingsproject;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class FolderModel extends Thread {

    private final File subFolder;
    private final MyHashTable model;
    private int amountSubTexts;
    private final BiConsumer<String, MyHashTable> consumer;



    public FolderModel(File subFolder, BiConsumer<String, MyHashTable> consumer) {
        this.subFolder = subFolder;
        this.consumer = consumer;
        model = new MyHashTable();
    }

    @Override
    public void run() {

        List<FileModel> txts = Arrays.stream(
                Objects.requireNonNull(subFolder.list((file, s) -> s.matches("^.*\\.txt$")))// all the *.txt file
        ).map(str -> new FileModel(new File(subFolder, str), model, consumer))
                .collect(Collectors.toList()); //toList

        amountSubTexts = txts.size();

        if(txts.size() == 0)return;// if there is no txts

        new ThreadPool().executeAndAwait(txts); // executes all txt threads and waits until they finish
    }

    public String getNameFolder(){
        return subFolder.getName();
    }

    public MyHashTable getHistogram() {
        return model;
    }

    public int getAmountSubTexts() {
        return amountSubTexts;
    }
}
