package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private int length;
    private Node front;
    private Node rear;

    static class Node {
        private Object data;
        private Node nextNode;

        public Node(Object data){
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }

    public QueueImpl() {
        length = 0;
        front = rear = null;
    }

    public  boolean isEmpty(){
        return length == 0;
    }

    @Override
    public void clear() {
        front = null;
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node nextNode = front;
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            Object res = nextNode.data;
            nextNode = nextNode.nextNode;
            return res;
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    @Override
    public void enqueue(Object element) {
        Node node = new Node(element);
        if(isEmpty()){
            front = node;
        }else {
            rear.setNextNode(node);
        }
        rear = node;
        length++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()){
            return null;
        }
        Object result = front.getData();
        front = front.getNextNode();
        length--;
        if(isEmpty()){
            rear = null;
        }
        return result;
    }

    @Override
    public Object top() {
        if(isEmpty()){
            return null;
        }
        return front.getData();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        if (front != null) {
            res.append(front.getData());
            Node cur = front.getNextNode();
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
        QueueImpl myQueue = new QueueImpl();
        myQueue.dequeue();
        System.out.println(myQueue.top());

        System.out.print(myQueue.toString());
        myQueue.enqueue('a');
        myQueue.enqueue('c');
        myQueue.dequeue();
        System.out.println(myQueue.toString());
        System.out.println(myQueue.top());
        myQueue.enqueue('d');
        myQueue.enqueue('e');
        Iterator itr = myQueue.iterator();
        while(itr.hasNext())
            System.out.print(itr.next() + " ");
        System.out.println();
        for (Object element : myQueue){
            System.out.println(element + " ");
        }
    }
}
