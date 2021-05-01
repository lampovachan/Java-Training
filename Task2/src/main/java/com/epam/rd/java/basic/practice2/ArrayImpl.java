package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] array;
    private int elementsInArray;

    public ArrayImpl(int n) {
        if (n <= 0) {
            System.out.println("The size must be greater than 0. Try again.");
            return;
        }
        this.array = new Object[n];
        this.elementsInArray = 0;
    }

    private boolean checkIfArrayFull() {
        return this.array.length == this.elementsInArray;
    }

    private int increaseArraySize(int size, String action) {
        if (action.equals("double")) {
            size = this.array.length * 2;
        } else {
            size = this.array.length + size;
        }
        return size;
    }

    private void copyArray(int size, String action) {
        size = increaseArraySize(size, action);
        Object[] tempArray = new Object[size];
        int tempElement = 0;
        for (int i = 0; i < this.array.length; i++, tempElement++) {
            if (this.array[i] == null) {
                tempElement--;
                continue;
            }
            tempArray[tempElement] = this.array[i];
        }
        this.array = null;
        this.array = new Object[tempArray.length];
        this.array = tempArray;
    }


    @Override
    public void clear() {
        array = new Object[0];
        this.elementsInArray = 0;
    }

    @Override
    public int size() {
        return this.elementsInArray;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        protected int index = 0;

        @Override
        public boolean hasNext() {
            return (index < elementsInArray);
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[index++];
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    @Override
    public void add(Object element) {
        if (checkIfArrayFull()) {
            copyArray(0, "double");
        }

        this.array[this.elementsInArray] = element;
        this.elementsInArray++;
    }

    @Override
    public void set(int index, Object element) {
        try {
            array[index] = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The index that you specified is not within bounds.");
        }
    }

    @Override
    public Object get(int index) {
        Object element = null;
        try {
            element = array[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The index that you specified is not within bounds.");
        }
        return element;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < this.array.length; i++) {
            if (element.equals(this.array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        Object[] copy = new Object[array.length - 1];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                copy[j++] = array[i];
            }
        }
        array = copy;
        this.elementsInArray--;
    }

    @Override
    public String toString() {
        StringBuilder symbol = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1)
                symbol.append(array[i]);
            else
                symbol.append(array[i]).append(", ");
        }
        symbol.append("]");
        return symbol.toString();
    }

    public static void main(String[] args) {
        ArrayImpl myArray = new ArrayImpl(3);
        myArray.add('A');
        myArray.add('B');
        myArray.add('C');
        myArray.size();
        Iterator itr = myArray.iterator();
        while(itr.hasNext())
            System.out.print(itr.next() + " ");
        System.out.println();
        for(Object x : myArray)
            System.out.print(x + " ");
        myArray.set(2, 'F');
        }
    }