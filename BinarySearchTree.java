import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

public class BinarySearchTree {
    public static void main(String[] args) {
        Tree t = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        t.remove(7);
        System.out.println(t.toString());
        Tree f = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(4,2,3,10,11,9)));
        System.out.println(f.toString());
        f.remove(10);
        System.out.println(f.toString());

        while(true) {
            menu();
        }
    }

    static void menu() {
        var scanner = new Scanner(System.in);
        System.out.println("1) Create a binary search tree\r\n" + //
                        "2) Add a node\r\n" + //
                        "3) Delete a node\r\n" + //
                        "4) Print nodes by InOrder\r\n" + //
                        "5) Print nodes by PreOrder\r\n" + //
                        "6) Print nodes by PostOrder\r\n" + //
                        "7) Exit program");
        var choice = scanner.nextInt();
        switch(choice) {
            case 7 -> {
                scanner.close();
                System.exit(0);
            }
            default -> System.out.println("Invalid option selected.");
        }
    }
    
}

class Tree<T extends Comparable<T>> {
    private Node<T> root; 
    private int depth;
    Tree(Iterable<T> initial) {
        for(var i : initial) {
            add(i);
        }
    }
    
    public Tree<T> add(T t) {
        if(null == root) {
            root = new Node<T>(t);
        } else {
            root.add(t);
        }
        return null;
    }

    public void remove(T t) {
        root.remove(t);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(root.toString());
        return sb.toString();
    }   

    public List<Node<T>> inorder() {
        return root.inorder();
    }

    
    public List<Node<T>> preorder() {
        return root.preorder();
    }

    
    public List<Node<T>> postorder() {
        return root.postorder();
    }

}
class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T t;
    private Node parent;
    private Node<T> left;
    private Node<T> right;
    Node(T t) {
        this.t = t;
    }
    Node(Node<T> parent, T t) {
        this.t = t;
        this.parent = parent;
    }
    public T getValue() {
        return t;
    }
    public int getChildrenCount() {
        return (null == left ? 0 : 1) + (null == right ? 0 : 1);
    }
    public void add(T addition) {
        if(0 < t.compareTo(addition)) {
            if(left == null)
                addLeft(addition);
            else
                left.add(addition);
        } else if (0 > t.compareTo(addition)) {
            if(right == null)
                addRight(addition);
            else
                right.add(addition);
        }
    }

    public void remove(T removal) {
        var node = new Node(removal);
        if(0 == this.compareTo(node)) {
            this.left = null;
            this.right = null;
        } else if(0 < this.compareTo(node)) {
            if(0 == this.getLeft().compareTo(node)) {
                this.left = null;
            } else {
                this.getLeft().remove(removal);
            }
        } else if(0 > this.compareTo(node)) {
            if(0 == this.getRight().compareTo(node)) {
                this.right = null;
            } else {
                this.getRight().remove(removal);
            }
        }
    }

    public List<Node<T>> inorder() {
        return this.inorder(null);
    }

    public List<Node<T>> inorder(List<Node<T>> accumulator) {
        if(null == accumulator)
            accumulator = new ArrayList<Node<T>>();
        if(this.getLeft() != null)
            this.getLeft().inorder(accumulator);
        accumulator.add(this);
        if(this.getRight() != null)
            this.getRight().inorder(accumulator);
        return accumulator;
    }

    public List<Node<T>> preorder() {
        return this.preorder(null);
    }

    public List<Node<T>> preorder(List<Node<T>> accumulator) {
        if(null == accumulator)
            accumulator = new ArrayList<Node<T>>();
        accumulator.add(this);
            if(this.getLeft() != null)
            this.getLeft().preorder(accumulator);
        if(this.getRight() != null)
            this.getRight().preorder(accumulator);
        return accumulator;
    }

    public List<Node<T>> postorder() {
        return this.postorder(null);
    }

    public List<Node<T>> postorder(List<Node<T>> accumulator) {
        if(null == accumulator)
            accumulator = new ArrayList<Node<T>>();
        if(this.getLeft() != null)
            this.getLeft().postorder(accumulator);
        if(this.getRight() != null)
            this.getRight().postorder(accumulator);
        accumulator.add(this);
        return accumulator;
    }
    public Node<T> predecessor() {
        return this.predecessor(null);
    }
    public Node<T> predecessor(Node<T> key) {
        if(key == null) {
            key = this;
        }
        List<Node<T>> inorder = inorder(null);
        if(inorder.get(inorder.indexOf(key) - 1) != null)
            return inorder.get(inorder.indexOf(key) - 1);
        else return null;
    }

    public Node<T> successor() {
        return this.successor(null);
    }

    public Node<T> successor(Node<T> key) {
        if(key == null) {
            key = this;
        }
        List<Node<T>> inorder = inorder(null);
        if(inorder.get(inorder.indexOf(key) - 1) != null)
            return inorder.get(inorder.indexOf(key) + 1);
        else return null;    }

    public Node<T> getLeft() {
        return left;
    }
    public Node<T> getRight() {
        return right;
    }    
    public void addLeft(T left) {
        if(null == this.left) {
            this.left = new Node(left);
        } else {
            // throw new IndexOutOfBoundsException("Node already has left child.");
        }
        
    }
    public void addRight(T right) {
        if(null == this.right) {
            this.right = new Node(right);
        } else {
            // throw new IndexOutOfBoundsException("Node already has right child.");
        }
    }
    @Override
    public int compareTo(Node<T> other) {
        return t.compareTo(other.getValue());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(this.stringify(""));
        return sb.toString();
    }   

    public String stringify(String indent) {
        var sb = new StringBuilder(this.getValue().toString());
        
        if(this.getLeft() != null) {
            sb.append("\n" + indent + "|");
            sb.append("\n" + indent + "+-" + "-" + this.getLeft().stringify(indent+"   "));
        }
        if(this.getRight() != null) {
            sb.append("\n" + indent + "|");
            sb.append("\n" + indent + "+-" + "-" + this.getRight().stringify(indent+"   "));
        }
        return sb.toString();
    }   
}
