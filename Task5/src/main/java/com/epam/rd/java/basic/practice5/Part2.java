package com.epam.rd.java.basic.practice5;

import java.util.logging.Logger;

public class Part2 extends Thread {
    private static final Logger logger = Logger.getLogger(Part2.class.getName());
    private String message;

    public Part2(String message) {
        this.message = message;
    }
    public static void main(String[] args){
        //this method is unnecessary
    }
    @Override
    public void run() {
        while (true){
            try {
                sleep(0);
            } catch (InterruptedException e) {
                logger.info("InterruptedException: ");
                Thread.currentThread().interrupt();
            }
            System.out.println(message);

            if (isInterrupted())
                return;
        }
    }
}
