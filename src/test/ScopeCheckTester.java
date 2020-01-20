package test;

import dist.Lexer;
import dist.Parser;
import error.ErrorHandler;
import error.StackErrorHandler;
import lexical.ArrayStringTable;
import lexical.StringTable;
import semantic.StackSymbolTable;
import semantic.SymbolTable;
import syntax.Program;
import visitor.ScopeCheckerVisitor;

public class ScopeCheckTester {


  public static void main(String[] args) throws Exception {

    Lexer lexer;
    Parser parser;
    StringTable stringTable = new ArrayStringTable();
    SymbolTable symbolTable = new StackSymbolTable(stringTable);
    ErrorHandler errorHandler = new StackErrorHandler();

    lexer = new Lexer(stringTable);

    if(lexer.initialize(args[0])) {
      System.out.println(args[0]);

      parser = new Parser(lexer);

      Program program = (Program) parser.parse().value;

      System.out.println(program);

      ScopeCheckerVisitor scopeCheckerVisitor = new ScopeCheckerVisitor(errorHandler);

      boolean sc = program.accept(scopeCheckerVisitor, symbolTable);

      System.out.println("\nScope checking result:\n" + sc + "\n");

      System.out.println("Symbol table: \n" + symbolTable);

      System.out.println("Errors:");
      errorHandler.logErrors();

    } else {
      System.out.println("File not found!");
    }
  }

}
