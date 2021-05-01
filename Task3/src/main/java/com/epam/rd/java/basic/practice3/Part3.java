package com.epam.rd.java.basic.practice3;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
       String input = Util.getInput("part3.txt");
       System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuffer buffer = new StringBuffer();
        String regex = "a-zA-Z\\p{InCyrillic}";

        Matcher m = Pattern.compile("(^|[^" + regex + "]*)([" + regex + "]+)([^" + regex + "]*|$)").matcher(input);
        while (m.find()) {
            String pre = m.group(1);
            String word = m.group(2);
            String post = m.group(3);
            StringBuilder builder = new StringBuilder(pre);
            if (word.length() > 2) {
                String begin = word.substring(0, 1);
                String lowerCase = begin.toLowerCase(Locale.getDefault());
                String upperCase = begin.toUpperCase(Locale.getDefault());
                begin = (!begin.equals(lowerCase)) ? lowerCase : upperCase;
                builder.append(begin).append(word.substring(1));
            } else {
                builder.append(word);
            }
            builder.append(post);
            m.appendReplacement(buffer, builder.toString());
        }
        m.appendTail(buffer);

        String result = buffer.toString();
        if (result.endsWith(System.lineSeparator())) {
            result = result.trim();
        }
        return result;
    }
}
