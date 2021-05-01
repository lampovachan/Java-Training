package com.epam.rd.java.basic.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
    private static final int N = 17;
    private static final int K = 1;
    private final int listLength;

    private List<Integer> circleInArray;
    private List<Integer> circleInLinked;

    public Part2(int listLength) {
        this.listLength = listLength;
    }

    public static void main(String[] args) {

        Part2 part2 = new Part2(N);

        System.out.print("ArrayList#Index:" + " ");
        System.out.println(removeByIndex(part2.getCircleInArray(), K) + " ms");
        System.out.print("LinkedList#Index:" + " ");
        System.out.println(removeByIndex(part2.getCircleInLinked(), K) + " ms");

        System.out.print("ArrayList#Iterator:" + " ");
        System.out.println(removeByIterator(part2.getCircleInArray(), K) + " ms");
        System.out.print("LinkedList#Iterator:" + " ");
        System.out.println(removeByIterator(part2.getCircleInLinked(), K) + " ms");

    }

    public static long removeByIterator(List list, int k) {
        long time = System.currentTimeMillis();
        int count = 0;
        Iterator it = list.iterator();
        while (list.size() > 1) {
            if (it.hasNext()) {
                it.next();
                count++;
                if (count == k) {
                    it.remove();
                    count = 0;
                }
            } else {
                it = list.iterator();
            }
        }
        return System.currentTimeMillis() - time;
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long time = System.currentTimeMillis();
        int local = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.size() == 1) {
                break;
            }

            local += (k - 1);

            while (local >= list.size()) {
                local = local - list.size();
            }
            list.remove(local);
        }
        return System.currentTimeMillis() - time;
    }

    public List<Integer> getCircleInArray() {
        circleInArray = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            circleInArray.add(i);
        }
        return new ArrayList<>(circleInArray);
    }

    public void setCircleInArray(List<Integer> circleInArray) {
        this.circleInArray = new ArrayList<>(circleInArray);
    }

    public List<Integer> getCircleInLinked() {
        circleInLinked = new LinkedList<>();
        for (int i = 0; i < listLength; i++) {
            circleInLinked.add(i);
        }
        return new LinkedList<>(circleInLinked);
    }

    public void setCircleInLinked(List<Integer> circleInLinked) {
        this.circleInLinked = new LinkedList<>(circleInLinked);
    }

}