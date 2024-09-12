package com.mksybr.sdc.cs201;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BinarySearchTree {
    static private Tree<Integer> tree = new Tree<Integer>();
    static private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Tree t = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        // System.out.println(t.toStringInOrder());
        // t.remove(7);
        // System.out.println(t.toString());
        // Tree f = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(4,2,3,10,11,9)));
        // System.out.println(f.toString());
        // f.remove(10);
        // System.out.println(f.toString());
        // var f = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(4,2,3,10,11,9)));
        // System.out.println(f.toString());
        // System.out.println(f.inorder());
        // System.out.println(f.toStringPreOrder());
        // System.out.println(f.toStringPostOrder());

        while(true) {
            System.out.println("1) Create a binary search tree\r\n" + //
            "2) Add a node\r\n" + //
            "3) Delete a node\r\n" + //
            "4) Print nodes by InOrder\r\n" + //
            "5) Print nodes by PreOrder\r\n" + //
            "6) Print nodes by PostOrder\r\n" + //
            "7) Exit program");
            var choice = scanner.nextInt();
            menu(choice);
        }
    }

    static void menu(int choice) {
        switch(choice) {
            case 1, 2 -> {
                readIntegers().forEach(i -> {
                    tree.add(i);
                });
            } 
            case 3 -> {
                readIntegers().forEach(i -> {
                    tree.remove(i);
                });
            } 
            case 4 -> {
                System.out.println(tree.inorder());
            } 
            case 5 -> {
                System.out.println(tree.preorder());
            } 
            case 6 -> {
                System.out.println(tree.postorder());
            } 
            case 7 -> {
                scanner.close();
                System.exit(0);
            }
        }
    }
    static List<Integer> readIntegers() {
        List<Integer> result = Collections.emptyList();
        if(scanner.hasNextLine()) {
            try {
                result = Arrays.asList(scanner.nextLine().split(" +"))
                .stream()
                .map(i -> Integer.parseInt(i))
                .toList();        
            } catch(java.lang.NumberFormatException e) {
                return readIntegers();
            }
        }
        return result;
    }
}

class Tree<T extends Comparable<T>> implements Comparable<Tree<T>> {
    public Node<T> root; 
    Tree() {}
    Tree(Node<T> root) {
        this.root = root;
    }
    Tree(Iterable<T> initial) {
        for(var i : initial) {
            add(i);
        }
    }
    
    public void add(T t) {
        if(null == root) {
            root = new Node<T>(t);
        } else {
            root.add(t);
        }
    }

    public void remove(T t) {
        this.root.remove(t, null);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(root.toString());
        return sb.toString();
    }   

    public List<T> inorder() {
        return root.inorder();
    }

    
    public List<T> preorder() {
        return root.preorder();
    }

    
    public List<T> postorder() {
        return root.postorder();
    }
    @Override
    public int compareTo(Tree<T> other) {
        
        var accumulator = 0;
        return this.compareTo(other, accumulator);
    }

    private int compareTo(Tree<T> other, int accumulator) {
        accumulator += this.root.compareTo(other.root);
        if(this.root.getLeft() != null && other.root.getLeft() != null)
            accumulator += this.root.getLeft().compareTo(other.root.getLeft());
        else if (this.root.getLeft() != null)
            accumulator += -1;
        else if(other.root.getLeft() != null)
            accumulator += 1;
        
        if(this.root.getRight() != null && other.root.getRight() != null)
            accumulator += this.root.getRight().compareTo(other.root.getRight());
        else if(this.root.getRight() != null)
            accumulator += -1;
        else if(other.root.getRight() != null)
            accumulator += 1;
            
        return Long.signum(accumulator);
    }
}

class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T t;
    public Node<T> left;
    public Node<T> right;
    Node(T t) {
        this.t = t;
    }
    public T getValue() {
        return t;
    }
    public int getChildrenCount() {
        return (null == left ? 0 : 1) + (null == right ? 0 : 1);
    }
    public void add(T addition) {
        if(this.t == null)
            this.t = addition;

        if(0 < t.compareTo(addition)) {
            if(left == null)
                addLeft(addition);
            else
                left.add(addition);
        } else if (0 >= t.compareTo(addition)) {
            if(right == null)
                addRight(addition);
            else
                right.add(addition);
        }
    }

    public void remove(T t, Node<T> parent) {
        Node<T> removal = new Node<T>(t);
        if(0 < this.compareTo(removal)) {
            if(this.getLeft() != null) {
                this.left.remove(t, this);
            } else
                return;
        } else if(0 > this.compareTo(removal)) {
            if(this.getRight() != null) {
                this.right.remove(t, this);
            } else 
                return;
        } else {
            if (this.getChildrenCount() == 2) {
                this.t = parent.successor();
            } else if(parent.left.t == this.t) {
                parent.left = (this.left != null) ? left : right;
            } else if(parent.right.t == this.t) {
                parent.right = (this.left != null) ? left : right;
            } 
            return;
        }
    }
    
    public List<T> inorder() {
        return this.inorder(null);
    }

    public List<T> inorder(List<T> accumulator) {
        if(null == accumulator)
            accumulator = new ArrayList<T>();
        if(this.getLeft() != null)
            this.getLeft().inorder(accumulator);
        accumulator.add(this.t);
        if(this.getRight() != null)
            this.getRight().inorder(accumulator);
        return accumulator;
    }

    public List<T> preorder() {
        return this.preorder(null);
    }

    public List<T> preorder(List<T> accumulator) {
        if(null == accumulator)
            accumulator = new ArrayList<T>();
        accumulator.add(this.getValue());
            if(this.getLeft() != null)
            this.getLeft().preorder(accumulator);
        if(this.getRight() != null)
            this.getRight().preorder(accumulator);
        return accumulator;
    }

    public List<T> postorder() {
        return this.postorder(null);
    }

    public List<T> postorder(List<T> accumulator) {
        if(null == accumulator)
            accumulator = new ArrayList<T>();
        if(this.getLeft() != null)
            this.getLeft().postorder(accumulator);
        if(this.getRight() != null)
            this.getRight().postorder(accumulator);
        accumulator.add(this.getValue());
        return accumulator;
    }
    public T predecessor() {
        return this.predecessor(null);
    }
    public T predecessor(Node<T> key) {
        if(key == null) {
            key = this;
        }
        List<T> inorder = inorder(null);
        if(inorder.get(inorder.indexOf(key) - 1) != null)
            return inorder.get(inorder.indexOf(key) - 1);
        else return null;
    }

    public T successor() {
        return this.successor(null);
    }

    public T successor(Node<T> key) {
        if(key == null) {
            key = this;
        }
        List<T> inorder = inorder(null);
        try {
            if(inorder.get(inorder.indexOf(key) - 1) != null)
                return inorder.get(inorder.indexOf(key) + 1);
            else return null;    
        } catch (IndexOutOfBoundsException e) {
            return inorder.get(0);
        }
    
    }

    public Node<T> getLeft() {
        return left;
    }
    public Node<T> getRight() {
        return right;
    }    
    public void addLeft(T left) {
        if(null == this.left) {
            this.left = new Node<T>(left);
        } else {
            // throw new IndexOutOfBoundsException("Node already has left child.");
        }
        
    }
    public void addRight(T right) {
        if(null == this.right) {
            this.right = new Node<T>(right);
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
