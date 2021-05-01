package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private StackNode top;
    private int size;

    static class StackNode {
        private Object value;
        private StackNode next;

        public StackNode(Object val) {
            this.value = val;
            this.next = null;
        }
        public Object getValue(){return value;}

        public StackNode getNext() {
            return next;
        }

        public void setNext(StackNode nextNode) {
            this.next = nextNode;
        }

        public String toString() {
            if(value == null) {
                return "null";
            }
            return value.toString();
        }
    }

    public StackImpl() {
        size = 0;
        top = null;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private StackNode current = top;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            Object item = current.value;
            current = current.next;
            return item;
        }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }
    }

    @Override
    public void push(Object element) {
        StackNode cur = new StackNode(element);
        cur.setNext(top);
        top = cur;
        size++;
    }

    @Override
    public Object pop() {
        if(size == 0 || top.getNext() == null) {
            return null;
        }
        Object poppedElement = top.getValue();
        this.top = this.top.getNext();
        size--;
        return poppedElement;
    }

    @Override
    public Object top() {
        try {
            return top.getValue();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        Object[] arr = new Object[size];
        if (top != null) {
            arr[0] = top.getValue();
            StackNode cur = top.getNext();
            int i = 1;
            while (cur != null) {
                arr[i++] = cur.getValue();
                cur = cur.getNext();
            }
            for (int j = arr.length-1; j >= 0; j--) {
                if (j==0){
                    res.append(arr[j]);
                }
                else {
                    res.append(arr[j]);
                    res.append(", ");
                }
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        while (!stack.isEmpty()) {
            String item = stack.toString();
            if (!item.equals("-")) stack.push(item);
        }
        System.out.println(stack);
        stack.push('a');
        stack.push('b');
        stack.push('c');
        stack.push(null);
        System.out.println(stack);
        System.out.println(stack.top());
        System.out.println(stack);
        System.out.println(stack.pop());
        stack.push(null);
        System.out.println(stack);
        System.out.println(stack.top());
        stack.clear();
        System.out.println(stack);
        stack.push(null);
        stack.clear();
    }

}
