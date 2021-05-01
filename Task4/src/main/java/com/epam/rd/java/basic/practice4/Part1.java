package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static boolean flag;

    public static String delete(String input) {
        String begin;
        String end;
        StringBuffer word;
        StringBuffer buffer = new StringBuffer();
        String letter = "a-zA-Z\\p{InCyrillic}";
        String regex = "(^|[^" + letter + "]*)([" + letter + "]+)([^" + letter + "]*|$)";

        Matcher m = Pattern.compile(regex).matcher(input);
        while (m.find()) {
            begin = m.group(1);
            word = new StringBuffer(m.group(2));
            end = m.group(3);
            String word1 = word.toString();
            if (word1.length() > 3) {
                word1 = word1.substring(2);
            }
            m.appendReplacement(buffer, begin + word1 + end);
        }
        m.appendTail(buffer);

        String string = buffer.toString();
        if (string.endsWith(System.lineSeparator())) {
            string = string.trim();
        }

        return string;
    }

    public static String readFile(String path, String encoding) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Scanner scan = new Scanner(new FileInputStream(new File(path)), encoding)) {
            while (scan.hasNextLine()) {
                builder.append(scan.nextLine()).append(System.lineSeparator());
            }
            if (flag) {
                throw new IllegalArgumentException();
            }
        }

        return builder.toString().trim();
    }

    public static String run(final String path, final String encoding) {
        try {
            final String res = readFile(path, encoding);
            return delete(res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
        public static void main ( final String[] args) {
            System.out.println(Part1.run("part1.txt", "Cp1251"));
        }
}
