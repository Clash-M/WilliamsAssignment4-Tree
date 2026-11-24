import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList <String> listOLines = new ArrayList<>();
        Scanner scnr = new Scanner(System.in);
        String userInput;
        AVLTree tree = new AVLTree();
    
    System.out.print("Enter file name (type 'n' to quit): ");
     userInput = scnr.nextLine();

     listOLines = ReadAndMakeList.readFile(userInput);

        for (String line : listOLines) {
            String[] words = line.trim().split("[\\s,.!?]+");
            for (String word : words) {
                tree.insert(word.toLowerCase().strip());
            }
        }
    
    while (!userInput.equals("n")) {
        
        
        System.out.println("1. Print the tree in order");
        System.out.println("2. Print the tree in post-order");
        System.out.println("3. Add a word to the tree");
        System.out.println("4. Remove a word from the tree");
        System.out.println("5. Search for a word in the tree");
        System.out.println("6. Quit");
        System.out.print("Choose an option: ");
        int option = scnr.nextInt();

       if (option == 6) {
        break;
       }
        switch (option) {
            case 1:
                System.out.println("--------------------------------------------------------------------");
                tree.printInOrder(tree.getRoot());
                System.out.println("--------------------------------------------------------------------");
                break;
            case 2:
                System.out.println("--------------------------------------------------------------------");
                tree.printPostOrder(tree.getRoot());
                System.out.println("--------------------------------------------------------------------");
                break;
            case 3:
                System.out.print("Enter a word to add: ");
                String wordToAdd = scnr.next();
                tree.insert(wordToAdd.toLowerCase().strip());
                break;
            case 4:
                System.out.print("Enter a word to remove: ");
                String wordToRemove = scnr.next();
                tree.deletion(wordToRemove.toLowerCase().strip());
                break;
            case 5:
                System.out.print("Enter a word to search for: ");
                String wordToSearch = scnr.next();
                
                tree.search(wordToSearch);
                
                break;
            case 6:
                System.out.println("Quitting...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
        
    }
        
    
    
}
}