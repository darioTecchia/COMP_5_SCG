package test;

import dist.*;
import syntax.Program;

public class ParserTester {

  static Lexer lexer;
  static Parser parser;

  public static void main(String[] args) throws Exception {

    lexer = new Lexer();

    if(lexer.initialize(args[0])) {
      parser = new Parser(lexer);
      System.out.println(parser.parse().value);
    } else {
      System.out.println("File not found!");
    }
  }
}
