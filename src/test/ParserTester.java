package test;

import dist.*;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.ArrayStringTable;
import lexical.StringTable;
import syntax.Program;

public class ParserTester {

  static Lexer lexer;
  static Parser parser;

  public static void main(String[] args) throws Exception {

    StringTable stringTable = new ArrayStringTable();

    ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();

    lexer = new Lexer(stringTable, complexSymbolFactory);

    if(lexer.initialize(args[0])) {
      parser = new Parser(lexer, complexSymbolFactory);
      System.out.println(parser.parse().value);
    } else {
      System.out.println("File not found!");
    }
  }
}
