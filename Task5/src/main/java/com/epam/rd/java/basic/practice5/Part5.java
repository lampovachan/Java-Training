package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

public class Part5 {
    private static final Logger logger = Logger.getLogger(Part5.class.getName());
    private static final String IOEXCEPTION = "IOException: ";
    public static void main(String[] args) {
        final String path = "part5.txt";

        File file = new File(path);
        if (file.exists()) {
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                logger.info(IOEXCEPTION);
            }
        }
        boolean isCreated = false;
        try {
            isCreated = file.createNewFile();
        } catch (IOException e) {
            logger.info(IOEXCEPTION);
        }
        if (!isCreated)
            System.out.println("File wasn't created or deleted.");

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file,"rw");
        } catch (FileNotFoundException e) {
            logger.info("FileNotFoundException: ");
        }
        Thread[] threads = new Thread[10];

        for(int i=0; i<threads.length; ++i)
            threads[i] = new Writer(i, raf);

        for (Thread t : threads)
            t.start();


        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.info("InterruptedException: ");
                Thread.currentThread().interrupt();
            }
        }

        try {
            assert raf != null;
            raf.close();
        } catch (IOException e) {
           logger.info(IOEXCEPTION);
        }

        Scanner in = null;
        try {
            in = new Scanner(new File(path), "UTF-8");
        } catch (FileNotFoundException e) {
            logger.info("FileNotFoundException: ");
        }
        assert in != null;
        while (in.hasNextLine())
            System.out.println(in.nextLine());

        in.close();
    }
}
class Writer extends Thread {
    private static final Logger logger = Logger.getLogger(Writer.class.getName());
    private static final String IOEXCEPTION = "IOException: ";
    private int num;
    private final RandomAccessFile raf;
    private long pointer;
    private static final long STRING_LENGTH = 20L + System.lineSeparator().length();

    Writer(int num, RandomAccessFile raf) {
        this.num = num;
        this.raf = raf;
        pointer = num * STRING_LENGTH;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            synchronized (raf) {
                boolean wait = true;
                while (wait) {
                    wait = false;
                    try {
                        raf.wait(1);
                    } catch (InterruptedException e) {
                        logger.info(IOEXCEPTION);
                        Thread.currentThread().interrupt();
                    }
                }
                try {
                    raf.seek(pointer + i);
                } catch (IOException e) {
                    logger.info("IOExceprion: ");
                }
                try {
                    raf.writeBytes(String.valueOf(num));
                } catch (IOException e) {
                    logger.info("IOExceprion: ");
                }
            }
        }
        synchronized (raf) {
            try {
                raf.seek(pointer + 20);
                raf.write(System.lineSeparator().getBytes());
            } catch (IOException e) {
                logger.info(IOEXCEPTION);
            }
        }
    }
}