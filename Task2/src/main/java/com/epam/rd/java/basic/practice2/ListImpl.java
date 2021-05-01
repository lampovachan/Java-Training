package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node head;

    int numberOfNodes = 0;

    public ListImpl() {head = null;}

    static class Node {
        private Object data;
        private Node next;

        public Node(Object data, Node next)
        {
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public Node getNextNode() {
            return next;
        }

        public void setNextNode(Node next) {
            this.next = next;
        }
    }

    @Override
    public void clear() {
        head = null;
        numberOfNodes = 0;
    }

    @Override
    public int size() {
        int counter = 0;
        if (head != null) {
            Node tmp = head;
            counter = 1;
            while (tmp.next != null) {
                tmp = tmp.next;
                counter++;
            }
        }
        return counter;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node nextNode;
        public IteratorImpl()
        {
            nextNode = head;
        }
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            Object res = nextNode.data;
            nextNode = nextNode.next;
            return res;
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    @Override
    public void addFirst(Object element) {
        head = new Node(element, head);
        numberOfNodes++;
    }

    @Override
    public void addLast(Object element) {
        if (head == null)
            addFirst(element);
        else {
            Node tmp = head;
            while (tmp.next != null) tmp = tmp.next;

            tmp.next = new Node(element, null);
        }
        numberOfNodes++;
    }

    @Override
    public void removeFirst() {
        try {
            head = head.next;
            numberOfNodes--;
        } catch (NullPointerException e){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void removeLast() {
        try {
            Node second = head;
            while (second.next.next != null)
                second = second.next;
            second.next = null;
            numberOfNodes--;
        } catch (NullPointerException e){
            throw new NoSuchElementException();
        }
    }

    @Override
    public Object getFirst() {
        if(head == null) return null;
        return head.data;
    }

    @Override
    public Object getLast() {
        if(head == null) return null;
        Node tmp = head;
        while(tmp.next != null) tmp = tmp.next;
        return tmp.data;
    }

    @Override
    public Object search(Object element) {
        for(Object tmp : this)
            if(tmp != null && tmp.equals(element))
                return element;
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node previous = head;
        Node current = head.getNextNode();
        if ((head.getData() == null && element == null) || (head.getData() != null && head.getData().equals(element))) {
            removeFirst();
        }
        while (current != null) {
            Object dataOld = current.getData();
            if ((dataOld == null && element == null) || (dataOld != null && dataOld.equals(element))) {
                Node afterRemoved = current.getNextNode();
                previous.setNextNode(afterRemoved);
                return true;
            } else {
                previous = current;
                current = current.getNextNode();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        if (head != null) {
            res.append(head.getData());
            Node cur = head.getNextNode();
            while (cur != null) {
                res.append(", ");
                res.append(cur.getData());
                cur = cur.getNextNode();
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addFirst("B");
        list.addFirst("A");
        list.addLast("C");
        list.addLast(null);
        System.out.println(list.toString() + " " + list.size());
        list.remove("A");
        System.out.println(list.toString() + " " + list.size());
        list.removeFirst();
        System.out.println(list.toString() + " " + list.size());
        list.removeLast();
        System.out.println(list.toString() + " " + list.size());
        list.clear();
        System.out.println(list.toString() + " " + list.size());
        list.getFirst();
        list.getLast();
        System.out.println(list.toString() + " " + list.size());
    }
}
