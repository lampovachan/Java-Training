package com.epam.rd.java.basic.practice5;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class Part4 {
    private static final Logger logger = Logger.getLogger(Part4.class.getName());
    private static final String EXCEPTION = "InterruptedException: ";
    private static final String FILE_NAME = "part4.txt";
    private static final int SIZE = 4;
    private static final int ROWS = 100;
    private static int[][] arr = new int[SIZE][ROWS];
    private static long counter;

    public static int[][] read() {
        Scanner s = null;
        try {
            s = new Scanner(new FileReader(FILE_NAME));

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < ROWS; ++j) {
                    if (s.hasNextInt()) {
                        arr[i][j] = s.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.info("FileNotFoundException: ");
        }
        if (s != null) {
            s.close();

        }

        return arr.clone();

    }

    public static int maximumValue(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                logger.info(EXCEPTION);
                Thread.currentThread().interrupt();
            }
            if (arr[i] > max) {
                max = arr[i];
            }

        }
        return max;
    }

    public static void singleThread() {
        long time = System.currentTimeMillis();
        int max = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    logger.info(EXCEPTION);
                    Thread.currentThread().interrupt();
                }
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        System.out.println(max);
        System.out.println((System.currentTimeMillis() - time));

    }

    public static int[] multiplyThreads() {
        final int[] temp = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            final int count = i;

            Thread myThread = new Thread() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    temp[count] = maximumValue(arr[count]);
                    time = System.currentTimeMillis() - time;
                    if (counter < time) {
                        counter = time;
                    }
                }
            };
            myThread.start();
        }

        return temp;
    }

    public static void multiplyRun() {
        int[] temp = multiplyThreads();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            logger.info(EXCEPTION);
            Thread.currentThread().interrupt();
        }
        Arrays.sort(temp);
        System.out.println(temp[SIZE - 1]);
        System.out.println(counter);
    }

    public static void main(String[] args){
        read();
        multiplyRun();
        singleThread();

    }

}