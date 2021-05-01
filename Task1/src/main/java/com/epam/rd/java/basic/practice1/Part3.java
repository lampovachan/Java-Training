package com.epam.rd.java.basic.practice1;

public class Part3 {

    public static void main(String[] args) {
        String[] edges = String.join( " ", args ).split("[, ]");
        for(int i = 0; i <= edges.length-1; i++) {
            if (i==edges.length-1)
                System.out.print(edges[i]);
            else
                System.out.print(edges[i]+ " ");
        }
    }
}

