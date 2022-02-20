import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Utils {

    private static File validsFile = new File("src/words.txt");
    private static File answersFile = new File("src/wordles.txt");
    private static int validsSize = sizeOfFile(Source.VALIDS);
    private static int answersSize = sizeOfFile(Source.ANSWERS);

    public enum Source {
        VALIDS, ANSWERS;
    }

    public static LinkedList<AI.Word> fileToList(Source src) {
        LinkedList<AI.Word> list = new LinkedList<>();
        try {
            Scanner scan = new Scanner(validsFile);
            while(scan.hasNextLine()) {
                list.add(new AI.Word(scan.nextLine().toUpperCase()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String[] fileToArray(Source src) {
        try {
            Scanner scan = new Scanner(validsFile);
            String array[] = new String[sizeOfFile(Source.VALIDS)];
            for(int i=0; scan.hasNextLine(); i++)
                array[i]=scan.nextLine().toUpperCase();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String randomFrom(Source src) {
        Scanner scan = getScanner(src);
        int fileSize = (src == Source.VALIDS) ?validsSize :answersSize;
        int r = (int)(Math.random() * fileSize);
        while(--r > 0)
            scan.nextLine();
        return scan.nextLine().toUpperCase();
    }

    private static int sizeOfFile(Source src) {
        Scanner scan = getScanner(src);
        int size=0;
        while(scan.hasNextLine()) {
            scan.nextLine();
            size++;
        }
        return size;
    }

    private static Scanner getScanner(Source src) {
        Scanner scan;
        if(src == Source.VALIDS) {
            try {
                scan = new Scanner(validsFile);
                return scan;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                scan = new Scanner(answersFile);
                return scan;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}