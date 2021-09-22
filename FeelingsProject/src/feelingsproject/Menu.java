package feelingsproject;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    // request the folder and validates it
    public FileManager folderMenu(){
        FileManager fileManager = null;

        while(fileManager == null){
            System.out.println("Type the path of the folder: ");
            String path = scanner.next();

            fileManager = FileManager.getFileManager(path);
        }

        return fileManager;
    }

    public int getOption(int min, int max, String message){
        int option;

        do{
            System.out.println(message);
            option = scanner.nextInt();
        }while (option < min || option > max);

        return option;
    }

    public int nGramMenu() {
        return getOption(1, 10000, "Type the N-Gram:\t");
    }

    public int mainMenu() {

        System.out.println("\n\n[0]\tExit");
        System.out.println("[1]\tCosine similarity");
        System.out.println("[2]\tNaive Bayes");
        System.out.println("[3]\tLogistic Regression");
        System.out.println("[4]\tK-nearest neighbors");


        int option = getOption(0, 4, "Option:\t");

        if (option == 0) scanner.close(); // close stream

        return option;
    }

}
