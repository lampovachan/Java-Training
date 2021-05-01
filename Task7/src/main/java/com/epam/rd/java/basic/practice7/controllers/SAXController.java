package com.epam.rd.java.basic.practice7.controllers;

import com.epam.rd.java.basic.practice7.constants.LaptopConstants;
import com.epam.rd.java.basic.practice7.constants.XMLConstants;
import com.epam.rd.java.basic.practice7.laptops.ColorPrices;
import com.epam.rd.java.basic.practice7.laptops.Laptops;
import com.epam.rd.java.basic.practice7.laptops.Parameters;
import com.epam.rd.java.basic.practice7.util.Util;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SAXController extends DefaultHandler {
    private static class ParserBooleanContainer {
        private static boolean brand;
        private static boolean series;
        private static boolean price;
        private static boolean colorPrices;
        private static boolean inBlackColor;
        private static boolean inGreyColor;
        private static boolean inWhiteColor;
        private static boolean salePrice;
        private static boolean parameters;
        private static boolean numberOfSeries;
        private static boolean processorGen;
        private static boolean display;
    }

    private final String xmlFilePath;

    private final List<Laptops> laptops;
    private final Laptops laptop = new Laptops();
    private Parameters parameters;
    private ColorPrices colorPrices;

    public SAXController(String xmlFlePath) {
        this.xmlFilePath = xmlFlePath;
        laptops = new ArrayList<>();
    }

    public void parse(boolean validate) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setValidating(true);
            try {
                factory.setFeature(XMLConstants.FEATURE_TURN_VALIDATION_ON, true);
                factory.setFeature(XMLConstants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            } catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        javax.xml.parsers.SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(xmlFilePath, this);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(LaptopConstants.LAPTOP)) {
        } else if (qName.equals(LaptopConstants.BRAND)) {
            ParserBooleanContainer.brand = true;
        } else if (qName.equals(LaptopConstants.SERIES)) {
            ParserBooleanContainer.series = true;
        } else if (qName.equals(LaptopConstants.PRICE)) {
            ParserBooleanContainer.price = true;
        } else if (qName.equals(LaptopConstants.SALE_PRICE)) {
            ParserBooleanContainer.salePrice = true;
        } else if (qName.equals(LaptopConstants.COLOR_PRICES)) {
            colorPrices = new ColorPrices();
            ParserBooleanContainer.colorPrices = true;
        } else if (qName.equals(LaptopConstants.IN_BLACK_COLOR_PRICE)) {
            ParserBooleanContainer.inBlackColor = true;
        } else if (qName.equals(LaptopConstants.IN_GREY_COLOR_PRICE)) {
            ParserBooleanContainer.inGreyColor = true;
        } else if (qName.equals(LaptopConstants.IN_WHITE_COLOR_PRICE)) {
            ParserBooleanContainer.inWhiteColor = true;
        } else if (qName.equals(LaptopConstants.PARAMETERS)) {
            parameters = new Parameters();
            ParserBooleanContainer.parameters = true;
        } else if (qName.equals(LaptopConstants.NUMBER_OF_SERIES)) {
            ParserBooleanContainer.numberOfSeries = true;
        } else if (qName.equals(LaptopConstants.PROCESSOR_GEN)) {
            ParserBooleanContainer.processorGen = true;
        } else if (qName.equals(LaptopConstants.DISPLAY)) {
            ParserBooleanContainer.display = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(LaptopConstants.LAPTOP)) {
            laptops.add(laptop);

        } else if (qName.equals(LaptopConstants.BRAND)) {
            ParserBooleanContainer.brand = false;
        } else if (qName.equals(LaptopConstants.SERIES)) {
            ParserBooleanContainer.series = false;
        } else if (qName.equals(LaptopConstants.PRICE)) {
            ParserBooleanContainer.price = false;
        } else if (qName.equals(LaptopConstants.COLOR_PRICES)) {
            laptop.setColorPrices(colorPrices);
            ParserBooleanContainer.colorPrices = false;
        } else if (qName.equals(LaptopConstants.SALE_PRICE)) {
            ParserBooleanContainer.salePrice = false;
        } else if (qName.equals(LaptopConstants.IN_BLACK_COLOR_PRICE)) {
            ParserBooleanContainer.inBlackColor = false;
        } else if (qName.equals(LaptopConstants.IN_GREY_COLOR_PRICE)) {
            ParserBooleanContainer.inGreyColor = false;
        } else if (qName.equals(LaptopConstants.IN_WHITE_COLOR_PRICE)) {
            ParserBooleanContainer.inWhiteColor = false;
        } else if (qName.equals(LaptopConstants.PARAMETERS)) {
            laptop.setParameters(parameters);
            ParserBooleanContainer.parameters = false;
        } else if (qName.equals(LaptopConstants.NUMBER_OF_SERIES)) {
            ParserBooleanContainer.numberOfSeries = false;
        } else if (qName.equals(LaptopConstants.PROCESSOR_GEN)) {
            ParserBooleanContainer.processorGen = false;
        } else if (qName.equals(LaptopConstants.DISPLAY)) {
            ParserBooleanContainer.display = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (!value.isEmpty()) {
            if (ParserBooleanContainer.brand) {
                laptop.setBrand(value);
            } else if (ParserBooleanContainer.series) {
                laptop.setSeries(value);
            } else if (ParserBooleanContainer.price) {
                laptop.setPrice(Double.parseDouble(value));
            } else if (ParserBooleanContainer.salePrice) {
                laptop.setSalePrice(Double.parseDouble(value));
            } else if (ParserBooleanContainer.colorPrices) {
                if (!setColorPrices(value)) {
                    colorPrices = new ColorPrices();
                }
            } else if (ParserBooleanContainer.parameters) {
                if (!setParameters(value)) {
                    parameters = new Parameters();
                }
            }
        }
    }

    private boolean setParameters(String s) {
        if (ParserBooleanContainer.numberOfSeries) {
            parameters.setNumberOfSeries(Integer.parseInt(s));
            return true;
        } else if (ParserBooleanContainer.processorGen) {
            parameters.setProcessorGen(Integer.parseInt(s));
            return true;
        } else if (ParserBooleanContainer.display) {
            parameters.setDisplay(Double.parseDouble(s));
            return true;
        }
        return false;
    }

    private boolean setColorPrices(String s) {
        if (ParserBooleanContainer.inBlackColor) {
            colorPrices.setInBlackColor(Double.parseDouble(s));
            return true;
        } else if (ParserBooleanContainer.inGreyColor) {
            colorPrices.setInGreyColor(Double.parseDouble(s));
            return true;
        } else if (ParserBooleanContainer.inWhiteColor) {
            colorPrices.setInWhiteColor(Double.parseDouble(s));
            return true;
        }
        return false;
    }

    @Override
    public void error(SAXParseException e) {
        try {
            super.error(e);
        } catch (SAXException saxException) {
            saxException.printStackTrace();
        }
    }

    public void writeXML() {
        laptops.sort(Comparator.reverseOrder());
        Util.writeSaxStax(laptops, XMLConstants.SAX_RESULT);
    }

    public static void main(String[] args) {
        SAXController saxParser = new SAXController("input.xml");
        saxParser.parse(true);
        saxParser.writeXML();
    }
}
