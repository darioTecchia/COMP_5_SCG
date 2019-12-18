package visitor;

import semantic.SymbolTable;

import nodetype.NodeType;

import syntax.*;
import syntax.expr.*;
import syntax.typedenoter.*;
import syntax.statement.*;
import syntax.expr.unaryexpr.*;
import syntax.expr.binaryexpr.arithop.*;
import syntax.expr.binaryexpr.relop.*;

/**
 * TypeCheckingVisitor
 */
public class TypeCheckerVisitor implements Visitor<NodeType, SymbolTable> {

  @Override
  public NodeType visit(Program program, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(Global global, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(Function function, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ParDecl parDecl, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(VarDecl varDecl, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(VarInitValue varInitValue, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ForStatement forStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(AssignStatement assignStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ReadStatement readStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(WriteStatement writeStatements, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ReturnStatement returnStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(LocalStatement localStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(NilConst nilConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(BooleanConst booleanConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(IntegerConst integerConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(FloatConst floatConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(StringConst stringConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ArrayRead arrayRead, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(FunctionCall functionCall, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(MinusOp minusOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(PlusOp plusOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(TimesOp timesOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(DivOp divOp, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(AndRelop andRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(OrRelop orRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(EqualsRelop equalsRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(UminusExpr uminusExpr, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(NotExpr notExpr, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(SharpExpr sharpExpr, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(Id id, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(ArrayConst arrayConst, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeType visit(NopStatement nopStatement, SymbolTable arg) {
    // TODO Auto-generated method stub
    return null;
  }

  
}