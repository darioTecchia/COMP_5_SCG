package visitor;

import java.util.function.Consumer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import syntax.*;
import syntax.expr.*;
import syntax.type.*;
import syntax.statement.*;
import syntax.expr.unaryexpr.*;
import syntax.expr.binaryexpr.arithop.*;
import syntax.expr.binaryexpr.relop.*;

public class ConcreteXMLVisitor implements Visitor<Element, Document> {

  private Consumer<? super AstNode> addParent(Element parent, Document arg){
    return (AstNode node) -> parent.appendChild(node.accept(this, arg));
  }

  @Override
  public Element visit(Program program, Document arg) {
    Element element = arg.createElement("Program");
    element.appendChild(program.getGlobal().accept(this, arg));
    program.getFunctions().forEach(addParent(element, arg));
    arg.appendChild(element);
    return element;
  }

  @Override
  public Element visit(Global global, Document arg) {
    Element element = arg.createElement("Global");
    global.getVarDecls().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(Function function, Document arg) {
    Element element = arg.createElement("Function");
    element.appendChild(function.getId().accept(this, arg));
    function.getParDecls().forEach(addParent(element, arg));
    element.appendChild(function.getType().accept(this, arg));
    function.getStatements().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(ParDecl parDecl, Document arg) {
    Element element = arg.createElement("ParDecl");
    element.appendChild(parDecl.getId().accept(this, arg));
    element.appendChild((parDecl.getType().accept(this, arg)));
    return element;
  }

  @Override
  public Element visit(VarDecl varDecl, Document arg) {
    Element element = arg.createElement("VarDecl");
    element.appendChild(varDecl.getId().accept(this, arg));
    element.appendChild(varDecl.getType().accept(this, arg));
    element.appendChild(varDecl.getVarInitValue().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(VarInitValue varInitValue, Document arg) {
    Element element = arg.createElement("VarInitValue");
    element.appendChild(varInitValue.getExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(PrimitiveType primitiveType, Document arg) {
    Element element = arg.createElement("PrimitiveType");
    element.setAttribute("kind", primitiveType.getKind());
    return element;
  }

  @Override
  public Element visit(ArrayType arrayType, Document arg) {
    Element element = arg.createElement("ArrayType");
    element.appendChild((arrayType.getType().accept(this, arg)));
    return element;
  }

  @Override
  public Element visit(FunctionType functionType, Document arg) {
    Element element = arg.createElement("FunctionType");
    functionType.getTypes().forEach(addParent(element, arg));
    element.appendChild(functionType.getReturnType().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(IfThenStatement ifThenStatement, Document arg) {
    Element element = arg.createElement("IfThenStatement");
    element.appendChild(ifThenStatement.getExpr().accept(this, arg));
    ifThenStatement.getStatements().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(IfThenElseStatement ifThenElseStatement, Document arg) {
    Element element = arg.createElement("IfThenElseStatement");
    element.appendChild(ifThenElseStatement.getExpr().accept(this, arg));
    ifThenElseStatement.getThenStatements().forEach(addParent(element, arg));
    ifThenElseStatement.getElseStatements().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(ForStatement forStatement, Document arg) {
    Element element = arg.createElement("ForStatement");
    element.appendChild(forStatement.getInitExpr().accept(this, arg));
    element.appendChild(forStatement.getPostConditionExpr().accept(this, arg));
    forStatement.getStatements().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(AssignStatement assignStatement, Document arg) {
    Element element = arg.createElement("AssignStatement");
    element.appendChild(assignStatement.getExpr().accept(this, arg));
    element.appendChild(assignStatement.getId().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(FunctionCallStatement functionCallStatement, Document arg) {
    Element element = arg.createElement("FunctionCallStatement");
    functionCallStatement.getFunctionParams().forEach(addParent(element, arg));
    element.appendChild(functionCallStatement.getId().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(ReadStatement readStatement, Document arg) {
    Element element = arg.createElement("ReadStatement");
    readStatement.getVars().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(WriteStatement writeStatements, Document arg) {
    Element element = arg.createElement("WriteStatement");
    writeStatements.getExprs().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(ReturnStatement returnStatement, Document arg) {
    Element element = arg.createElement("ReturnStatement");
    element.appendChild(returnStatement.getExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(LocalStatement localStatement, Document arg) {
    Element element = arg.createElement("LocalStatement");
    localStatement.getVarDecls().forEach(addParent(element, arg));
    localStatement.getStatements().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(NilConst nilConst, Document arg) {
    Element element = arg.createElement("NilConst");
    element.setAttribute("value", "null");
    return element;
  }

  @Override
  public Element visit(BooleanConst booleanConst, Document arg) {
    Element element = arg.createElement("BooleanConst");
    element.setAttribute("value", booleanConst.getValue().toString());
    return element;
  }

  @Override
  public Element visit(IntegerConst integerConst, Document arg) {
    Element element = arg.createElement("IntegerConst");
    element.setAttribute("value", String.valueOf(integerConst.getValue()));
    return element;
  }

  @Override
  public Element visit(FloatConst floatConst, Document arg) {
    Element element = arg.createElement("FloatConst");
    element.setAttribute("value", String.valueOf(floatConst.getValue()));
    return element;
  }

  @Override
  public Element visit(StringConst stringConst, Document arg) {
    Element element = arg.createElement("StringConst");
    element.setAttribute("value", stringConst.getValue());
    return element;
  }

  @Override
  public Element visit(ArrayElemAssignStatement arrayElemAssignStatement, Document arg) {
    Element element = arg.createElement("ArrayElemAssignStatement");
    element.appendChild(arrayElemAssignStatement.getArrayExpr().accept(this, arg));
    element.appendChild(arrayElemAssignStatement.getAssigneeExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(ArrayRead arrayRead, Document arg) {
    Element element = arg.createElement("ArrayRead");
    element.appendChild(arrayRead.getArrayExpr().accept(this, arg));
    element.appendChild(arrayRead.getArrayPointerExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(FunctionCall functionCall, Document arg) {
    Element element = arg.createElement("FunctionCall");
    element.appendChild(functionCall.getId().accept(this, arg));
    functionCall.getExprs().forEach(addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(MinusOp minusOp, Document arg) {
    Element element = arg.createElement("MinusOp");
    element.appendChild(minusOp.getLeftOperand().accept(this, arg));
    element.appendChild(minusOp.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(PlusOp plusOp, Document arg) {
    Element element = arg.createElement("PlusOp");
    element.appendChild(plusOp.getLeftOperand().accept(this, arg));
    element.appendChild(plusOp.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(TimesOp timesOp, Document arg) {
    Element element = arg.createElement("TimesOp");
    element.appendChild(timesOp.getLeftOperand().accept(this, arg));
    element.appendChild(timesOp.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(DivOp divOp, Document arg) {
    Element element = arg.createElement("DivOp");
    element.appendChild(divOp.getLeftOperand().accept(this, arg));
    element.appendChild(divOp.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(AndRelop andRelop, Document arg) {
    Element element = arg.createElement("AndRelop");
    element.appendChild(andRelop.getLeftOperand().accept(this, arg));
    element.appendChild(andRelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(GreaterThanRelop greaterThanRelop, Document arg) {
    Element element = arg.createElement("GreaterThanRelop");
    element.appendChild(greaterThanRelop.getLeftOperand().accept(this, arg));
    element.appendChild(greaterThanRelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(OrRelop orRelop, Document arg) {
    Element element = arg.createElement("OrRelop");
    element.appendChild(orRelop.getLeftOperand().accept(this, arg));
    element.appendChild(orRelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(GreaterThanERelop greaterThanERelop, Document arg) {
    Element element = arg.createElement("GreaterThanERelop");
    element.appendChild(greaterThanERelop.getLeftOperand().accept(this, arg));
    element.appendChild(greaterThanERelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(LessThenRelop lessThenRelop, Document arg) {
    Element element = arg.createElement("LessThenRelop");
    element.appendChild(lessThenRelop.getLeftOperand().accept(this, arg));
    element.appendChild(lessThenRelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(LessThenERelop lessThenERelop, Document arg) {
    Element element = arg.createElement("LessThenERelop");
    element.appendChild(lessThenERelop.getLeftOperand().accept(this, arg));
    element.appendChild(lessThenERelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(EqualsRelop equalsRelop, Document arg) {
    Element element = arg.createElement("EqualsRelop");
    element.appendChild(equalsRelop.getLeftOperand().accept(this, arg));
    element.appendChild(equalsRelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(NotEqualsRelop notEqualsRelop, Document arg) {
    Element element = arg.createElement("NotEqualsRelop");
    element.appendChild(notEqualsRelop.getLeftOperand().accept(this, arg));
    element.appendChild(notEqualsRelop.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(UminusExpr uminusExpr, Document arg) {
    Element element = arg.createElement("UminusExpr");
    element.appendChild(uminusExpr.getExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(NotExpr notExpr, Document arg) {
    Element element = arg.createElement("NotExpr");
    element.appendChild(notExpr.getExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(SharpExpr sharpExpr, Document arg) {
    Element element = arg.createElement("SharpExpr");
    element.appendChild(sharpExpr.getExpr().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(Id id, Document arg) {
    Element element = arg.createElement("Id");
    element.setAttribute("lexeme", id.getValue());
    return element;
  }

  @Override
  public Element visit(ArrayConst arrayConst, Document arg) {
    Element element = arg.createElement("ArrayConst");
    element.appendChild(arrayConst.getType().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(NopStatement nopStatement, Document arg) {
    Element element = arg.createElement("NopStatement");
    return element;
  }

}