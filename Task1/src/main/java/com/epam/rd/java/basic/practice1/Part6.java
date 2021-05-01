package com.epam.rd.java.basic.practice1;

public class Part6 {
    public static void main(String[] arg) {
        int number = Integer.parseInt(arg[0]);
        int ct;
        int n = 0;
        int i = 1;
        int j;
        while (n < number) {
            j = 1;
            ct = 0;
            while (j <= i) {
                if (i % j == 0)
                    ct++;
                j++;
            }
            if (ct == 2) {
                if (n < number-1)
                    System.out.print(i + " ");
                else
                    System.out.print(i);
                n++;
            }
            i++;
        }
    }
}





