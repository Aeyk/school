package com.mksybr.sdc.cs201;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


// [ERROR] Failures:
// [ERROR]   TextSearcherTest.testMatch_1:35 expected: <16> but was: <18>
// [ERROR]   TextSearcherTest.testMatch_2:41 expected: <19> but was: <20>
// [ERROR]   TextSearcherTest.testNonMatch_2:59 expected: <-1> but was: <7>

class TextSearcherTest {
    @Test
    public void testGoodCharacterTable_0() {
        var subject = Search.makeBadCharacterTable("hello world");
        var expected = new HashMap<Character, Integer>(Map.of(' ', 5, 'r', 2, 'd', 0, 'e', 9, 'w', 4, 'h', 10,'l', 1, 'o', 3));
        assertEquals(true, expected.equals(subject));
    }
    @Test
    public void testGoodCharacterTable_1() {
        var subject = Search.makeBadCharacterTable("example");
        var expected = new HashMap<Character, Integer>(Map.of('e', 0, 'x', 5, 'a', 4, 'm', 3, 'p', 2, 'l', 1));
        assertEquals(true, expected.equals(subject));
    }
    @Test
    public void testMatch_0() {
        var subject = Search.indexAt("hello world", "wor");
        var expected = 5;
        assertEquals(expected, subject);
    }
    @Test
    public void testMatch_1() {
        var subject = Search.indexAt("Here is a simple example.", "example");
        var expected = 16;
        assertEquals(expected, subject);
    }
    @Test
    public void testMatch_2() {
        var subject = Search.indexAt("Here is the simple example.", "example");
        var expected = 18;
        assertEquals(expected, subject);
    }
    @Test
    public void testMatch_3() {
        var subject = Search.indexAt("Hello world", "Hello");
        var expected = 0;
        assertEquals(expected, subject);
    }
    @Test
    public void testNonMatch_0() {
        var subject = Search.indexAt("hello world", "worf");
        var expected = -1;
        assertEquals(expected, subject);
    }
    @Test
    public void testNonMatch_1() {
        var subject = Search.indexAt("hello world", "wofr");
        var expected = -1;
        assertEquals(expected, subject);
    }
    @Test
    public void testNonMatch_2() {
        var subject = Search.indexAt("hello world", "dor");
        var expected = -1;
        assertEquals(expected, subject);
    }
}
