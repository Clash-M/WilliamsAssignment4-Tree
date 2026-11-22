import java.util.HashMap;
import java.util.Map;


public class AVLTreeNode {
    private Map<String, Integer> data;
    private AVLTreeNode leftNode;
    private AVLTreeNode rightNode;
    private int height;



    public AVLTreeNode(String key, int value){
        data = new HashMap<>();
        data.put(key, value);
        height = 0;

    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }

    public void setLeftNode(AVLTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(AVLTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public AVLTreeNode getLeftNode() {
        return leftNode;
    }

    public AVLTreeNode getRightNode() {
        return rightNode;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
