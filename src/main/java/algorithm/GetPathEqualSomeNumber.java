package algorithm;

/**
 * Created by wangwei110 on 2018/5/21.
 * 在一棵二叉树中，找寻节点和为某个值的路径
 */
import java.util.LinkedList;

class BinaryNode{
    BinaryNode left;
    BinaryNode right;
    int value;
    BinaryNode(){
        left = null;
        right = null;
    }
    BinaryNode(int value){
        this.value = value;
        left = null;
        right = null;
    }

    private static int sum = 0;
    private static LinkedList<Integer> path = new LinkedList<Integer>();
    void PrintAllPathsEqualsSomeNumber(int num){
        path.add(value);
        sum += value;
        if(sum == num)
            System.out.println(path.toString());
        if(left != null){
            left.PrintAllPathsEqualsSomeNumber(num);
            sum -= left.value;
            path.remove(path.size() - 1);
        }
        if(right != null){
            right.PrintAllPathsEqualsSomeNumber(num);
            sum -= right.value;
            path.remove(path.size() - 1);
        }
    }
}

public class GetPathEqualSomeNumber {
    public static void main(String[] args) {
        /*******************build tree*********************************/
        BinaryNode root = new BinaryNode(4);
        root.left = new BinaryNode(2);
        root.left.left = new BinaryNode(1);
        root.left.right = new BinaryNode(3);
        root.left.right.left = new BinaryNode(6);
        root.right = new BinaryNode(6);
        root.right.left = new BinaryNode(5);
        root.right.right = new BinaryNode(7);
        root.right.right.right = new BinaryNode(8);

        root.PrintAllPathsEqualsSomeNumber(15);
        /***************************************************************/
    }
}
