package com.epam.rd.java.basic.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part62 {

    private String fileName;
    private String[] input;

    public static void main(String[] args) {
        new Part62().consoleInput("--input", "part6.txt", "--task", "length");
    }

    private boolean consoleInput(String input1, String fileName1, String task1, String operation1) {
        if (!("--input".equals(input1) || "-i".equals(input1))) {
            System.err.println("Bad operation");
            return false;
        }
        if (!("--task".equals(task1) || "-t".equals(task1))) {
            System.err.println("Bad task");
            return false;
        }

        this.fileName = fileName1;
        initialize();

        if ("length".equals(operation1)) {
            getResultLength();
        } else {
            return false;
        }
        return true;
    }

    private String getInput() {
        StringBuilder builder = new StringBuilder();
        try (Scanner f = new Scanner(new File(fileName), "CP1251")) {
            while (f.hasNext()) {
                builder.append(f.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", fileName));
        }
        return builder.toString();
    }

    public void initialize() {
        StringBuilder sb1 = new StringBuilder();
        Pattern p1 = Pattern.compile("[a-zA-Z]+");
        Matcher m1 = p1.matcher(getInput());
        while (m1.find()) {
            sb1.append(m1.group()).append(" ");
        }
        input = sb1.toString().split(" ");
    }

    private void getResultLength() {
        class Counts {
            private final int lngth;
            private final int plc;

            public Counts(int length, int place) {
                this.lngth = length;
                this.plc = place;
            }

            public int getLngth() {
                return lngth;
            }

        }
        final HashMap<String, Counts> countsHashMap = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            int finalPlace = place;
            countsHashMap.computeIfAbsent(w, key -> new Counts(w.length(), finalPlace));
        }
        TreeMap<String, Counts> sortedW = new TreeMap<>((a, b) -> {
            Counts countWithPlaceA = countsHashMap.get(a);
            Counts countWithPlaceB = countsHashMap.get(b);
            int l = countWithPlaceB.lngth - countWithPlaceA.lngth;
            if (l == 0) {
                return countWithPlaceA.plc - countWithPlaceB.plc;
            } else {
                return l;
            }
        });
        sortedW.putAll(countsHashMap);

        int f = 0;
        for (Map.Entry<String, Counts> entry : sortedW.entrySet()) {
            if (f == 3) {
                break;
            }
            f++;
            System.out.println(entry + " ==> " + sortedW.get(entry).getLngth());
        }
    }

}