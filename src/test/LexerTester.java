package test;

import dist.*;
import java_cup.runtime.Symbol;
import lexical.ArrayStringTable;
import lexical.StringTable;

public class LexerTester {

  static Lexer lexicalAnalyzer;
  static Parser parser;

  public static void main(String[] args) throws Exception {

    StringTable stringTable = new ArrayStringTable();

    Lexer lexicalAnalyzer = new Lexer(stringTable);
    String filePath = args[0];

    if (lexicalAnalyzer.initialize(filePath)) {
      Symbol token;
      try {
        while ((token = lexicalAnalyzer.next_token()) != null) {
          if(token.sym == ParserSym.EOF) {
            break;
          }
          String toRet = "<" +
              ParserSym.terminalNames[token.sym] +
              (token.value == null ? ">" : (", "+token.value+">"));
          System.out.println(toRet);
        }
      } catch (Exception e) {
        System.out.println("File parsing ended!!");
      }

    } else {
      System.out.println("File not found!!");
    }
  }
}
