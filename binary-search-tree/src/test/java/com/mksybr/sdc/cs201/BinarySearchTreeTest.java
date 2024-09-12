package com.mksybr.sdc.cs201;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeSearchTests {
    @Test
    public void removal() {
        // https://www.algolist.net/Data_structures/Binary_search_tree/Removal
        var subject = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,12,9,21,19,25))); 
        var expected = new Tree<Integer>(new ArrayList<Integer>(Arrays.asList(5,2,-4,3,19,9,21,25))); 
        
        subject.remove(19);
        assertEquals(subject, expected);
    }
}
