package com.epam.rd.java.basic.practice1;

public class Part4 {

    public static void main(String[] args) {
        int divisor = 1;
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        for(int i = 1; i <= a && i <= b; i++)
        {
            if(a%i==0 && b%i==0)
                divisor = i;
        }
        System.out.print(divisor);
    }

}
