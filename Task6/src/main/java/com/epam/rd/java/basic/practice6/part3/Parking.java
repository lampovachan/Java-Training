package com.epam.rd.java.basic.practice6.part3;

import java.util.Arrays;
import java.util.List;

public class Parking {
    private static Integer[] park;

    private static int countCar;

    public Parking(int n) {
        fillInNull(n);
    }

    private static void fillInNull(int n) {
        park = new Integer[n];
        for (int i = 0; i < n; i++) {
            park[i] = 0;
        }
    }

    public static boolean depart(int k) {
        if (k < 0 || k > (park.length - 1)) {
            throw new IllegalArgumentException("k < 0 || k > (n - 1)");
        }

        if (park[k] == 0) {
            return false;
        }
        park[k] = 0;
        countCar--;
        return true;
    }

    public static boolean arrive(int k) {
        List<Integer> list;
        list = Arrays.asList(park);
        if (!list.contains(0))
            return false;
        if (countCar == park.length) {
            return false;
        }
        if (k < 0 || k > park.length - 1)
            throw new IllegalArgumentException("k < 0 || k > (n - 1)");
        while (k <= park.length) {
            if (k + 1 > park.length) {
                k = 0;
            }
            if (park[k] != 0) {
                k++;
            } else {
                park[k] = 1;
                return true;
            }
        }
        return false;
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (Integer car : park) {
            sb.append(car);
        }
        System.out.print(sb.toString() + System.lineSeparator());
    }
    public static void main(String[] args){
      //this method is unnecessary
    }
}
