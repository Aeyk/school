package com.mksybr.sdc.cs201;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeSearchTests {
    @Test
    public void removeRoot() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>(5)); 

        subject.remove(5);
        assertEquals(0, expected.compareTo(subject));
    }

    @Test
    public void removeRoot_0() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,21,19,25))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>()); 

        subject.remove(5);
        assertEquals(0, expected.compareTo(subject));
    }
  
    @Test
    public void removeRoot_1() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(2, 3, 4))); 

        subject.remove(1);
        assertEquals(0, expected.compareTo(subject));
    }
  
    @Test
    public void removeLeafNode() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,21,19,25))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,21,19))); 

        subject.remove(25);
        assertEquals(0, expected.compareTo(subject));
    }

    @Test
    public void removeNodeWithOneChild() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,21,19,25))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,19,25))); 
        
        subject.remove(21);
        assertEquals(0, expected.compareTo(subject));
    }


    @Test
    public void removeNodeWithTwoChildren() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,21,19,25))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,19,25))); 
        
        subject.remove(21);
        assertEquals(0, expected.compareTo(subject));
    }

    @Test
    public void creation() {
        var subject = new Tree<Integer>(new Node<Integer>(1));
        var expected = new Tree<Integer>(Arrays.asList(1));
        
        assertEquals(0, expected.compareTo(subject));
        assertEquals(0, subject.compareTo(expected));

        subject = new Tree<Integer>(new Node<Integer>(1));
        subject.add(2);
        expected = new Tree<Integer>(Arrays.asList(1,2));
        assertEquals(0, expected.compareTo(subject));
    }
    @Test
    public void preorder() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3, 6, 5, 7))); 
        var expected = Arrays.asList(4, 2, 1, 3, 6, 5, 7);
        
        assertEquals(true, expected.equals(subject.preorder()));
    }
    
    @Test
    public void postorder() {
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3, 6, 5, 7))); 
        var expected = Arrays.asList(1, 3, 2, 5, 7, 6, 4);
        
        assertEquals(true, expected.equals(subject.postorder()));
    }
}
