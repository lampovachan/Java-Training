package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        String minimum = getMinimum(input);
        String maximum = getMaximum(input);
        return minimum + System.lineSeparator() + maximum;
    }

    public static String getMinimum(String input) {
        StringBuilder string = new StringBuilder();
        int count = Integer.MAX_VALUE;
        string.append("Min: ");
        Pattern p = Pattern.compile("[a-zA-Z\\p{InCyrillic}]+");
        Matcher m = p.matcher(input);
        while (m.find()) {
            Pattern p2 = Pattern.compile(m.group());
            Matcher m2 = p2.matcher(string.toString().substring(5));
            if (count > m.group().length()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Min: ");
                count = m.group().length();
                sb2.append(m.group());
                string = sb2;
            } else if (count == m.group().length() && !(m2.find())) {
                string.append(", ").append(m.group());
            }
        }
        return string.toString();
    }


    public static String getMaximum(String input) {
        StringBuilder str = new StringBuilder();
        int count = 0;
        str.append("Max: ");
        Pattern p = Pattern.compile("[a-zA-Z\\p{InCyrillic}]+");
        Matcher m = p.matcher(input);
        while (m.find()) {
            Pattern p2 = Pattern.compile(m.group());
            Matcher m2 = p2.matcher(str.toString().substring(5));
            if (count < m.group().length()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Max: ");
                count = m.group().length();
                sb2.append(m.group());
                str = sb2;
            } else if (count == m.group().length() && !(m2.find())) {
                str.append(", ").append(m.group());

            }
        }
        return str.toString();
    }
}
