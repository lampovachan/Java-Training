package com.epam.rd.java.basic.practice5;

import java.util.logging.Logger;

public class Part3 {
    private static final Logger logger = Logger.getLogger(Part3.class.getName());
    private static final String EXCEPTION = "InterruptedException: ";

    private int counter;
    private int counter2;

    private Thread[] threads;
    private int numberOfIteration;

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.threads = new Thread[numberOfThreads];
        this.numberOfIteration = numberOfIterations;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter2() {
        return counter2;
    }

    public void setCounter2(int counter2) {
        this.counter2 = counter2;
    }


    public void reset() {
        counter = 0;
        counter2 = 0;
    }


    public void output() {
        System.out.println(counter == counter2);
        setCounter(getCounter() + 1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException | NullPointerException e) {
            logger.info(EXCEPTION);
            Thread.currentThread().interrupt();
        }
        setCounter2(getCounter2() + 1);
    }

    public void compare() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadImp();
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.info(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        }
    }


    public void compareSync() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadImpSynch();
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.info(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        }

    }

    public void closeThreads() {
        for (Thread thread : Part3.this.threads) {
            thread.interrupt();
        }
    }


    public static void main(String[] args) {
        Part3 p3 = new Part3(2, 5);
        p3.compare();
        p3.reset();
        p3.compareSync();
    }

    class ThreadImp extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < numberOfIteration; i++) {
                output();
            }
        }
    }


    class ThreadImpSynch extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < numberOfIteration; i++) {
                synchronized (Part3.this) {
                    output();
                }
            }
        }
    }
}