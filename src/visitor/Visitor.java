package visitor;

import syntax.*;
import syntax.statement.*;
import syntax.type.*;
import syntax.expr.*;
import syntax.expr.binaryexpr.arithop.*;
import syntax.expr.binaryexpr.relop.*;
import syntax.expr.unaryexpr.NotExpr;
import syntax.expr.unaryexpr.SharpExpr;
import syntax.expr.unaryexpr.UminusExpr;

public interface Visitor<T, P> {

  T visit(Program program, P arg);

  T visit(Global global, P arg);

  T visit(Function function, P arg);

  T visit(ParDecl parDecl, P arg);

  T visit(VarDecl varDecl, P arg);

  T visit(VarInitValue varInitValue, P arg);

  T visit(PrimitiveType primitiveType, P arg);

  T visit(ArrayType arrayType, P arg);

  T visit(FunctionType functionType, P arg);

  T visit(IfThenStatement ifThenStatement, P arg);

  T visit(IfThenElseStatement ifThenElseStatement, P arg);

  T visit(ForStatement forStatement, P arg);

  T visit(AssignStatement assignStatement, P arg);

  T visit(FunctionCallStatement functionCallStatement, P arg);

  T visit(ReadStatement readStatement, P arg);

  T visit(WriteStatement writeStatements, P arg);

  T visit(ReturnStatement returnStatement, P arg);

  T visit(LocalStatement localStatement, P arg);

  T visit(NilConst nilConst, P arg);

  T visit(BooleanConst booleanConst, P arg);

  T visit(IntegerConst integerConst, P arg);

  T visit(FloatConst floatConst, P arg);

  T visit(StringConst stringConst, P arg);

  T visit(ArrayElemAssignStatement arrayElemAssignStatement, P arg);

  T visit(ArrayRead arrayRead, P arg);

  T visit(FunctionCall functionCall, P arg);

  T visit(MinusOp minusOp, P arg);

  T visit(PlusOp plusOp, P arg);

  T visit(TimesOp timesOp, P arg);

  T visit(DivOp divOp, P arg);

  T visit(AndRelop andRelop, P arg);

  T visit(GreaterThanRelop greaterThanRelop, P arg);

  T visit(OrRelop orRelop, P arg);

  T visit(GreaterThanERelop greaterThanERelop, P arg);

  T visit(LessThenRelop lessThenRelop, P arg);

  T visit(LessThenERelop lessThenERelop, P arg);

  T visit(EqualsRelop equalsRelop, P arg);

  T visit(NotEqualsRelop notEqualsRelop, P arg);

  T visit(UminusExpr uminusExpr, P arg);

  T visit(NotExpr notExpr, P arg);

  T visit(SharpExpr sharpExpr, P arg);

  T visit(Id id, P arg);

  T visit(ArrayConst arrayConst, P arg);

  T visit(NopStatement nopStatement, P arg);
}
