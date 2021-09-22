package feelingsproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class FileModel extends Thread {

    private final File file; // txt to read
    private final MyHashTable model; // histogram / frequency table
    private final BiConsumer<String, MyHashTable> consumer;

    public FileModel(File file, MyHashTable model, BiConsumer<String, MyHashTable> consumer){
        super();
        this.model = model;
        this.file = file;
        this.consumer = consumer;
    }

    public FileModel(File file, BiConsumer<String, MyHashTable> consumer){
        super();
        this.consumer = consumer;
        this.model = new MyHashTable();
        this.file = file;
    }

    @Override
    public void run() {

        try (Scanner scanner = new Scanner(file)){

            //read the whole file
            scanner.useDelimiter("\\Z");
            if(!scanner.hasNext()) return; // if txt is empty
            String fileText = scanner.next();

            consumer.accept(fileText, model); // separate by words or Ngram

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public MyHashTable getHistogram(){
        return model;
    }

}
