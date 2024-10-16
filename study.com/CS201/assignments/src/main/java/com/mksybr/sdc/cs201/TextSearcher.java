package com.mksybr.sdc.cs201;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

public class TextSearcher {
    
    static private Scanner scanner = new Scanner(System.in);
    static private String data = Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming").stream().reduce((a, b) -> a.concat(b)).get();
    public static void main(String[] args) {
        while(true) {
            System.out.println("1) Display the text\r\n" + 
            "2) Search\r\n" + 
            "3) Exit program\r");
            var choice = Integer.valueOf(scanner.nextInt());
            menu(choice);
        }
    }

    /** Main event loop. Take user input and interact with the tree. */
    static void menu(int choice) {
        scanner = new Scanner(System.in);
        switch(choice) {
            case 1 -> {
                System.out.println(data);
            }
            case 2 -> {
                var query = scanner.nextLine();
                System.out.println(query);
                System.out.println(Search.indexAt(data, query));
            }
            case 3 -> {
                scanner.close();
                System.exit(0);
            }
        }
    }
}

class Search {
    public static int indexAt(String haystack, String needle) {
        HashMap<Character, Integer> badCharacterTable = makeBadCharacterTable(needle);
        HashMap<Integer, Integer> goodSuffixTable = makeGoodSuffixTable(needle);
        String suffix = "";
        StringBuilder sb = new StringBuilder("");
        if(Global.DEBUG)
            System.out.printf("indexAt\n");
        int index = 0, patternIndex = needle.length() - 1;
        for(index = needle.length() - 1; index < haystack.length(); index++) {
            int lookback = 0;
            if(suffix.equals(needle)) {
                break;
            }
            Integer badCharacterSkip = badCharacterTable.get(haystack.charAt(index));
            if(null == badCharacterSkip)
                badCharacterSkip = needle.length();
            
            Integer goodSuffixSkip = goodSuffixTable.get(patternIndex);
            if(null == goodSuffixSkip)
                goodSuffixSkip = 0;
            
            lookback: {
                if(haystack.charAt(index) == needle.charAt(patternIndex)) {
                    for(lookback = 0; lookback <= patternIndex; lookback++) {
                        if(lookback >= patternIndex) {
                            if(Global.TRACE) {
                                System.out.printf("\t%s\n\t%s^\n\t%s%s\n", haystack, " ".repeat(index - lookback), " ".repeat(index - lookback), 
                                    needle.substring(patternIndex - lookback, patternIndex + 1));
                            }
                            if(haystack.charAt(index - lookback) == needle.charAt(patternIndex - lookback)) {
                                if(index == needle.length() - 1)
                                    index += 1;
                                return index - lookback - 1;
                            } else {
                                break lookback;
                            }
                        }
                        if(haystack.charAt(index - lookback) == needle.charAt(patternIndex - lookback)) {                   
                            if(Global.TRACE) {
                                System.out.printf("\t%s\n\t%s^\n\t%s%s\n", haystack, " ".repeat(index - lookback), " ".repeat(index - lookback), 
                                    needle.substring(patternIndex - lookback, patternIndex + 1));
                            }
                        } else {
                            badCharacterSkip = badCharacterTable.get(haystack.charAt(patternIndex - lookback));
                            if(null == badCharacterSkip)
                                badCharacterSkip = needle.length();

                            goodSuffixSkip = goodSuffixTable.get(index - lookback - 1);
                            if(null == goodSuffixSkip)
                                goodSuffixSkip = 0;

                            
                            index = index - lookback + 1;
                            break lookback;
                        }
                    }
                }
            }
            if(Global.TRACE) {
                System.out.printf("\t%s\n\t%s^\n\t%s%c\n", haystack, " ".repeat(index), " ".repeat(index), needle.charAt(patternIndex));
            }
            if(Global.DEBUG) {
                System.out.printf("\tindex: %d text character: %c pattern index: %d pattern character: %c\n", index, haystack.charAt(index), patternIndex, needle.charAt(patternIndex));
                System.out.printf("\tgood suffix skip: %d bad character skip: %d\n", goodSuffixSkip, badCharacterSkip);
            }
            index += Math.max(badCharacterSkip, goodSuffixSkip) - 1;
        }
        if(suffix.equals(needle)) {
            return index - patternIndex;
        } else {
            return -1;
        }
    }

    public static HashMap<Character, Integer> makeBadCharacterTable(String needle) {
        if(Global.DEBUG)
            System.out.printf("makeBadCharacterTable\n");
        HashMap<Character, Integer> badCharacterTable = new HashMap<Character, Integer>();
        for(int index = needle.length() - 1; index >= 0; index--) {
            // Set index of right most character
            if(null == badCharacterTable.get(needle.charAt(index))) {
                badCharacterTable.put(needle.charAt(index), Math.abs(Integer.valueOf(index) - (needle.length()) + 1));
            } else {
                badCharacterTable.put(needle.charAt(index), Math.min(Math.abs(Integer.valueOf(index) - (needle.length() - 1)), badCharacterTable.get(needle.charAt(index))));
            }
        }
        return badCharacterTable;
    }

    public static HashMap<Integer, Integer> makeGoodSuffixTable(String needle) {
        if(Global.DEBUG)
            System.out.printf("makeGoodSuffixTable\n");
        HashMap<Integer, Integer> goodSuffixTable = new HashMap<Integer, Integer>();
        BiFunction<String, Integer, Integer> getSuffixLength = (_needle, index) -> {
            var suffixLength = 0;
            for (var i = index; i > 0 && needle.charAt(i) == needle.charAt(_needle.length() - 1 - index + i); i--) {
                suffixLength += 1;
            }
            return suffixLength;
        };
        var last = needle.length() - 1;
        for (int i = needle.length() - 1; i >= 0; i--) {
            if(needle.indexOf(needle.substring(i + 1)) == 0) {
                last = i + 1;
            }
            goodSuffixTable.put(i, last + (needle.length() - 1 - i));
        }

        for(int i = 0; i < needle.length() - 1; i++) {
            var suffixLength = getSuffixLength.apply(needle, i);
            if(needle.charAt(i - suffixLength) != needle.charAt(needle.length() - 1 - suffixLength)) {
                goodSuffixTable.put(needle.length() -1 - suffixLength, needle.length() - 1 - i + suffixLength);
            }
        }
        return goodSuffixTable;
    }

}

class Global {
    public static final boolean DEBUG = System.getenv("CS201_DEBUG") != null;
    public static final boolean TRACE = System.getenv("CS201_TRACE") != null;
}