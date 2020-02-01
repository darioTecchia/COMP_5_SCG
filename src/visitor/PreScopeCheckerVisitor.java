package visitor;

import error.ErrorHandler;
import nodekind.NodeKind;
import semantic.SymbolTable;
import semantic.SymbolTableRecord;
import syntax.*;
import syntax.expr.*;
import syntax.expr.binaryexpr.arithop.DivOp;
import syntax.expr.binaryexpr.arithop.MinusOp;
import syntax.expr.binaryexpr.arithop.PlusOp;
import syntax.expr.binaryexpr.arithop.TimesOp;
import syntax.expr.binaryexpr.relop.*;
import syntax.expr.unaryexpr.NotExpr;
import syntax.expr.unaryexpr.SharpExpr;
import syntax.expr.unaryexpr.UminusExpr;
import syntax.statement.*;
import syntax.typedenoter.ArrayTypeDenoter;
import syntax.typedenoter.FunctionTypeDenoter;
import syntax.typedenoter.PrimitiveTypeDenoter;

import java.util.List;

public class PreScopeCheckerVisitor implements Visitor<Boolean, SymbolTable> {

  private ErrorHandler errorHandler;
  private int mainCounter = 0;

  public PreScopeCheckerVisitor(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  private boolean checkContext(List<? extends AstNode> nodes, SymbolTable arg) {
    if(nodes.isEmpty()){
      return true;
    }
    else {
      return nodes.stream().allMatch(node -> node.accept(this, arg));
    }
  }


  @Override
  public Boolean visit(Program program, SymbolTable arg) {
    arg.enterScope();
    boolean areFunctionsSafe = this.checkContext(program.getFunctions(), arg);
    //arg.exitScope();

    if(this.mainCounter >= 2) {
      this.errorHandler.reportError("Too many main method, only one can be present");
      return false;
    } else {
      return areFunctionsSafe;
    }
  }

  @Override
  public Boolean visit(Global global, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(Function function, SymbolTable arg) {
    if(function.getVariable().getName().equalsIgnoreCase("main")) {
      this.mainCounter++;
    }

    boolean isFunctionSafe = function.getVariable().accept(this, arg);
    if(!isFunctionSafe) {
      this.errorHandler.reportYetDefined(function);
    } else {
      String name = function.getVariable().getValue();
      arg.addEntry(name, new SymbolTableRecord(function.getVariable().getName(), function.codomain(), NodeKind.FUNCTION));
    }
    return isFunctionSafe;
  }

  @Override
  public Boolean visit(ParDecl parDecl, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(VarDecl varDecl, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(VarInitValue varInitValue, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ForStatement forStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(AssignStatement assignStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ReadStatement readStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(WriteStatement writeStatements, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ReturnStatement returnStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(LocalStatement localStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(NilConst nilConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(BooleanConst booleanConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(IntegerConst integerConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(FloatConst floatConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(StringConst stringConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ArrayRead arrayRead, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(FunctionCall functionCall, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(MinusOp minusOp, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(PlusOp plusOp, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(TimesOp timesOp, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(DivOp divOp, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(AndRelop andRelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(OrRelop orRelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(EqualsRelop equalsRelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(UminusExpr uminusExpr, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(NotExpr notExpr, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(SharpExpr sharpExpr, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(ArrayConst arrayConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(NopStatement nopStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(WhileStatement whileStatement, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(Variable variable, SymbolTable arg) {
    return !arg.probe(variable.getValue());
  }

  @Override
  public Boolean visit(Id id, SymbolTable arg) {
    return true;
  }

}
