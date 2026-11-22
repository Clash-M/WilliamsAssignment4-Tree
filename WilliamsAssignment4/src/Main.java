import java.util.Stack;
void main() {
    AVLTree tree = new AVLTree();

    tree.insert("30");
    tree.insert("30");
    tree.insert("20");
    tree.insert("10");
    String lastInsertedKey = "5";
    tree.insert(lastInsertedKey);

    Stack<AVLTreeNode> nodes = new Stack<>();
    Stack<String> sides = new Stack<>();
    if (tree.getRoot() != null) {
        nodes.push(tree.getRoot());
        sides.push("root");
    }

    while (!nodes.isEmpty()) {
        AVLTreeNode node = nodes.pop();
        String side = sides.pop();
        System.out.println("Side: " + side + ", data: " + node.getData()
                + ", height: " + node.getHeight());
        if (node.getRightNode() != null) {
            nodes.push(node.getRightNode());
            sides.push("right");
        }
        if (node.getLeftNode() != null) {
            nodes.push(node.getLeftNode());
            sides.push("left");
        }
    }
}
