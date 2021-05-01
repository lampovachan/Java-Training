package com.epam.rd.java.basic.practice7.constants;

public class XMLConstants {
    public static final String VALID_XML_FILE = "input.xml";
    public static final String INVALID_XML_FILE = "input-invalid.xml";
    public static final String XSD_FILE = "input.xsd";
    public static final String SAX_RESULT = "output.sax.xml";
    public static final String STAX_RESULT = "output.stax.xml";
    public static final String DOM_RESULT = "output.dom.xml";

    public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";

    private XMLConstants() {
        throw new IllegalStateException("Utility class XMLConstants");
    }
}
