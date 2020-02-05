package test;

import dist.Lexer;
import dist.Parser;
import error.ErrorHandler;
import error.StackErrorHandler;
import lexical.ArrayStringTable;
import lexical.StringTable;
import org.w3c.dom.Document;
import semantic.StackSymbolTable;
import syntax.Program;
import template.XMLTemplate;
import visitor.ConcreteXMLVisitor;
import visitor.PreScopeCheckerVisitor;
import visitor.ScopeCheckerVisitor;
import visitor.TypeCheckerVisitor;

public class ScopeCheckTester {


  public static void main(String[] args) throws Exception {

    Lexer lexer;
    Parser parser;
    StringTable stringTable = new ArrayStringTable();
    StackSymbolTable symbolTable = new StackSymbolTable(stringTable);
    ErrorHandler errorHandler = new StackErrorHandler();

    lexer = new Lexer(stringTable);

    if(lexer.initialize(args[0])) {
      System.out.println(args[0]);

      parser = new Parser(lexer);

      Program program = (Program) parser.parse().value;

      System.out.println(program);

      PreScopeCheckerVisitor preScopeCheckerVisitor = new PreScopeCheckerVisitor(errorHandler);
      ScopeCheckerVisitor scopeCheckerVisitor = new ScopeCheckerVisitor(errorHandler);
      TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor(errorHandler);
      ConcreteXMLVisitor xmlVisitor = new ConcreteXMLVisitor();

      XMLTemplate xmlTemplate = new XMLTemplate();
      Document xmlDocument = xmlTemplate.create().get();
      program.accept(xmlVisitor, xmlDocument);
      xmlTemplate.write(args[0] + ".xml", xmlDocument);

      boolean psc = program.accept(preScopeCheckerVisitor, symbolTable);
      System.out.println("\nPre Scope checking result:\n" + psc + "\n");
      System.out.println("Pre Check Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

      boolean sc = program.accept(scopeCheckerVisitor, symbolTable);
      System.out.println("\nScope checking result:\n" + sc + "\n");
      System.out.println("Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

      program.accept(typeCheckerVisitor, symbolTable);
      System.out.println("\nType checking");
      System.out.println("Type Check Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

    } else {
      System.out.println("File not found!");
    }
  }

}
