package visitor;

import semantic.SymbolTable;
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

public class PreScopeCheckerVisitor implements Visitor<Void, SymbolTable> {


  @Override
  public Void visit(Program program, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(Global global, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(Function function, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ParDecl parDecl, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(VarDecl varDecl, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(VarInitValue varInitValue, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ForStatement forStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(AssignStatement assignStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ReadStatement readStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(WriteStatement writeStatements, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ReturnStatement returnStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(LocalStatement localStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(NilConst nilConst, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(BooleanConst booleanConst, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(IntegerConst integerConst, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(FloatConst floatConst, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(StringConst stringConst, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ArrayRead arrayRead, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(FunctionCall functionCall, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(MinusOp minusOp, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(PlusOp plusOp, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(TimesOp timesOp, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(DivOp divOp, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(AndRelop andRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(OrRelop orRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(EqualsRelop equalsRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(UminusExpr uminusExpr, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(NotExpr notExpr, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(SharpExpr sharpExpr, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(Id id, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(ArrayConst arrayConst, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(NopStatement nopStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(WhileStatement whileStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public Void visit(Variable variable, SymbolTable arg) {
    return null;
  }
}
