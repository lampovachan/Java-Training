package com.epam.rd.java.basic.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part61 {

    private String file;
    private String[] input;

    public static void main(String[] args) {
        new Part61().console("--input", "part6.txt", "--task", "frequency");
    }

    private boolean console(String inputNew, String file, String task1, String action) {
        if (!("--input".equals(inputNew) || "-i".equals(inputNew))) {
            System.err.println("Wrong action");
            return false;
        }
        if (!("--task".equals(task1) || "-t".equals(task1))) {
            System.err.println("Wrong task1");
            return false;
        }

        this.file = file;
        initialize();

        if (action.equals("frequency")) {
            getResultFrequency();
        }
        return true;
    }

    private String getInput() {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner fileNew = new Scanner(new File(file), "CP1251")) {
            while (fileNew.hasNext()) {
                stringBuilder.append(fileNew.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", file));
        }
        return stringBuilder.toString();
    }

    public void initialize() {
        StringBuilder sb1 = new StringBuilder();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(getInput());
        while (matcher.find()) {
            sb1.append(matcher.group()).append(" ");
        }
        input = sb1.toString().split(" ");
    }

    private void getResultFrequency() {
        class Count {
            private int countNew = 1;
            private final int placeNew;

            public Count(int place) {
                this.placeNew = place;
            }

            public int getCountNew() {
                return countNew;
            }

            public Count setCountNew(int countNew) {
                this.countNew = countNew;
                return this;
            }

        }
        final HashMap<String, Count> map = new HashMap<>();
        for (int placeNew = 0; placeNew < input.length; placeNew++) {
            String word = input[placeNew];
            Count count = map.get(word);
            if (count == null) {
                map.put(word, new Count(placeNew));
            } else {
                count.setCountNew(count.getCountNew() + 1);
            }
        }
        TreeMap<String, Count> sorted = new TreeMap<>((a, b) -> {
            Count countWithPlace1 = map.get(a);
            Count countWithPlace2 = map.get(b);
            int c = countWithPlace2.countNew - countWithPlace1.countNew;
            if (c == 0) {
                return countWithPlace1.placeNew - countWithPlace2.placeNew;
            } else {
                return c;
            }
        });
        sorted.putAll(map);

        TreeSet<String> strings = new TreeSet<>(Comparator.reverseOrder());
        int i = 0;
        for (String string : sorted.keySet()) {
            if (i == 3) {
                break;
            }
            i++;
            strings.add(string);
        }
        for (String t : strings) {
            System.out.println(t + " ==> " + sorted.get(t).getCountNew());
        }
    }
}

