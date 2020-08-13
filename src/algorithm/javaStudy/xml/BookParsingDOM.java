package algorithm.javaStudy.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class BookParsingDOM {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();//DOM Parser

        Document doc = parser.parse("src/algorithm/javaStudy/xml/book.xml");//parsing
        NodeList list = doc.getElementsByTagName("book");
        //NodeList alist = doc.getElementsByTagName("author");

        for (int i = 0; i < list.getLength(); i++) {
            Node book = list.item(i);//책 한권

            NamedNodeMap map = book.getAttributes();
            Node attr = map.getNamedItem("kind");
            //Node aeach = alist.item(i);

            String child = attr.getNodeValue();
            //Node achild = aeach.getFirstChild();

            System.out.println(child);
        }
    }
}