package com.epam.rd.java.basic.practice1;

public class Part5 {

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        int sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        System.out.print(sum);
    }
	
}
