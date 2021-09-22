package feelingsproject;
import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class FileManager {

    public static FileManager fileManager;

    public FileModel mysteryFile;
    private final File folder;

    private FileManager(File folder){
        this.folder = folder;
    }

    public static FileManager getFileManager(String folderPath) {
        File folder = new File(folderPath);
        if(validateFolder(folder)) {
            fileManager = new FileManager(folder);
            return fileManager;
        }
        return null;
    }

    private static boolean validateFolder(File folder) {

        if(!folder.isDirectory()){
            System.err.println("The given path: " + folder.getAbsolutePath() + " its not a folder");
            return false;
        }


        if(!folder.exists()){
            System.err.println("The given folder: " + folder.getAbsolutePath() + " does not exist");
            return false;
        }

        if(!folder.canRead()){
            System.err.println("Permission to read denied");
            return false;
        }

        if( !(new File(folder, "mystery.txt").exists()) ){
            System.err.println("The file mystery.txt does not exist");
            return false;
        }

        return true;
    }

    public FileModel getMysteryFile() {
        return mysteryFile;
    }

    public List<FolderModel> calculateModels(BiConsumer<String, MyHashTable> consumer) {

        // get histogram of each folder
        List<FolderModel> models = Arrays.stream(
                Objects.requireNonNull(folder.list((folder, subItem) -> new File(folder, subItem).isDirectory())) // all sub-directories
        ).map(str -> new FolderModel(new File(folder, str), consumer)) // create a VectorLanguage/thread for each directory
                .collect(Collectors.toList()); //toList

        mysteryFile = new FileModel(new File(folder, "mystery.txt"), consumer);

        List<Thread> threads = new ArrayList<>(models); // to execute the mystery file along with the folders.
        threads.add(mysteryFile);

        new ThreadPool().executeAndAwait(threads); // executes all folder threads and waits until they finish

        return models;
    }


}
