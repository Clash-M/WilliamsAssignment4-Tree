import java.util.ArrayList;
import java.util.Scanner;
void main() {
    AVLTree tree = new AVLTree();
    ArrayList <String> listOLines = new ArrayList<>();
    Scanner scnr = new Scanner(System.in);
    String userInput;
    
    
    System.out.print("Enter file name: ");
     userInput = scnr.nextLine();
    do {
         

         listOLines = ReadAndMakeList.readFile(userInput);

        for (String line : listOLines) {
            String[] words = line.trim().split("[\\s,.!?]+");
            for (String word : words) {
                tree.insert(word.toLowerCase().strip());
            }
        }

        AVLTreeNode rootOTree = tree.getRoot();
        System.out.println("IN-ORDER PRINT ALPHABETICAL");
        tree.printInOrder(rootOTree);

        System.out.println();
        System.out.println();
        System.out.println("POST-ORDER PRINT");
        tree.printPostOrder(rootOTree);


        do {
            System.out.print("Would you like to insert or delete a word? (i/d): ");
            userInput = scnr.nextLine();
            if(userInput.equalsIgnoreCase("i")){
                System.out.print("Enter word to insert: ");
                userInput = scnr.nextLine();
                tree.insert(userInput.toLowerCase().strip());
                System.out.println("Word inserted.");
            } else if (userInput.equalsIgnoreCase("d")){
                System.out.print("Enter word to delete: ");
                userInput = scnr.nextLine();
                tree.deletion(userInput.toLowerCase().strip());
                System.out.println("Word deleted.");
            } else {
                System.out.println("Invalid input. Please enter 'i' or 'd'.");
            }
        } while (userInput.equalsIgnoreCase("exit"));

        System.out.println();
        System.out.println();
        

         userInput = scnr.nextLine();
         
    } while (!userInput.equalsIgnoreCase("n"));
    
    

}
