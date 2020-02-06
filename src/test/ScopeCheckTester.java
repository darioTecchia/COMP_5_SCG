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
import template.CTemplate;
import template.XMLTemplate;
import visitor.*;

public class ScopeCheckTester {


  public static void main(String[] args) throws Exception {

    boolean isWin = System.getProperty("os.name").toLowerCase().contains("windows");

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

      ConcreteXMLVisitor xmlVisitor = new ConcreteXMLVisitor();
      PreScopeCheckerVisitor preScopeCheckerVisitor = new PreScopeCheckerVisitor(errorHandler);
      ScopeCheckerVisitor scopeCheckerVisitor = new ScopeCheckerVisitor(errorHandler);
      TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor(errorHandler);

      CTemplate cTemplate = new CTemplate();
      String model = cTemplate.create().get();
      CodeGeneratorVisitor codeGeneratorVisitor = new CodeGeneratorVisitor(model);

      XMLTemplate xmlTemplate = new XMLTemplate();
      Document xmlDocument = xmlTemplate.create().get();
      program.accept(xmlVisitor, xmlDocument);
      xmlTemplate.write(args[0], xmlDocument);

      System.out.println("Pre Scope checking\n");
      boolean psc = program.accept(preScopeCheckerVisitor, symbolTable);
      System.out.println("\nPre Scope checking result:\n" + psc + "\n");
      System.out.println("Pre Check Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

      System.out.println("\nScope checking\n");
      boolean sc = program.accept(scopeCheckerVisitor, symbolTable);
      System.out.println("\nScope checking result:\n" + sc + "\n");
      System.out.println("Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

      System.out.println("\nType checking");
      program.accept(typeCheckerVisitor, symbolTable);
      System.out.println("Type Check Symbol table: \n" + symbolTable);
      System.out.println("Errors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

      model = program.accept(codeGeneratorVisitor, symbolTable);
      System.out.println("\nCode generation");
      System.out.println("Code Generation Symbol table: \n" + symbolTable);
      System.out.println("\nCode: \n" + model.toString());
      System.out.println("\nErrors:");
      errorHandler.logErrors();

      symbolTable.resetLevel();

      PostCodeGenerationVisitor postCodeGenerationVisitor  = new PostCodeGenerationVisitor(model);
      model = program.accept(postCodeGenerationVisitor, symbolTable);
      System.out.println("\nCode generation");
      System.out.println("Code Generation Symbol table: \n" + symbolTable);
      System.out.println("\nCode: \n" + model.toString());
      System.out.println("\nErrors:");
      errorHandler.logErrors();
      cTemplate.write(args[0], model.toString());

      symbolTable.resetLevel();

    } else {
      System.out.println("File not found!");
    }
  }

}
