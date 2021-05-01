package com.epam.rd.java.basic.practice6.part1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

public class Part1 {
	private static final String ASDF = "asdf\n";
	public static void main(String[] args) {
		String string = "asd\n" +
				"43\n" +
				ASDF +
				"asd\n" +
				"43\n" +
				"434\n" +
				ASDF +
				"kasdf\n" +
				ASDF +
				"stop\n" +
				ASDF +
				"stop";
		wordCounter(new ByteArrayInputStream (string.getBytes(StandardCharsets.UTF_8)), System.out);
	}

	public static void wordCounter(InputStream in, PrintStream out) {
		Scanner scanner = new Scanner(in);
		WordContainer wordContainer = new WordContainer(new Word.CompareByWord());
		while (scanner.hasNext()) {
			String word = scanner.next();
			if ("stop".equals(word)) {
				break;
			}
			wordContainer.add(new Word(word));

		}

		Iterator iterator = wordContainer.frequencyIterator();
		while (iterator.hasNext()) {
			Word w = (Word) iterator.next();
			out.printf("%s : %s%n" +
					"", w.getValue(), w.getFrequency());
		}
	}

}
