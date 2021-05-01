package com.epam.rd.java.basic.practice6.part1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class WordContainer extends TreeSet<Word>{
	private static final long serialVersionUID = -2634104525087620456L;

	public WordContainer(Comparator<? super Word> comparator) {
		super(comparator);
	}

	public static void main(String[] args) {
		Part1.wordCounter(System.in, System.out);
	}

	@Override
	public boolean add(Word wordW) {
		if (!contains(wordW)) {
			super.add(wordW);
			return false;
		}
		for (Word next : this) {
			assert comparator() != null;
			if (comparator().compare(next, wordW) == 0) {
				next.setFrequency(next.getFrequency() + 1);
				return true;
			}
		}
		return false;
	}

	public Iterator<Word> frequencyIterator() {
		TreeSet<Word> words = new TreeSet<>(new Word.CompareByFrequency());
		words.addAll(this);

		return words.iterator();
	}
}
