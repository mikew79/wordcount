import mikew79.wordcount.WordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Count {
    public static void main(String[] args) {

        // we always require one argument the file to parse
        if (args.length != 1) {
            System.out.println("Error: Missing or incorrect arguments");
            System.out.println("USAGE count <filename>");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (file.exists()) { // the file passed in exists
            WordCounter wc = new WordCounter();

            try {
                Scanner input = new Scanner(file); // Create a scanner to pass the file contents to the parse function
                if (wc.parse(input) > 0) {
                    wc.printCounts();
                } else {
                    System.out.println("No Words found in file: " + args[0]);
                    System.exit(0);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Error: parsing file: " + args[0]);
                System.out.println(ex.toString());
                System.exit(1);
            }
        }
        else {
            System.out.println("Error: File: " + args[0] + " does not exist");
            System.out.println("USAGE count <filename>");
            System.exit(1);
        }
    }
}