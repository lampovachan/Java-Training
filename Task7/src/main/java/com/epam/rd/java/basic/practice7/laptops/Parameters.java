package com.epam.rd.java.basic.practice7.laptops;

import java.util.Arrays;
import java.util.List;

public class Parameters implements Comparable<Parameters>{
    private int numberOfSeries;
    private int processorGen;
    private double display;
    private static final List<Integer> VALID_PROCESSOR_GEN = Arrays.asList(3, 5, 7, 9);

    public Parameters(int numberOfSeries, int processorGen, int display) {
        this.processorGen = validateProcessorGen(numberOfSeries);
        this.numberOfSeries = numberOfSeries;
        this.display = display;
    }

    public Parameters() {

    }

    private static int validateProcessorGen(int enterProcessorGen) {
        if(VALID_PROCESSOR_GEN.contains(enterProcessorGen)) {
            return enterProcessorGen;
        }
        return 0;
    }

    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public int getProcessorGen() {
        return processorGen;
    }

    public void setProcessorGen(int processorGen) {
        this.processorGen = processorGen;
    }

    public double getDisplay() {
        return display;
    }

    public void setDisplay(double display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "Parameters:\n\t" + "Number of Series: " + numberOfSeries + "\n\tProcessor Generation: "
                + processorGen + "\n\tSize of Display: " + display;
    }

    @Override
    public int compareTo(Parameters a) {
        int result = Integer.compare(numberOfSeries, a.numberOfSeries);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(processorGen, a.processorGen);
        if (result != 0) {
            return result;
        }
        result = Double.compare(display, a.display);
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
