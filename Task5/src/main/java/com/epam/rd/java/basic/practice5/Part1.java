package com.epam.rd.java.basic.practice5;
import java.util.logging.Logger;

class ThreadOne extends Thread {
    Logger logger = Logger.getLogger(ThreadOne.class.getName());
    private static final String EXPRESSION = "InterruptedException: ";

    ThreadOne() {
    }
@Override
    public void run() {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(this.getName());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            logger.info(EXPRESSION);
            Thread.currentThread().interrupt();
        }
    }
}


class ThreadTwo implements Runnable {
    private static final String EXPRESSION = "InterruptedException: ";
    Logger logger = Logger.getLogger(ThreadTwo.class.getName());
    Thread t;

    ThreadTwo() {
        t = new Thread(this);
    }

    public void run() {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(t.getName());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            logger.info(EXPRESSION);
            Thread.currentThread().interrupt();
        }
    }
}

public class Part1 {
    private static final String EXCEPTION = "InterruptedException: ";
    private static final Logger logger = Logger.getLogger(Part1.class.getName());
    public static void main(String[] args) {
        Thread threadOne = new ThreadOne();
        Thread threadTwo = new Thread(new ThreadTwo());
        threadOne.start();
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            logger.info(EXCEPTION);
            Thread.currentThread().interrupt();
        }
        threadTwo.start();
        try {
            threadTwo.join();
        } catch (InterruptedException e) {
            logger.info(EXCEPTION);
            Thread.currentThread().interrupt();
        }
    }
}

