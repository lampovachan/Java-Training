package com.epam.rd.java.basic.practice7.controllers;

import com.epam.rd.java.basic.practice7.constants.LaptopConstants;
import com.epam.rd.java.basic.practice7.constants.XMLConstants;
import com.epam.rd.java.basic.practice7.laptops.ColorPrices;
import com.epam.rd.java.basic.practice7.laptops.Laptops;
import com.epam.rd.java.basic.practice7.laptops.Parameters;
import com.epam.rd.java.basic.practice7.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMController {
    private String xmlFilePath;
    private Document xmlDocument;
    private List<Laptops> laptops;
    private Laptops laptop;

    public DOMController(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
        laptops = new ArrayList<>();
    }

    public List<Laptops> getLaptops() {
        return laptops;
    }

    public void parseXML(boolean validate) {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        dFactory.setNamespaceAware(true);
        if (validate) {
            try {
                dFactory.setFeature("http://xml.org/sax/features/validation", true);
                dFactory.setFeature("http://apache.org/xml/features/validation/schema", true);
            } catch (ParserConfigurationException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        try {
            xmlDocument = dFactory.newDocumentBuilder().parse(xmlFilePath);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println(e.getLocalizedMessage());
        }
        NodeList brands = xmlDocument.getElementsByTagName("laptop");
        for (int i = 0; i < brands.getLength(); i++) {
            NodeList brand = brands.item(i).getChildNodes();
            parse(brand);
            laptops.add(laptop);
        }
    }


    private void parse(NodeList brand) {
        laptop = new Laptops();
        Node node;
        for (int i = 0; i < brand.getLength(); i++) {
            node = brand.item(i);
            if (node.getNodeName().equals(LaptopConstants.BRAND)) {
                laptop.setBrand(node.getTextContent());
            } else if (node.getNodeName().equals(LaptopConstants.SERIES)) {
                laptop.setSeries(node.getTextContent());
            } else if (node.getNodeName().equals(LaptopConstants.PRICE)) {
                laptop.setPrice(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(LaptopConstants.COLOR_PRICES)) {
                laptop.setColorPrices(getColorPrices(node.getChildNodes()));
            } else if (node.getNodeName().equals(LaptopConstants.SALE_PRICE)) {
                laptop.setSalePrice(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(LaptopConstants.PARAMETERS)) {
                laptop.setParameters(getParameters(node.getChildNodes()));
            }
        }
    }

    private static ColorPrices getColorPrices(NodeList nodeList) {
        ColorPrices colorPrices = new ColorPrices();
        Node node;
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if (node.getNodeName().equals(LaptopConstants.IN_BLACK_COLOR_PRICE)) {
                colorPrices.setInBlackColor(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(LaptopConstants.IN_GREY_COLOR_PRICE)) {
               colorPrices.setInGreyColor(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(LaptopConstants.IN_WHITE_COLOR_PRICE)) {
                colorPrices.setInWhiteColor(Double.parseDouble(node.getTextContent()));
            }
        }
        return colorPrices;
    }

    private static Parameters getParameters(NodeList nodeList) {
        Parameters parameters = new Parameters();
        Node node;
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if (node.getNodeName().equals(LaptopConstants.NUMBER_OF_SERIES)) {
                parameters.setNumberOfSeries(Integer.parseInt(node.getTextContent()));
            } else if (node.getNodeName().equals(LaptopConstants.DISPLAY)) {
                parameters.setDisplay(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(LaptopConstants.PROCESSOR_GEN)) {
                parameters.setProcessorGen(Integer.parseInt(node.getTextContent()));
            }
        }
        return parameters;
    }


    private Document createDocument() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(LaptopConstants.LAPTOPS);
        document.appendChild(root);
        Element element;

        for (Laptops laptopObj : laptops) {
            Element laptopElement = document.createElement(LaptopConstants.LAPTOP);
            List<Object> listedLaptop = Util.laptopToList(laptopObj);
            List<Object> listedColorPrices = listedLaptop.subList(4, 7);
            List<Object> listedParameters = listedLaptop.subList(7, listedLaptop.size());
            for (int i = 0; i < 4; i++) {
                element = document.createElement(LaptopConstants.LAPTOP_ARRAY[i]);
                element.setTextContent(listedLaptop.get(i).toString());
                laptopElement.appendChild(element);
            }
            laptopElement.appendChild(getColorPrices(document, listedColorPrices));
            laptopElement.appendChild(getParameters(document, listedParameters));
            root.appendChild(laptopElement);
        }
        return document;

    }

    private static Element getColorPrices(Document document, List<Object> callPrice) {
        Element callPriceElement = document.createElement(LaptopConstants.COLOR_PRICES);
        Element childElement;
        for (int i = 1; i <= LaptopConstants.COLOR_PRICES_ARRAY.length; i++) {
            childElement = document.createElement(LaptopConstants.COLOR_PRICES_ARRAY[i - 1]);
            childElement.setTextContent(callPrice.get(i - 1).toString());
            callPriceElement.appendChild(childElement);
        }
        return callPriceElement;
    }

    private static Element getParameters(Document document, List<Object> parameters) {
        Element parametersElement = document.createElement(LaptopConstants.PARAMETERS);
        Element childElement;
        for (int i = 1; i <= LaptopConstants.PARAMETERS_ARRAY.length; i++) {
            childElement = document.createElement(LaptopConstants.PARAMETERS_ARRAY[i - 1]);
            childElement.setTextContent(parameters.get(i - 1).toString());
            parametersElement.appendChild(childElement);
        }
        return parametersElement;
    }

    public void saveToXML()  {

        StreamResult result = new StreamResult(new File(XMLConstants.DOM_RESULT));
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = null;
        try {
            t = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        assert t != null;
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        try {
            t.transform(new DOMSource(createDocument()), result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DOMController parser = new DOMController("input.xml");
        parser.parseXML(true);
        parser.saveToXML();
    }
}
