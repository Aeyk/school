package com.mksybr.sdc.cs201;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeSearchTests {
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
}
