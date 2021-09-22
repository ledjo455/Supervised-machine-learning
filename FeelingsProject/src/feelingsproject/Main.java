package feelingsproject;
import feelingsproject.Algorithms.Algorithm;
import feelingsproject.Algorithms.AlgorithmFactory;
import feelingsproject.Menu;


import java.util.Hashtable;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        FileManager fileManager = menu.folderMenu(); // request folder

        Hashtable<Integer, Algorithm> algorithms = AlgorithmFactory.getAlgorithms();

        int option;

        while((option = menu.mainMenu()) != 0) {

            System.out.println("Extracting data...");
            List<FolderModel> models = fileManager.calculateModels(algorithms.get(option).getConsumer());
            FileModel mysteryFile = fileManager.getMysteryFile();
            System.out.println("Data extracted!\n\n");

            algorithms.get(option).executeAlgorithm(models, mysteryFile);
            algorithms.get(option).printResults();
        }

    }

}