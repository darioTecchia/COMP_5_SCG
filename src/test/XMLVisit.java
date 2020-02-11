package test;

import dist.*;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.ArrayStringTable;
import lexical.StringTable;
import org.w3c.dom.Document;
import syntax.Program;
import template.XMLTemplate;
import visitor.ConcreteXMLVisitor;

/**
 * XMLVisit
 */
public class XMLVisit {

  static Lexer lexer;
  static Parser parser;

  public static void main(String[] args) throws Exception {

    StringTable stringTable = new ArrayStringTable();

    ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();

    lexer = new Lexer(stringTable, complexSymbolFactory);

    if(lexer.initialize(args[0])) {
      System.out.println(args[0]);
      parser = new Parser(lexer, complexSymbolFactory);

      Program program = (Program) parser.parse().value;

      XMLTemplate xmlTemplate = new XMLTemplate();

      Document xmlDocument = xmlTemplate.create().get();

      ConcreteXMLVisitor xmlVisitor = new ConcreteXMLVisitor();

      program.accept(xmlVisitor, xmlDocument);

      xmlTemplate.write(args[0] + ".xml", xmlDocument);

    } else {
      System.out.println("File not found!");
    }
  }
}