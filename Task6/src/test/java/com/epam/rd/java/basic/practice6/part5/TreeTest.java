package com.epam.rd.java.basic.practice6.part5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TreeTest {
    private Tree<Integer> tree;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        tree = new Tree<>();
    }

    @Test
    public void testAdd() {
        Assert.assertTrue(tree.add(3));
        Assert.assertTrue(tree.add(4));
        Assert.assertFalse(tree.add(3));

    }

    @Test
    public void testRemove() {
        tree.add(3);
        Assert.assertTrue(tree.remove(3));
        Assert.assertFalse(tree.remove(3));
    }

    @Test
    public void testPrint() {
        Integer[] array = {1, 2, 5, 3, 6};
        tree.add(array);
        tree.print();
        Assert.assertEquals("1" + System.lineSeparator() +
                "  2" + System.lineSeparator() +
                "      3" + System.lineSeparator() +
                "    5" + System.lineSeparator() +
                "      6" + System.lineSeparator(), outContent.toString());
        outContent.reset();
    }
}

