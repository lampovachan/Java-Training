package com.epam.rd.java.basic.practice7.laptops;

public class ColorPrices implements Comparable<ColorPrices> {
    private double inBlackColor;
    private double inGreyColor;
    private double inWhiteColor;

    public ColorPrices(double black, double grey, double white) {
        inBlackColor = black;
        inGreyColor = grey;
        inWhiteColor = white;
    }

    public ColorPrices() {

    }

    public double getInBlackColor() {
        return inBlackColor;
    }

    public void setInBlackColor(double inBlackColor) {
        this.inBlackColor = inBlackColor;
    }

    public double getInGreyColor() {
        return inGreyColor;
    }

    public void setInGreyColor(double inGreyColor) {
        this.inGreyColor = inGreyColor;
    }

    public double getInWhiteColor() {
        return inWhiteColor;
    }

    public void setInWhiteColor(double inWhiteColor) {
        this.inWhiteColor = inWhiteColor;
    }

    @Override
    public String toString() {
        return "Color prices:\n\t" + "In black color: " + inBlackColor + "\n\tIn grey color: "
                + inGreyColor + "\n\tIn white color: " + inWhiteColor;
    }

    @Override
    public int compareTo(ColorPrices a) {
        int comparationResult = Double.compare(inBlackColor, a.inBlackColor);
        if (comparationResult != 0) {
            return comparationResult;
        }
        comparationResult = Double.compare(inGreyColor, a.inGreyColor);
        if (comparationResult != 0) {
            return comparationResult;
        }
        comparationResult = Double.compare(inWhiteColor, a.inWhiteColor);
        if (comparationResult != 0) {
            return comparationResult;
        }
        return 0;
    }
}
