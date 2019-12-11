package template;

import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XMLTemplate implements Template<Document> {

  @Override
  public void write(String filePath, Document model) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(model);
      StreamResult result = new StreamResult(filePath);
      transformer.transform(source, result);
    } catch (TransformerConfigurationException ex) {
      System.err.println("Check configuration for generate XML");
    } catch (TransformerException ex) {
      System.err.println("Error during transforming");
    }
  }

  @Override
  public Optional<Document> create() {
    Document doc = null;
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      doc = docBuilder.newDocument();
    } catch (ParserConfigurationException ex) {
      System.err.println("Error creating XML Document" + ex);
    }
    return Optional.ofNullable(doc);
  }

}