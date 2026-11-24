import java.util.Stack;
public class AVLTree {

    private AVLTreeNode root;

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    public AVLTreeNode getRoot() {
        return root;
    }
    //Helper method used to calculate balance factor and not cause a nullPointerException
    private int height(AVLTreeNode Node){
        if(Node == null){
            return -1;
        }

        return Node.getHeight();
    }
    //Inserts node based on string given
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
            
            return;
        }



// loop to find where I should add
        while(currentNode != null && !inserted) {
            
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

                    
                    inserted = true;

                }
                else {
                    currentNode = currentNode.getLeftNode();
                }
            }

        }
            //REBALANCE

            while (!(visitedNodes.isEmpty())) {

                AVLTreeNode currentNodeVisited = visitedNodes.pop();
                


                updateHeight(currentNodeVisited);
                AVLTreeNode left = currentNodeVisited.getLeftNode();
                AVLTreeNode right = currentNodeVisited.getRightNode();

                


                int bf = height(left) - height(right);
                
                if (bf > 1) {
                    int leftBf = height(left.getLeftNode()) - height(left.getRightNode());

                    //LL case
                    if (leftBf >= 0) {
                        
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

    //Searches from user inputed string
    public void search(String targetKey) {
        AVLTreeNode currentNode = root;
        String nodeKey = null;
        while(currentNode != null){

        nodeKey = currentNode.getData().keySet().iterator().next();
        
        if (targetKey.compareTo(nodeKey) == 0){
            System.out.println("Found key: " + nodeKey + " with value: " + currentNode.getData().get(nodeKey));
            return;
        }

        else if (targetKey.compareTo(nodeKey) > 0){
            currentNode = currentNode.getRightNode();
            System.out.println("Going right");
        }

        else if (targetKey.compareTo(nodeKey) < 0){
            currentNode = currentNode.getLeftNode();
            System.out.println("Going left");
        }

        else{
            System.out.println("Key not found");
            return;
        }


    }
    }

    //Deletes node based on the string data it contains
    public void deletion(String target){

        Stack<AVLTreeNode> visitedNodes = new Stack<>();
        AVLTreeNode currentNode = root;
        String nodeKey = null;
        AVLTreeNode parentNode = root;
        boolean deleted = false;

//Search
        while (currentNode != null) {
            nodeKey = currentNode.getData().keySet().iterator().next();
            visitedNodes.push(currentNode);
            
        
            if (target.compareTo(nodeKey) == 0) {
                
                //Root deletion

                if (root.getData().containsKey(target)) {
                    //root no kids
                    if(root.getLeftNode() == null && root.getRightNode() == null){
                        root = null;
                        return;
                    }

                    //root one kid
                    else if(root.getLeftNode() != null && root.getRightNode() == null){
                        root = root.getLeftNode();
                        deleted = true;
                        break;
                    }

                    else if(root.getLeftNode() == null && root.getRightNode() != null){
                        root = root.getRightNode();
                        deleted = true;
                        break;
                    }
                    //root has two kids
                    else{

                        // TODO: FIX IF ROOT HAS TWO KIDSSS

                    AVLTreeNode successor = findMin(root.getRightNode());

                    

                    if(root.getRightNode() == successor){
                        root.setData(successor.getData());
                        root.setHeight(height(root));
                        root.setRightNode(successor.getRightNode());
                        deleted = true;
                        break;
                    }

                    else{
                        AVLTreeNode parentOfsuc = findMinParent(root.getRightNode());
                        root.setData(successor.getData());
                        
                        parentOfsuc.setLeftNode(successor.getRightNode());
                        updateHeight(parentOfsuc);
                        
                        deleted = true;
                        break;
                    }

                    }
                    
                }

                //If deleted node that is not the root has no children
                if(currentNode.getLeftNode() == null && currentNode.getRightNode() == null){
                    //Child was left node
                    if (parentNode.getLeftNode() == currentNode) {
                        parentNode.setLeftNode(null);
                        deleted = true;
                        break;
                    }
                    //Child was right node
                    else if(parentNode.getRightNode() == currentNode){
                        parentNode.setRightNode(null);
                        deleted = true;
                        break;
                    }
                }

                //If deleted node has one child
                //Right node
                else if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) {
                    if(parentNode.getLeftNode() == currentNode){
                    parentNode.setLeftNode(currentNode.getLeftNode());
                        deleted = true;
                        break;
                    }

                    else{
                        parentNode.setRightNode(currentNode.getLeftNode());
                        deleted = true;
                        break;
                    }
                }
                //Left node
                else if(currentNode.getRightNode() != null && currentNode.getLeftNode() == null ){
                    if(parentNode.getRightNode() == currentNode){
                    parentNode.setRightNode(currentNode.getRightNode());
                    deleted = true;
                    break;
                    }
                    else{
                        parentNode.setLeftNode(currentNode.getRightNode());
                        deleted = true;
                        break;
                    }
                }
                //Deletion of node with two kids
                else{
                    AVLTreeNode successor = findMin(currentNode.getRightNode());
                    
                    
                    if (successor == currentNode.getRightNode()) {
                        currentNode.setData((successor.getData()));
                        currentNode.setRightNode(successor.getRightNode());
                        
                        deleted = true;
                        break;
                    }

                    else{
                        AVLTreeNode successorParent = findMinParent(currentNode.getRightNode());
                        currentNode.setData((successor.getData()));
                        successorParent.setLeftNode(successor.getRightNode());
                        updateHeight(successorParent);
                        deleted = true;
                        break;
                    }

                    
                }

        }

            else if (target.compareTo(nodeKey) > 0){
            parentNode = currentNode;
            currentNode = currentNode.getRightNode();
            
            
        }

            else if (target.compareTo(nodeKey) < 0){
            parentNode = currentNode;
            currentNode = currentNode.getLeftNode();
            
            
        }

        
            else{
            System.out.println("Deletion target not found");
            return;
        }

    }

    

    while (!(visitedNodes.isEmpty())) {

                AVLTreeNode currentNodeVisited = visitedNodes.pop();
                


                updateHeight(currentNodeVisited);
                AVLTreeNode left = currentNodeVisited.getLeftNode();
                AVLTreeNode right = currentNodeVisited.getRightNode();

                


                int bf = height(left) - height(right);
                
                if (bf > 1) {
                    int leftBf = height(left.getLeftNode()) - height(left.getRightNode());

                    //LL case
                    if (leftBf >= 0) {

                        
                        
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
                        
                        currentNodeVisited.setLeftNode(LRotation(currentNodeVisited.getLeftNode()));
                        AVLTreeNode newRoot = RRotation(currentNodeVisited);

                        if (visitedNodes.isEmpty()) {
                            root = newRoot;
                        } 
                        else {
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
                        
                        AVLTreeNode newRoot = LRotation(currentNodeVisited);
                        if (visitedNodes.isEmpty()) {
                            root = newRoot;
                        } 
                        else {
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
    
    //Rotates Right
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
    //Rotates Left 
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
    //Updates height for balancing 
    public void updateHeight(AVLTreeNode node){
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
    }

    //finds successor for deletion

    public AVLTreeNode findMin(AVLTreeNode node){
        AVLTreeNode currentNode = node;

        while(currentNode.getLeftNode() != null) {
            currentNode = currentNode.getLeftNode();
        }

        return currentNode;
    }

    //Finds the successors parent for deletion

    public AVLTreeNode findMinParent(AVLTreeNode node){
        AVLTreeNode currentNode = node;
        
        if (currentNode == null || currentNode.getLeftNode() == null) {
        return null;
        }

        while(currentNode.getLeftNode().getLeftNode() != null) {
            currentNode = currentNode.getLeftNode();
        }

        return currentNode;
    }

    //In-Order print

    public void printInOrder(AVLTreeNode node){
        if (node == null){
            return;
        }

        

        printInOrder(node.getLeftNode());

        System.out.println("Word: " + node.getData() + " Height: " + node.getHeight());

        printInOrder(node.getRightNode());
    }

    //Post-order print

    public void printPostOrder(AVLTreeNode node){
        Stack<AVLTreeNode> stackONodes = new Stack<>();

        if(node == null){
            return;
        }

        stackONodes.push(node);

        printPostOrder(node.getLeftNode());
        printPostOrder(node.getRightNode());

        for (AVLTreeNode nodesInStack : stackONodes) {
            System.out.println("Word: " + nodesInStack.getData() + " Height: " + nodesInStack.getHeight());
        }

    }
    


}
