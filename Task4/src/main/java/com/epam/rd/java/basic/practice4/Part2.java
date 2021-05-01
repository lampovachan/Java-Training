package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Part2 {
    private String fileName;
    private String fileName2;
    public static final int MAX = 50;
    public static final int N = 10;
    public static final String ENCODING = "Cp1251";
    public static final SecureRandom random = new SecureRandom();

    private Part2(String fileName, String fileName2, String encoding) {
        setFileName(fileName);
        setFileName2(fileName2);
        setEncoding(encoding);
    }

    public String setEncoding(String encoding){
        return encoding;
    }

    public int calculateRandomInt() {
        return getRandom().nextInt(getMax());
    }

    public String fillWithRandomNumbers() {
        int count = 0;
        final StringBuilder sb = new StringBuilder();
        while (count != getN()) {
            sb.append(calculateRandomInt()).append(" ");
            count++;
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public String getEncoding() {
       return ENCODING;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileName2() {
        return fileName2;
    }

    public int getMax() {
        return MAX;
    }

    public int getN() {
        return N;
    }

    public Random getRandom() {
        return random;
    }

    public String result() {
        String str = "";
        try {
            final StringBuilder sb = new StringBuilder();
            final Scanner sc = new Scanner(new File(getFileName()), getEncoding());
            while (sc.hasNextLine()) {
                sb.append("input ==> ").append(sc.nextLine()).append(System.lineSeparator());
            }
            sc.close();
            final Scanner sc2 = new Scanner(new File(getFileName2()), getEncoding());
            while (sc2.hasNextLine()) {
                sb.append("output ==> ").append(sc2.nextLine());
            }
            sc2.close();
            str = sb.toString().substring(0, sb.length() - 1);

        } catch (final FileNotFoundException e) {
            System.out.println(getFileName() + "  or " + getFileName2() + " not found");
        }
        return str;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String sortNumbers(String str) {
        final String[] arr = str.split(" ");
        final int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(arr2);
        final StringBuilder sb = new StringBuilder();
        for (final int element : arr2) {
            sb.append(element).append(" ");
        }
        return sb.toString();
    }

    public void writeFirstFile() {
        try (final PrintWriter pr = new PrintWriter(new File(getFileName()), getEncoding())) {
            pr.write(fillWithRandomNumbers());
        } catch (final FileNotFoundException e) {
            System.out.println(getFileName() + " cannot be written");
        } catch (final UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding");
        }
    }

    public void writeSecondFile() {

        try (final Scanner sc = new Scanner(new File(getFileName()), getEncoding());
             final PrintWriter pr2 = new PrintWriter(new File(getFileName2()), getEncoding())) {
            while (sc.hasNextLine()) {
                pr2.write(sortNumbers(sc.nextLine()));
            }
        } catch (final FileNotFoundException e) {
            System.out.println(getFileName() + " or " + getFileName2() + " cannot be written");
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            System.out.println("Unsupported encoding");
        }
    }

    public static void main(String[] args) {
        final Part2 part2 = new Part2("part2.txt", "part2_sorted.txt", "CP1251");
        part2.writeFirstFile();
        part2.writeSecondFile();
        System.out.println(part2.result());
    }
}