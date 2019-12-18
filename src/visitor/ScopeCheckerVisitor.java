package visitor;

import java.util.List;

import error.ErrorHandler;

import nodekind.NodeKind;
import semantic.SymbolTable;

import semantic.SymbolTableRecord;
import syntax.*;
import syntax.expr.*;
import syntax.typedenoter.*;
import syntax.statement.*;
import syntax.expr.unaryexpr.*;
import syntax.expr.binaryexpr.arithop.*;
import syntax.expr.binaryexpr.relop.*;

/**
 * ConcreteContextVisitor
 */
public class ScopeCheckerVisitor implements Visitor<Boolean, SymbolTable> {

  private ErrorHandler errorHandler;

  public ScopeCheckerVisitor(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  private boolean checkContext(List<? extends AstNode> nodes, SymbolTable arg) {
    if(nodes.isEmpty())
      return true;
    else
      return nodes.stream().allMatch(node -> node.accept(this, arg));
  }

  @Override
  public Boolean visit(Program program, SymbolTable arg) {
    // arg.enterScope();
    boolean isGlobalSafe = program.getGlobal().accept(this, arg);
    boolean areFunctionsSafe = this.checkContext(program.getFunctions(), arg);
    boolean isProgramSafe = isGlobalSafe && areFunctionsSafe;
    if(!isProgramSafe) {
      this.errorHandler.reportError("Program Error", program);
    }
    // arg.exitScope();
    return isProgramSafe;
  }

  @Override
  public Boolean visit(Global global, SymbolTable arg) {
    arg.enterScope();
    boolean areVarDeclsSafe = this.checkContext(global.getVarDecls(), arg);
    boolean isGlobalSafe = areVarDeclsSafe;
    if(!isGlobalSafe) {
      this.errorHandler.reportError("Global Error", global);
    }
    arg.exitScope();
    return areVarDeclsSafe;
  }

  @Override
  public Boolean visit(Function function, SymbolTable arg) {
    boolean isFunctionSafe = function.getId().accept(this, arg);
    if(!isFunctionSafe) {
      this.errorHandler.reportYetDefined(function);
    } else {
      arg.enterScope();
      boolean areParDeclsSafe = this.checkContext(function.getParDecls(), arg);
      boolean areStatementsSafe = this.checkContext(function.getStatements(), arg);
      boolean areTypeSafe = function.getTypeDenoter().accept(this, arg);
      isFunctionSafe = areStatementsSafe && areStatementsSafe && areTypeSafe;
      if(!isFunctionSafe) {
        this.errorHandler.reportError("Function Eeclaration error", function);
      } else {
        arg.exitScope();
        String name = function.getId().getValue();
        arg.addEntry(name, new SymbolTableRecord(function.getTypeDenoter().typeFactory(), NodeKind.FUNCTION));
      }

    }
    return null;
  }

  @Override
  public Boolean visit(ParDecl parDecl, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(VarDecl varDecl, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(VarInitValue varInitValue, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ForStatement forStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(AssignStatement assignStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ReadStatement readStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(WriteStatement writeStatements, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ReturnStatement returnStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(LocalStatement localStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(NilConst nilConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(BooleanConst booleanConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(IntegerConst integerConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(FloatConst floatConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(StringConst stringConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ArrayRead arrayRead, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(FunctionCall functionCall, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(MinusOp minusOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(PlusOp plusOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(TimesOp timesOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(DivOp divOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(AndRelop andRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(OrRelop orRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(EqualsRelop equalsRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(UminusExpr uminusExpr, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(NotExpr notExpr, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(SharpExpr sharpExpr, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(Id id, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(ArrayConst arrayConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean visit(NopStatement nopStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  
}