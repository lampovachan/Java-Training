package com.epam.rd.java.basic.practice6.part5;

public class Part5 {
    public static final String SIGN = "~~~~~~~";

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        System.out.println(tree.add(3));
        System.out.println(tree.add(3));

        System.out.println(SIGN);
        tree.add(new Integer[] { 1, 2, 5, 4, 6, 0 });
        tree.print();

        System.out.println(SIGN);
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(5));

        System.out.println(SIGN);
        tree.print();
    }

}
