package visitor;

import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
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

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class CodeGeneratorVisitor implements Visitor<String, SymbolTable> {

  private final String root;

  public CodeGeneratorVisitor(String root) {
    this.root = root;
  }

  private String beautify(List<? extends AstNode> nodes, StringJoiner joiner, SymbolTable table){
    nodes.forEach(node -> joiner.add(node.accept(this, table)));
    return joiner.toString();
  }

  private Consumer<ParDecl> formatArg(StringJoiner joiner, SymbolTable table){
    return new Consumer<ParDecl>() {
      @Override
      public void accept(ParDecl t) {
        joiner.add(String.format("%s", t.accept(CodeGeneratorVisitor.this, table)));
      }
    };
  }

  private String formatType(NodeType type){
    PrimitiveNodeType pType = PrimitiveNodeType.class.cast(type);
    switch(pType){
      case FLOAT:
        return "%f";
      case STRING:
        return "%s";
      default:
        return "%d";
    }
  }

  @Override
  public String visit(Program program, SymbolTable arg) {
    arg.enterScope();
    String global = program.getGlobal().accept(this, arg);
    String functionDefinitions = this.beautify(program.getFunctions(), new StringJoiner("\n"), arg);
    arg.exitScope();
    return this.root
        .replace("$declarations$", global)
        .replace("$functionsDefinitions$", functionDefinitions);
  }

  @Override
  public String visit(Global global, SymbolTable arg) {
    String varDeclarations = this.beautify(global.getVarDecls(), new StringJoiner("\n"), arg);
    return varDeclarations;
  }

  @Override
  public String visit(Function function, SymbolTable arg) {
    String functionName = function.getVariable().accept(this, arg);
    StringJoiner arguments = new StringJoiner(", ");
    arg.enterScope();
    function.getParDecls().forEach(this.formatArg(arguments, arg));
    String returnType = function.getTypeDenoter().accept(this, arg);
    String body = this.beautify(function.getStatements(), new StringJoiner("\n"), arg);
    arg.exitScope();
    if(functionName.equalsIgnoreCase("main")) returnType = "int";
    return String.format("%s %s(%s){\n%s\n}\n", returnType, functionName, arguments.toString(), body);
  }

  @Override
  public String visit(ParDecl parDecl, SymbolTable arg) {
    String parameterType = parDecl.getTypeDenoter().accept(this, arg);
    String parameterName = parDecl.getVariable().accept(this, arg);
    return String.format("%s %s", parameterType, parameterName);
  }

  @Override
  public String visit(VarDecl varDecl, SymbolTable arg) {
    String type = varDecl.getTypeDenoter().accept(this, arg);
    String varName = varDecl.getVariable().accept(this, arg);
    if(varDecl.getTypeDenoter() instanceof ArrayTypeDenoter) varName = varName + "[50]";
    if(varDecl.getVarInitValue() != null) {
      String varInitValue = varDecl.getVarInitValue().accept(this, arg);
      return String.format("%s %s = %s;", type, varName, varInitValue);
    } else {
      return String.format("%s %s;", type, varName);
    }
  }

  @Override
  public String visit(VarInitValue varInitValue, SymbolTable arg) {
    String initValue = varInitValue.getExpr().accept(this, arg);
    return initValue;
  }

  @Override
  public String visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    return primitiveType.typeFactory().cType();
  }

  @Override
  public String visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    return arrayType.getElementsType().cType();
  }

  @Override
  public String visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    return "null";
  }

  @Override
  public String visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    String condition = ifThenStatement.getExpr().accept(this, arg);
    String then = this.beautify(ifThenStatement.getStatements(), new StringJoiner("\n"), arg);
    return String.format("if(%s){\n%s\n}", condition, then);
  }

  @Override
  public String visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    String condition = ifThenElseStatement.getExpr().accept(this, arg);
    String then = this.beautify(ifThenElseStatement.getThenStatements(), new StringJoiner("\n"), arg);
    String thenElse = this.beautify(ifThenElseStatement.getElseStatements(), new StringJoiner("\n"), arg);
    return String.format("if(%s){\n%s\n} else {\n%s\n}", condition, then, thenElse);
  }

  @Override
  public String visit(ForStatement forStatement, SymbolTable arg) {
    arg.enterScope();
    String variable = forStatement.getVariable().accept(this, arg);
    String initExpr = forStatement.getInitExpr().accept(this, arg);
    String postCond = forStatement.getPostConditionExpr().accept(this, arg);
    String statements = this.beautify(forStatement.getStatements(), new StringJoiner("\n"), arg);
    arg.exitScope();
    return String.format("{\nint %s;\nfor(%s = %s; %s < %s; %s++){\n%s\n}\n}", variable, variable, initExpr, variable, postCond, variable, statements);
  }

  @Override
  public String visit(AssignStatement assignStatement, SymbolTable arg) {
    String left = assignStatement.getId().accept(this, arg);
    String right = assignStatement.getExpr().accept(this, arg);
    return String.format("%s = %s;", left, right);
  }

  @Override
  public String visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    StringJoiner funJoiner = new StringJoiner(", ");
    functionCallStatement.getFunctionParams().forEach(i -> funJoiner.add(i.accept(this, arg)));
    String fName = functionCallStatement.getId().accept(this, arg);
    return String.format("%s(%s);", fName, funJoiner.toString());
  }

  @Override
  public String visit(ReadStatement readStatement, SymbolTable arg) {
    StringJoiner scanfs = new StringJoiner("\n");
    readStatement.getVars().forEach(var -> {
      String type = this.formatType(arg.lookup(var.getValue()).get().getNodeType());
      String varName = (type == "%s" ? "&" + var.getName() : var.getName());
      scanfs.add(String.format("scanf(\"%s\", &%s);", type, varName));
    });
    return scanfs.toString();
  }

  @Override
  public String visit(WriteStatement writeStatements, SymbolTable arg) {
    StringJoiner printfs = new StringJoiner("\n");
    writeStatements.getExprs().forEach(expr -> {
      String type = this.formatType(expr.getType());
      String toPrint = expr.accept(this, arg);
      printfs.add(String.format("printf(\"%s\", %s);", type, toPrint));
    });
    return printfs.toString();
  }

  @Override
  public String visit(ReturnStatement returnStatement, SymbolTable arg) {
    String toReturn = returnStatement.getExpr().accept(this, arg);
    return String.format("return %s;", toReturn);
  }

  @Override
  public String visit(LocalStatement localStatement, SymbolTable arg) {
    arg.enterScope();
    String varDecls = this.beautify(localStatement.getVarDecls(), new StringJoiner("\n"), arg);
    String statements = this.beautify(localStatement.getStatements(), new StringJoiner("\n"), arg);
    arg.exitScope();
    return String.format("{\n%s\n%s\n}", varDecls, statements);
  }

  @Override
  public String visit(NilConst nilConst, SymbolTable arg) {
    return "NULL";
  }

  @Override
  public String visit(BooleanConst booleanConst, SymbolTable arg) {
    return Boolean.toString(booleanConst.getValue());
  }

  @Override
  public String visit(IntegerConst integerConst, SymbolTable arg) {
    return Integer.toString(integerConst.getValue());
  }

  @Override
  public String visit(FloatConst floatConst, SymbolTable arg) {
    return Float.toString(floatConst.getValue());
  }

  @Override
  public String visit(StringConst stringConst, SymbolTable arg) {
    return "\"" + stringConst.getValue() + "\"";
  }

  @Override
  public String visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    String array = arrayElemAssignStatement.getArrayExpr().accept(this, arg);
    String index = arrayElemAssignStatement.getArrayPointExpr().accept(this, arg);
    String assignee = arrayElemAssignStatement.getAssigneeExpr().accept(this, arg);
    return String.format("%s[%s] = %s;", array, index, assignee);
  }

  @Override
  public String visit(ArrayRead arrayRead, SymbolTable arg) {
    String array = arrayRead.getArrayExpr().accept(this, arg);
    String index = arrayRead.getArrayPointerExpr().accept(this, arg);
    return String.format("%s[%s]", array, index, index);
  }

  @Override
  public String visit(FunctionCall functionCall, SymbolTable arg) {
    StringJoiner funJoiner = new StringJoiner(", ");
    functionCall.getExprs().forEach(i -> funJoiner.add(i.accept(this, arg)));
    String fName = functionCall.getId().accept(this, arg);
    return String.format("%s(%s)", fName, funJoiner.toString());
  }

  @Override
  public String visit(MinusOp minusOp, SymbolTable arg) {
    String left = minusOp.getLeftOperand().accept(this, arg);
    String right = minusOp.getRightOperand().accept(this, arg);
    return String.format("%s - %s", left, right);
  }

  @Override
  public String visit(PlusOp plusOp, SymbolTable arg) {
    String left = plusOp.getLeftOperand().accept(this, arg);
    String right = plusOp.getRightOperand().accept(this, arg);
    return String.format("%s + %s", left, right);
  }

  @Override
  public String visit(TimesOp timesOp, SymbolTable arg) {
    String left = timesOp.getLeftOperand().accept(this, arg);
    String right = timesOp.getRightOperand().accept(this, arg);
    return String.format("%s * %s", left, right);
  }

  @Override
  public String visit(DivOp divOp, SymbolTable arg) {
    String left = divOp.getLeftOperand().accept(this, arg);
    String right = divOp.getRightOperand().accept(this, arg);
    return String.format("%s / %s", left, right);
  }

  @Override
  public String visit(AndRelop andRelop, SymbolTable arg) {
    String left = andRelop.getLeftOperand().accept(this, arg);
    String right = andRelop.getRightOperand().accept(this, arg);
    return String.format("%s && %s", left, right);
  }

  @Override
  public String visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    String left = greaterThanRelop.getLeftOperand().accept(this, arg);
    String right = greaterThanRelop.getRightOperand().accept(this, arg);
    return String.format("%s > %s", left, right);
  }

  @Override
  public String visit(OrRelop orRelop, SymbolTable arg) {
    String left = orRelop.getLeftOperand().accept(this, arg);
    String right = orRelop.getRightOperand().accept(this, arg);
    return String.format("%s || %s", left, right);
  }

  @Override
  public String visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    String left = greaterThanERelop.getLeftOperand().accept(this, arg);
    String right = greaterThanERelop.getRightOperand().accept(this, arg);
    return String.format("%s >= %s", left, right);
  }

  @Override
  public String visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    String left = lessThenRelop.getLeftOperand().accept(this, arg);
    String right = lessThenRelop.getRightOperand().accept(this, arg);
    return String.format("%s < %s", left, right);
  }

  @Override
  public String visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    String left = lessThenERelop.getLeftOperand().accept(this, arg);
    String right = lessThenERelop.getRightOperand().accept(this, arg);
    return String.format("%s <= %s", left, right);
  }

  @Override
  public String visit(EqualsRelop equalsRelop, SymbolTable arg) {
    String left = equalsRelop.getLeftOperand().accept(this, arg);
    String right = equalsRelop.getRightOperand().accept(this, arg);
    return String.format("%s == %s", left, right);
  }

  @Override
  public String visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    String left = notEqualsRelop.getLeftOperand().accept(this, arg);
    String right = notEqualsRelop.getRightOperand().accept(this, arg);
    return String.format("%s != %s", left, right);
  }

  @Override
  public String visit(UminusExpr uminusExpr, SymbolTable arg) {
    String left = uminusExpr.getExpr().accept(this, arg);
    return String.format("-%s", left);
  }

  @Override
  public String visit(NotExpr notExpr, SymbolTable arg) {
    String left = notExpr.getExpr().accept(this, arg);
    return String.format("!(%s)", left);
  }

  @Override
  public String visit(SharpExpr sharpExpr, SymbolTable arg) {
    return "# TODO";
  }

  @Override
  public String visit(Id id, SymbolTable arg) {
    String variableName = id.getName();
    return id.getValue();
  }

  @Override
  public String visit(ArrayConst arrayConst, SymbolTable arg) {
    return "{ }";
  }

  @Override
  public String visit(NopStatement nopStatement, SymbolTable arg) {
    return "nop();";
  }

  @Override
  public String visit(WhileStatement whileStatement, SymbolTable arg) {
    String expr = whileStatement.getExpr().accept(this, arg);
    String statements = this.beautify(whileStatement.getStatements(), new StringJoiner("\n"), arg);
    return String.format("while(%s){\n%s\n}", expr, statements);
  }

  @Override
  public String visit(Variable variable, SymbolTable arg) {
    String variableName = variable.getName();
    return variableName;
  }
}
