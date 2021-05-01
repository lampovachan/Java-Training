package com.epam.rd.java.basic.practice6.part3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParkingTest {
    private Parking parking;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outContent));
        parking = new Parking(4);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testArrive(){
        Assert.assertTrue(parking.arrive(1));

        parking.print();
        Assert.assertEquals("0100"+System.lineSeparator(), outContent.toString());
        outContent.reset();

        Assert.assertTrue(parking.arrive(1));

        parking.print();
        Assert.assertEquals("0110"+System.lineSeparator(), outContent.toString());
        outContent.reset();

        Assert.assertTrue(parking.arrive(3));
        Assert.assertTrue(parking.arrive(3));

        parking.print();
        Assert.assertEquals("1111"+System.lineSeparator(), outContent.toString());
        outContent.reset();

        Assert.assertFalse(parking.arrive(0));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testArriveException(){
        parking.arrive(10);
    }
    @Test
    public void testDepart(){
        Assert.assertFalse(parking.depart(1));
        parking.arrive(1);
        Assert.assertTrue(parking.depart(1));
        parking.print();
        Assert.assertEquals("0000"+System.lineSeparator(), outContent.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepartException(){
        parking.depart(10);
    }

}