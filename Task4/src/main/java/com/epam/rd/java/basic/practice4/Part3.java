package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static boolean label;

    public void start() throws FileNotFoundException {
        String name = "part3.txt";
        String result = readFile(name, "Cp1251");
        input(result);
    }

    public static String readFile(final String path1, final String code) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(new File(path1)), code)) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
            }
            if (label) {
                throw new IllegalArgumentException();
            }

        }
        return stringBuilder.toString();
    }

    public void input(String res) {

        String regex;
        final String letter = "a-zA-Z\\p{InCyrillic}";
        final String startStr = "(^|\\s)([" + letter + "]";
        final String endStr = ")(?=\\s|" + System.lineSeparator() + "|$)";
        final String dec = "[0-9]";
        final String startDec = "(^|\\s)([+-]?(";
        final String endDec = "))(?=\\s|" + System.lineSeparator() + "|$)";
        Scanner sc = new Scanner(System.in, "Cp1251");
        while (sc.hasNext()) {
            String str = sc.nextLine();
            switch (str) {
                case "char":
                    regex = startStr + "{1}" + endStr;
                    System.out.println(getResTypeParse(regex, res));
                    break;

                case "String":
                    regex = startStr + "{2,}" + endStr;
                    System.out.println(getResTypeParse(regex, res));
                    break;

                case "int":
                    regex = startDec + dec + "+" + endDec;
                    System.out.println(getResTypeParse(regex, res));
                    break;

                case "double":
                    regex = startDec + "(" + dec + "+[.]" + dec + "*)|([.]" + dec + "+)" + endDec;
                    System.out.println(getResTypeParse(regex, res));
                    break;

                case "stop":
                    return;

                default:
                    System.out.println("Incorrect input");
            }
        }
    }

    public String getResTypeParse(final String regex, String input) {
        String wordNew;
        StringBuilder result = new StringBuilder();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        boolean isFound = false;
        while (matcher.find()) {
            wordNew = matcher.group(2);
            if (!isFound) {
                result.append(wordNew);
                isFound = true;
            } else {
                result.append(" ".concat(wordNew));
            }
        }
        result.append(" ");
        if (!isFound) {
            result.append("No such values");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        try {
            part3.start();
        } catch (FileNotFoundException e) {
           System.out.println(e.getMessage());
        }

    }
}
