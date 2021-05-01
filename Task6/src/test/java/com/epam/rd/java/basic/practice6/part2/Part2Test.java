package com.epam.rd.java.basic.practice6.part2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Part2Test {
    private Part2 part2;
    private static final int K = 1;
    @Before
    public void setUp(){
        part2 = new Part2(6);
    }

    @Test
    public void TestGetCircleInArray(){
        Integer[] array = {0, 1, 2, 3, 4, 5};
        List<Integer> arrayList = part2.getCircleInArray();
        List<Integer> testArrayList = Arrays.asList(array);
        Assert.assertEquals(testArrayList, arrayList);
    }

    @Test
    public void TestGetCircleInLinked(){
        Integer[] array = {0, 1, 2, 3, 4, 5};
        List<Integer> linkedList = part2.getCircleInLinked();
        List<Integer> testLinkedList = Arrays.asList(array);
        Assert.assertEquals(testLinkedList, linkedList);
    }

    @Test
    public void TestRemoveByIterator(){
        long now = System.currentTimeMillis();
        long testTime = Part2.removeByIterator(part2.getCircleInArray(), K);
        long delta = System.currentTimeMillis() - now;
        Assert.assertEquals(delta, testTime);

        now = System.currentTimeMillis();
        testTime = Part2.removeByIterator(part2.getCircleInLinked(), K);
        delta = System.currentTimeMillis() - now;
        Assert.assertEquals(delta, testTime);
    }

    @Test
    public void TestRemoveByIndex(){
        long now = System.currentTimeMillis();
        long testTime = Part2.removeByIndex(part2.getCircleInArray(), K);
        long delta = System.currentTimeMillis() - now;
        Assert.assertEquals(delta, testTime);

        now = System.currentTimeMillis();
        testTime = Part2.removeByIndex(part2.getCircleInLinked(), K);
        delta = System.currentTimeMillis() - now;
        Assert.assertEquals(delta, testTime);
    }

    @Test
    public void testRemoveByIndexIsFasterInArrayList(){
        int arrayListFaster = 0;
        for (int i = 0; i < 7; i++) {
            long time = System.nanoTime();
            Part2.removeByIndex(part2.getCircleInArray(), K);
            long timeArrayList = System.nanoTime() - time;

            time = System.nanoTime();
            Part2.removeByIndex(part2.getCircleInLinked(), K);
            long timeLinkedList = System.nanoTime() - time;
            if (timeArrayList < timeLinkedList)
                arrayListFaster ++;
        }
        Assert.assertTrue(arrayListFaster >= 4);
    }

    @Test
    public void testRemoveByIteratorIsFasterInLinkedList(){
        int linkedListFaster = 0;
        for (int i = 0; i < 7; i++) {
            long time = System.nanoTime();
            Part2.removeByIterator(part2.getCircleInArray(), K);
            long timeArrayList = System.nanoTime() - time;

            time = System.nanoTime();
            Part2.removeByIterator(part2.getCircleInLinked(), K);
            long timeLinkedList = System.nanoTime() - time;
            if (timeArrayList < timeLinkedList)
                linkedListFaster ++;
        }
        Assert.assertTrue(linkedListFaster >= 4);
    }
}
