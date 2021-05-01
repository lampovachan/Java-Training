package com.epam.rd.java.basic.practice1;

public class Part7 {

    public static void main(String[] args) {
        printOut("A");
        printOut("B");
        printOut("Z");
        printOut("AA");
        printOut("AZ");
        printOut("BA");
        printOut("ZZ");
        printOut("AAA");
    }
    public static void printOut(String number) {
        String a = " ==> ";
        System.out.println(number + a + (str2int(number) + a + int2str(str2int(number))));
    }

    public static int str2int(String number) {
        int result = 0;
        for (int i = 0; i < number.length(); i++) {
            result *= 26;
            result += number.charAt(i) - 'A' + 1;
        }
        return result;
    }

    public static String int2str(int number) {
        StringBuilder chars = new StringBuilder();
        StringBuilder charsMirror = new StringBuilder();
        int modul;
        int divident = number;
        while (divident != 0) {
            modul = divident % 26;
            if (modul == 0) {
                chars.append("Z");
                divident = (divident - 1) / 26;
            } else {
                chars.append((char) (modul + 64));
                divident = (divident - modul) / 26;
            }
        }
        for (int i = 0; i < chars.length(); i++)
            charsMirror.append(chars.charAt(chars.length() - i - 1));
        return charsMirror.toString();
    }

    public static String rightColumn(String number) {
        String chars;
        int num;
        num = str2int(number);
        num++;
        chars = int2str(num);
        return chars;
    }
}
