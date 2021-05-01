package com.epam.rd.java.basic.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part63 {

    private String fileName;
    private String[] inputing;

    public static void main(String[] args) {

        new Part63().consoleInput("--input", "part6.txt", "--task", "duplicates");
    }

    private boolean consoleInput(String enter, String fileNew, String practice, String operat) {

        if (!("--enter".equals(enter) || "-i".equals(enter))) {
            System.err.println("Wrong operat");
            return false;
        }
        if (!("--practice".equals(practice) || "-t".equals(practice))) {
            System.err.println("Wrong practice");
            return false;
        }

        this.fileName = fileNew;
        initialize();

        if ("duplicates".equals(operat)) {
            getResult();
        } else {
            return false;
        }
        return true;
    }

    private String getInputMessage() {
        StringBuilder sbNew = new StringBuilder();
        try (Scanner sc = new Scanner(new File(fileName), "CP1251")) {
            while (sc.hasNext()) {
                sbNew.append(sc.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", fileName));
        }
        return sbNew.toString();
    }

    public void initialize() {
        StringBuilder string = new StringBuilder();
        Pattern p1 = Pattern.compile("[a-zA-Z]+");
        Matcher m1 = p1.matcher(getInputMessage());
        while (m1.find()) {
            string.append(m1.group()).append(" ");
        }
        inputing = string.toString().split(" ");
    }

    private void getResult() {
        final Map<String, Integer> counts = new LinkedHashMap<>();
        for (String word : inputing) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                count +=1;
                counts.put(word, count);
            }
        }
        int i1 = 0;
        for (Map.Entry<String, Integer> count : counts.entrySet()) {
            if (i1 == 3) {
                break;
            }
            if (count.getValue() > 1) {
                i1++;
                System.out.println(new StringBuilder(count.getKey()).reverse().toString().toUpperCase());
            }
        }
    }
}
