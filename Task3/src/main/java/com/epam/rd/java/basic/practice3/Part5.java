package com.epam.rd.java.basic.practice3;

public class Part5 {
    private static final String[] letters = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX",
            "V", "IV", "I" };

    private static final int[] numbers = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    public static void main(String[] args) {
        int number = 200;
        for (int i = 1; i <= number; i++) {
            System.out.println(i + " ==> " + decimal2Roman(i) + " ==> " + roman2Decimal(decimal2Roman(i)));
        }
    }

    public static String decimal2Roman(int dec) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            while (dec >= numbers[i]) {
                builder.append(letters[i]);
                dec -= numbers[i];
            }
        }
        return builder.toString();
    }

    public static int roman2Decimal(String roman) {
        StringBuilder s = new StringBuilder(roman);
        int constNum = 0;
        int number = 0;
        while (s.length() != 0) {
            if (s.indexOf(letters[number]) == 0) {
                constNum += numbers[number];
                s.delete(0, letters[number].length());
                continue;
            }
            number++;
        }
        return constNum;
    }
}
