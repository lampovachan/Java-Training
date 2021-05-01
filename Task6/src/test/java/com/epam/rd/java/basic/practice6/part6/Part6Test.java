package com.epam.rd.java.basic.practice6.part6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Part6Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Part6 part6;

    @Before
    public void setUp() {
        part6 = new Part6();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCheckConsoleInput() {
        Assert.assertFalse(part6.consoleInput("--output", "part6.txt", "--task", "length"));
        Assert.assertFalse(part6.consoleInput("--input", "part6.txt", "--cast", "length"));
        Assert.assertTrue(part6.consoleInput("-i", "part6.txt", "-t", "length"));
        Assert.assertFalse(part6.consoleInput("-i", "part6.txt", "-t", "array"));
    }

    @Test
    public void testLength() {
        part6.consoleInput("--input", "part6.txt", "--task", "length");
        Assert.assertEquals("chimpanzee ==> 10" + System.lineSeparator() +
                "mongoose ==> 8" + System.lineSeparator() +
                "tortoise ==> 8" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testFrequency() {
        part6.consoleInput("--input", "part6.txt", "--task", "frequency");
        Assert.assertEquals("whale ==> 3" + System.lineSeparator() +
                "cheetah ==> 4" + System.lineSeparator() +
                "bison ==> 3" + System.lineSeparator(), outContent.toString());
    }
    @Test
    public void testDuplicates() {
        part6.consoleInput("--input", "part6.txt", "--task", "duplicates");
        Assert.assertEquals("RAUGAJ" + System.lineSeparator() +
                "NOSIB" + System.lineSeparator() +
                "ELAHW" + System.lineSeparator(), outContent.toString());
    }
}

