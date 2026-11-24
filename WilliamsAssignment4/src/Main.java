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
        System.out.println("PRE-ORDER PRINT");
        tree.printPreOrder(rootOTree);


         userInput = scnr.nextLine();
         
    } while (!userInput.equalsIgnoreCase("n"));
    
    

}
