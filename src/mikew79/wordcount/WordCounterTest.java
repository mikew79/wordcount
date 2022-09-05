package mikew79.wordcount;

import org.junit.jupiter.api.Assertions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {
    private final String TEST_STRING =  "Lorem ipsum dolor sit amet";
    private final String TEST_STRING2 =  "Lorem lorem dolor ";
    private final String TEST_STRING3 =  "Lorem dolor lorem";

    @org.junit.jupiter.api.BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @org.junit.jupiter.api.Test
    void test_parse() {
        Scanner sc = new Scanner(TEST_STRING);
        WordCounter wc = new WordCounter();
        int count = wc.parse(sc);
        Assertions.assertEquals(5, count);
    }

    @org.junit.jupiter.api.Test
    void test_getSortedCounts() {
        Scanner sc = new Scanner(TEST_STRING2);
        WordCounter wc = new WordCounter();
        int count = wc.parse(sc);


        LinkedHashMap<String, Integer> results = wc.getSortedCounts(false);
        int index = 0;
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (index == 0) {
                Assertions.assertEquals("LOREM", entry.getKey());
                Assertions.assertEquals(2, entry.getValue());
            } else if (index == 1) {
                Assertions.assertEquals("DOLOR", entry.getKey());
                Assertions.assertEquals(1, entry.getValue());
            }
            index++;
        }
        Assertions.assertEquals(2, index);
    }

    @org.junit.jupiter.api.Test
    void test_getSortedCountsOrder() {
        Scanner sc1 = new Scanner(TEST_STRING2);
        WordCounter wc1 = new WordCounter();
        wc1.parse(sc1);

        Scanner sc2 = new Scanner(TEST_STRING2);
        WordCounter wc2 = new WordCounter();
        wc2.parse(sc2);
        LinkedHashMap<String, Integer> results1 = wc1.getSortedCounts(false);
        LinkedHashMap<String, Integer> results2 = wc2.getSortedCounts(false);
        Assertions.assertEquals(results1.keySet(), results2.keySet());

        for (Map.Entry<String, Integer> entry : results1.entrySet()) {
            Assertions.assertEquals(results2.get(entry.getKey()),entry.getValue());
        }

    }
}