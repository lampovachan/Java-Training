package com.epam.rd.java.basic.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private final int first;
    private final int last;
    private final boolean revers;

    public Range(int a, int b) {
        this(a, b, false);
    }

    public Range(int a, int b, boolean reverse) {
        revers = !reverse;
        first = a;
        last = b;
    }

    public void output() {
        for (Integer integer : this) {
            System.out.printf("%s ", integer);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        if (revers) {
            return new RangeIterator();
        }
        return new RangeIteratorDes();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int pointer;

        RangeIterator() {
            pointer = first - 1;
        }

        @Override
        public boolean hasNext() {
            return pointer < last;
        }

        @Override
        public Integer next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return ++pointer;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class RangeIteratorDes implements Iterator<Integer> {
        private int pointer;

        RangeIteratorDes() {
            pointer = last + 1;
        }

        @Override
        public boolean hasNext() {
            return pointer > first;
        }

        @Override
        public Integer next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return --pointer;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
