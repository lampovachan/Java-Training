package com.epam.rd.java.basic.practice6.part4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

public class RangeTest {
    private Range range;
    private Range reverseRange;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outContent));
        range = new Range(2, 5);
        reverseRange = new Range(2, 5, true);
    }

    @Test
    public void testIterator(){
        Iterator iterator = range.iterator();
        int number = (int) iterator.next();
        Assert.assertEquals(2, number);
        number = (int) iterator.next();
        Assert.assertEquals(3, number);
    }
    @Test
    public void testIteratorReverse(){
        Iterator iterator = reverseRange.iterator();
        int number = (int) iterator.next();
        Assert.assertEquals(5, number);

        number = (int) iterator.next();
        Assert.assertEquals(4, number);
    }
    @Test
    public void testOutput(){
        range.output();
        Assert.assertEquals("2 3 4 5 ", outContent.toString());
        outContent.reset();

        reverseRange.output();
        Assert.assertEquals("5 4 3 2 ", outContent.toString());
        outContent.reset();
    }
}
