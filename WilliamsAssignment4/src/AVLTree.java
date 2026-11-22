import java.util.LinkedList;
import java.util.Stack;
import java.math.*;
public class AVLTree {

    private AVLTreeNode root;

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    private int height(AVLTreeNode Node){
        if(Node == null){
            return -1;
        }

        return Node.getHeight();
    }

    public void insert(String key){
        Stack<AVLTreeNode> visitedNodes = new Stack<>();
        AVLTreeNode currentNode = root;

        AVLTreeNode NodeToAdd = new AVLTreeNode(key, 1);
        boolean inserted = false;

// if there is nothing in the tree

        if(root == null){

            root = new AVLTreeNode(key, 1);
            updateHeight(root);
            visitedNodes.push(root);
            System.out.println("Inserted AT ROOT");
            return;
        }



// loop to find where I should add
        while(currentNode != null && !inserted) {
            System.out.println("Stack size = " + visitedNodes.size());
            String nodeKey = currentNode.getData().keySet().iterator().next();
            visitedNodes.push(currentNode);
// if I find key already there I update the value

            if (key.compareTo(nodeKey) == 0) {

                currentNode.getData().put(key, currentNode.getData().get(key) + 1);
                return;

            }
//add to right
            else if (key.compareTo(nodeKey) > 0) {

                if (currentNode.getRightNode() == null) {

                    currentNode.setRightNode(NodeToAdd);

                    System.out.println("Inserted RIGHT");
                    inserted = true;

                }
                else {
                    currentNode = currentNode.getRightNode();
                }
            }
// add to left
            else if (key.compareTo(nodeKey) < 0) {


                if (currentNode.getLeftNode() == null) {

                    currentNode.setLeftNode(NodeToAdd);

                    System.out.println("Inserted LEFT");
                    inserted = true;

                }
                else {
                    currentNode = currentNode.getLeftNode();
                }
            }

        }
            System.out.println("Starting rebalance");

            while (!(visitedNodes.isEmpty())) {

                AVLTreeNode currentNodeVisited = visitedNodes.pop();
                System.out.println(currentNodeVisited.getData());


                updateHeight(currentNodeVisited);
                AVLTreeNode left = currentNodeVisited.getLeftNode();
                AVLTreeNode right = currentNodeVisited.getRightNode();

                System.out.println("Node: " + key);
                System.out.println("Height: " + currentNodeVisited.getHeight());


                int bf = height(left) - height(right);
                System.out.println("BF: " + bf);
                if (bf > 1) {
                    int leftBf = height(left.getLeftNode()) - height(left.getRightNode());

                    //LL case
                    if (leftBf >= 0) {
                        System.out.println("LL");
                        AVLTreeNode newRoot = RRotation(currentNodeVisited);
                        if (visitedNodes.isEmpty()) {
                            root = newRoot;
                        } else {
                            AVLTreeNode parent = visitedNodes.peek();

                            if (parent.getLeftNode() == currentNodeVisited) {
                                parent.setLeftNode(newRoot);
                            } else {
                                parent.setRightNode(newRoot);
                            }
                        }
                    }
                    //LR case
                    else if (leftBf < 0) {
                        System.out.println("LR");
                        currentNodeVisited.setLeftNode(LRotation(currentNodeVisited.getLeftNode()));
                        AVLTreeNode newRoot = RRotation(currentNodeVisited);

                        if (visitedNodes.isEmpty()) {
                            root = newRoot;
                        } else {
                            AVLTreeNode parent = visitedNodes.peek();

                            if (parent.getLeftNode() == currentNodeVisited) {
                                parent.setLeftNode(newRoot);
                            } else {
                                parent.setRightNode(newRoot);
                            }
                        }

                    }

                }

                if (bf < -1) {
                    int rightBf = height(right.getLeftNode()) - height(right.getRightNode());
                    //RR case
                    if (rightBf <= 0) {
                        System.out.println("RR");
                        AVLTreeNode newRoot = LRotation(currentNodeVisited);
                        if (visitedNodes.isEmpty()) {
                            root = newRoot;
                        } else {
                            AVLTreeNode parent = visitedNodes.peek();

                            if (parent.getLeftNode() == currentNodeVisited) {
                                parent.setLeftNode(newRoot);
                            } else {
                                parent.setRightNode(newRoot);
                            }
                        }
                    }
                    //RL case
                    else if (rightBf > 0) {
                        System.out.println("RL");
                        currentNodeVisited.setRightNode(RRotation(currentNodeVisited.getRightNode()));
                        AVLTreeNode newRoot = LRotation(currentNodeVisited);

                        if (visitedNodes.isEmpty()) {
                            root = newRoot;
                        } else {
                            AVLTreeNode parent = visitedNodes.peek();

                            if (parent.getLeftNode() == currentNodeVisited) {
                                parent.setLeftNode(newRoot);
                            } else {
                                parent.setRightNode(newRoot);
                            }
                        }
                    }

                }

            }

    }

    public void

    public AVLTreeNode RRotation(AVLTreeNode node){

        if(node == null || node.getLeftNode() == null) {
            return node;
        }

        AVLTreeNode leftChild = node.getLeftNode();
        AVLTreeNode leftRightChild = node.getLeftNode().getRightNode();

        node.setLeftNode(leftRightChild);
        leftChild.setRightNode(node);

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;

    }

    public AVLTreeNode LRotation(AVLTreeNode node){

        if(node == null || node.getRightNode() == null) {
            return node;
        }

        AVLTreeNode rightChild = node.getRightNode();
        AVLTreeNode rightLeftChild = node.getRightNode().getLeftNode();

        node.setRightNode(rightLeftChild);
        rightChild.setLeftNode(node);

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;

    }

    public void updateHeight(AVLTreeNode node){
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
    }


}
