package com.epam.rd.java.basic.practice6.part3;

public class Part3 {

    public static void main(String[] args) {
        Parking.print();
        Parking.arrive(1);
        Parking.arrive(2);
        System.out.println(Parking.depart(3));
        Parking.print();
    }

}
