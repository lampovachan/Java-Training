package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    Part4(String filename) {
        setFileName(filename);
    }

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public final void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void output() {
        Parser parser = new Parser(new File(getFileName()));
        for (String str : parser) {
            System.out.println(str);
        }

    }

    public static void main(String[] args) {
        new Part4("part4.txt").output();
    }
}
class Parser implements Iterable<String> {
    public static final String ENCODING = "Cp1251";

    private Matcher matcher;

    private static final String REGEXP = "\\p{javaUpperCase}.*?\\.";

    public final String getEncoding() {
        return ENCODING;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public final void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public Parser(File file) {
        StringBuilder sb = new StringBuilder();
        try (Scanner s = new Scanner (file, getEncoding())) {
            while (s.hasNextLine()) {
                sb.append(s.nextLine()).append(" ");
                Pattern p = Pattern.compile(REGEXP);
                setMatcher(p.matcher(sb));
            }
        } catch (FileNotFoundException e) {
            System.out.println(file + " not found");
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new ParserIterator(getMatcher());
    }

    private static class ParserIterator implements Iterator<String> {

        private Matcher matcher;

        public Matcher getMatcher() {
            return this.matcher;
        }

        public final void setMatcher(Matcher matcher) {
            this.matcher = matcher;
        }

        public ParserIterator(Matcher matcher) {
            setMatcher(matcher);
        }

        @Override
        public boolean hasNext() {
            return getMatcher().find();
        }

        @Override
        public String next() {
            try {
                return getMatcher().group();
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException();
            }
        }
    }

}