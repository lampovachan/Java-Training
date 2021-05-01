package com.epam.rd.java.basic.practice3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {

    public static void main(String[] args) {
        String input = Util.getInput("part4.txt");
        System.out.println(input);
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest d = MessageDigest.getInstance(algorithm);
        d.reset();
        final String code = "Cp1251";
        try {
            d.update(input.getBytes(code));
        } catch (final UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported");
        }
        byte[] array = d.digest();
        StringBuilder str = new StringBuilder();
        StringBuilder strNew = new StringBuilder();
        for (final byte var : array) {
            final char count = 0xFF;
            strNew.append(Integer.toHexString(count & var));
            if (strNew.length() == 1) {
                str.append('0');
            }
            str.append(strNew);
            strNew.setLength(0);
        }
        str.toString();
        String result = str.toString();
        return result.toUpperCase(Locale.ENGLISH);
    }
}
