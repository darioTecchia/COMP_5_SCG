package cli;

import dist.Lexer;
import dist.Parser;
import error.ErrorHandler;
import error.StackErrorHandler;
import java_cup.runtime.ComplexSymbolFactory;
import lexical.ArrayStringTable;
import lexical.StringTable;
import org.w3c.dom.Document;
import semantic.StackSymbolTable;
import syntax.Program;
import template.CTemplate;
import template.XMLTemplate;
import visitor.*;

import java.io.FileNotFoundException;

public class MyPallene {

  private Parser parser;
  private ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
  private StringTable stringTable = new ArrayStringTable();
  private Lexer lexer = new Lexer(stringTable, complexSymbolFactory);
  private StackSymbolTable symbolTable = new StackSymbolTable(stringTable);
  private ErrorHandler errorHandler = new StackErrorHandler();

  private String filePath;
  private boolean verbose;

  public MyPallene(String filePath) {
    this.filePath = filePath;
    this.verbose = false;
  }

  public MyPallene(String filePath, boolean verbose) {
    this.filePath = filePath;
    this.verbose = verbose;
  }

  private void debug(String message) {
    if(this.verbose) {
      System.out.println(message);
    }
  }

  public void compile() throws Exception {
    if(lexer.initialize(this.filePath)) {
      debug(this.filePath);

      parser = new Parser(lexer, complexSymbolFactory);

      Program program = (Program) parser.parse().value;

      debug("Parsing result: " + program);

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
      xmlTemplate.write(this.filePath, xmlDocument);

      debug("Pre Scope checking\n");
      boolean psc = program.accept(preScopeCheckerVisitor, symbolTable);
      debug("\nPre Scope checking result:\n" + psc + "\n");
      debug("Pre Check Symbol table: \n" + symbolTable);

      if(errorHandler.haveErrors()) {
        errorHandler.logErrors();
        throw new Exception("Error during Pre Scope Checking phase");
      }

      symbolTable.resetLevel();

      debug("\nScope checking\n");
      boolean sc = program.accept(scopeCheckerVisitor, symbolTable);
      debug("\nScope checking result:\n" + sc + "\n");
      debug("Symbol table: \n" + symbolTable);

      if(errorHandler.haveErrors()) {
        errorHandler.logErrors();
        throw new Exception("Error during Scope Checking phase");
      }

      symbolTable.resetLevel();

      debug("\nType checking");
      program.accept(typeCheckerVisitor, symbolTable);
      debug("Type Check Symbol table: \n" + symbolTable);

      if(errorHandler.haveErrors()) {
        errorHandler.logErrors();
        throw new Exception("Error during Type Checking phase");
      }

      symbolTable.resetLevel();

      model = program.accept(codeGeneratorVisitor, symbolTable);
      debug("\nCode generation");
      debug("Code Generation Symbol table: \n" + symbolTable);
      debug("\nCode: \n" + model.toString());

      if(errorHandler.haveErrors()) {
        errorHandler.logErrors();
        throw new Exception("Error during Code Generation phase");
      }

      symbolTable.resetLevel();

      PostCodeGenerationVisitor postCodeGenerationVisitor  = new PostCodeGenerationVisitor(model);
      model = program.accept(postCodeGenerationVisitor, symbolTable);
      debug("\nCode generation");
      debug("Code Generation Symbol table: \n" + symbolTable);
      debug("\nCode: \n" + model.toString());

      if(errorHandler.haveErrors()) {
        errorHandler.logErrors();
        throw new Exception("Error during Post Code Generation phase");
      }

      cTemplate.write(this.filePath, model.toString());

      symbolTable.resetLevel();

    } else {
      throw new Exception();
    }
  }

}
