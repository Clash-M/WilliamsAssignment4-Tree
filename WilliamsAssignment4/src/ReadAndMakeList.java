import java.io.*;
import java.util.ArrayList;
public class ReadAndMakeList {
    public static ArrayList<String> readFile(String fileName){
        ArrayList<String> fileLines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = br.readLine()) != null ){
                fileLines.add(line);
            }
        } catch (IOException e) {
           System.out.println("Error reading file");
        }

     return fileLines;
    }



}
