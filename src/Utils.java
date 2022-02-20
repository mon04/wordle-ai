import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Utils {
    public static LinkedList<AI.Word> getListFromFile(String filePath) {
        LinkedList<AI.Word> list = new LinkedList<>();
        try {
            Scanner scan = new Scanner(new File(filePath));
            while(scan.hasNextLine()) {
                list.add(new AI.Word(scan.nextLine().toUpperCase()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
