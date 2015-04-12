package com.yoolean.common.util;

import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by chenhang on 2015/4/11.
 */
public class XMLUtil {
    public static String hotSpotToXML(HotSpot hotSpot) throws ParserConfigurationException, TransformerException {
        Document document = toDocument(hotSpot);
        return toXMLString(document);
    }

    public static HotSpot hotSpotFromXML(String xml) throws ParserConfigurationException, IOException, SAXException {
        Document document = getDocument(xml);
        Element root = document.getDocumentElement();
        return getHotSpot(root);
    }

    public static HotArea hotAreaFromXML(String xml) throws ParserConfigurationException, IOException, SAXException {
        Document document = getDocument(xml);
        Element root = document.getDocumentElement();
        return getHotArea(root);
    }

    private static HotArea getHotArea(Element root) {
        HotArea hotArea=new HotArea();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            HotSpot hotSpot=getHotSpot(node);
            hotArea.getHotSpots().add(hotSpot);
        }
        return hotArea;
    }

    private static HotSpot getHotSpot(Node root) {
        HotSpot hotSpot = new HotSpot();

        String id = getTextValueOfChildNode(root, "id");
        hotSpot.setId(id);

        String owner = getTextValueOfChildNode(root, "owner");
        hotSpot.setOwner(owner);

        String status = getTextValueOfChildNode(root, "status");
        hotSpot.setStatus(status);

        String type = getTextValueOfChildNode(root, "type");
        hotSpot.setType(type);

        String title = getTextValueOfChildNode(root, "title");
        hotSpot.setTitle(title);

        String description = getTextValueOfChildNode(root, "description");
        hotSpot.setDescription(description);

        Node location = getChildNode(root, "location");
        double latitude = Double.parseDouble(getTextValueOfChildNode(location, "latitude"));
        double longitude = Double.parseDouble(getTextValueOfChildNode(location, "longitude"));
        hotSpot.setLocation(new Location(latitude, longitude));
        return hotSpot;
    }

    private static Document getDocument(String xml) throws SAXException, IOException, ParserConfigurationException {
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        return getDocumentBuilder().parse(inputStream);
    }

    private static Node getChildNode(Node root, String tagName) {
        Node child = null;
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (tagName.equals(node.getNodeName())) {
                child = node;
                break;
            }
        }
        return child;
    }

    private static String getTextValueOfChildNode(Node root, String tagName) {
        String value = null;
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (tagName.equals(node.getNodeName())) {
                value = node.getTextContent();
                break;
            }
        }
        return value;
    }

    private static Document toDocument(HotSpot hotSpot) throws ParserConfigurationException {
        DocumentBuilder builder = getDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("hotSpot");
        document.appendChild(root);

        createChild(document, root, "id", hotSpot.getId());
        createChild(document, root, "owner", hotSpot.getOwner());
        createChild(document, root, "status", hotSpot.getStatus());
        createChild(document, root, "type", hotSpot.getType());
        createChild(document, root, "title", hotSpot.getTitle());
        createChild(document, root, "description", hotSpot.getDescription());

        Element location = createChild(document, root, "location");
        createChild(document, location, "latitude", String.valueOf(hotSpot.getLocation().getLatitude()));
        createChild(document, location, "longitude", String.valueOf(hotSpot.getLocation().getLongitude()));
        return document;
    }

    private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    private static String toXMLString(Document document) throws TransformerException {
        StringWriter stringWriter = new StringWriter();
        Transformer serializer = getTransformer();
        serializer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    private static Transformer getTransformer() throws TransformerConfigurationException {
        return TransformerFactory.newInstance().newTransformer();
    }

    private static Element createChild(Document document, Element root, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        root.appendChild(element);
        return element;
    }

    private static Element createChild(Document document, Element root, String tagName) {
        Element element = document.createElement(tagName);
        root.appendChild(element);
        return element;
    }
}
