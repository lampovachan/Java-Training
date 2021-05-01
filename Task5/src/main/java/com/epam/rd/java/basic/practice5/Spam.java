package com.epam.rd.java.basic.practice5;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Logger;

public class Spam {
    private static final Logger logger = Logger.getLogger(Spam.class.getName());
    private Thread[] threads;
    public Spam(String[] messages, int[] times) {
        if(messages.length == times.length) {
            threads = new Thread[messages.length];
            for(int i = 0; i < messages.length; i++) {
                threads[i] = new Worker(messages[i], times[i]);
            }
        }
    }
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }
    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
    private static class Worker extends Thread {
        private String mes;
        private int time;
        public Worker(String mes, int time) {
            this.mes = mes;
            this.time = time;
        }
        @Override
        public void run() {
            while(true) {
                System.out.println(mes);
                try {
                    Worker.sleep(time);
                } catch (InterruptedException e) {
                    logger.info("InterruptedException: ");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };
        Spam spam = new Spam(messages, times);
        spam.start();
        try {
            if( System.in.read() == KeyEvent.VK_ENTER) {
                spam.stop();
            }
        } catch (IOException e) {
            System.err.println(Thread.currentThread().getName() + " is interrupted");
        }

    }
}
