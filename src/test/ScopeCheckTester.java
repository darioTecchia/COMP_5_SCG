package test;

import dist.Lexer;
import dist.Parser;
import error.ErrorHandler;
import error.StackErrorHandler;
import lexical.ArrayStringTable;
import lexical.StringTable;
import semantic.FakeSymbolTable;
import semantic.StackSymbolTable;
import semantic.SymbolTable;
import syntax.Program;
import visitor.PreScopeCheckerVisitor;
import visitor.ScopeCheckerVisitor;
import visitor.TypeCheckerVisitor;

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

      PreScopeCheckerVisitor preScopeCheckerVisitor = new PreScopeCheckerVisitor(errorHandler);
      ScopeCheckerVisitor scopeCheckerVisitor = new ScopeCheckerVisitor(errorHandler);
      TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor(errorHandler);

      boolean psc = program.accept(preScopeCheckerVisitor, symbolTable);
      System.out.println("\nPre Scope checking result:\n" + psc + "\n");
      System.out.println("Pre Check Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      boolean sc = program.accept(scopeCheckerVisitor, symbolTable);
      System.out.println("\nScope checking result:\n" + sc + "\n");
      System.out.println("Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      program.accept(typeCheckerVisitor, new FakeSymbolTable((StackSymbolTable) symbolTable, stringTable));
      System.out.println("\nType checking");
      System.out.println("Type Check Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

    } else {
      System.out.println("File not found!");
    }
  }

}
