package com.epam.rd.java.basic.practice6.part1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

public class WordContainerTest {
    private Word word1;
    private Word word2;
    private Word word3;
    private WordContainer wordContainer;

    @Before
    public void setUp(){
        word1 = new Word("Hello");
        word2 = new Word("Hello");
        word3 = new Word("Another hello");
        wordContainer = new WordContainer(new Word.CompareByWord());
    }

    @Test
    public void TestAdd() {
        Assert.assertFalse(wordContainer.add(word1));
        Assert.assertTrue(wordContainer.add(word2));
        Assert.assertFalse(wordContainer.add(word3));
    }

    @Test
    public void TestFrequencyIterator(){
        wordContainer.add(word1);
        wordContainer.add(word2);
        wordContainer.add(word3);
        Iterator iterator = wordContainer.frequencyIterator();

        Word word = (Word) iterator.next();
        Assert.assertEquals("Hello", word.getValue());
        Assert.assertEquals(2, word.getFrequency());

        word = (Word) iterator.next();
        Assert.assertEquals("Another hello", word.getValue());
        Assert.assertEquals(1, word.getFrequency());
    }
}
