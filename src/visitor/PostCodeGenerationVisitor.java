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

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class PostCodeGenerationVisitor implements Visitor<String, SymbolTable> {

  private final String root;

  public PostCodeGenerationVisitor(String root) {
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
        joiner.add(String.format("%s", t.accept(PostCodeGenerationVisitor.this, table)));
      }
    };
  }

  @Override
  public String visit(Program program, SymbolTable arg) {
    arg.enterScope();
    String functionDeclarations = this.beautify(program.getFunctions(), new StringJoiner("\n"), arg);
    arg.exitScope();
    return this.root
        .replace("$functionsDeclarations$", functionDeclarations);
  }

  @Override
  public String visit(Global global, SymbolTable arg) {
    return null;
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
    if(functionName.equalsIgnoreCase("main")) return "";
    return String.format("%s %s(%s);", returnType, functionName, arguments.toString(), body);
  }

  @Override
  public String visit(ParDecl parDecl, SymbolTable arg) {
    String parameterType = parDecl.getTypeDenoter().accept(this, arg);
    String parameterName = parDecl.getVariable().accept(this, arg);
    return String.format("%s %s", parameterType, parameterName);
  }

  @Override
  public String visit(VarDecl varDecl, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(VarInitValue varInitValue, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    return primitiveType.typeFactory().cType();
  }

  @Override
  public String visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    return arrayType.cType();
  }

  @Override
  public String visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    return "null";
  }

  @Override
  public String visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(ForStatement forStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(AssignStatement assignStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(ReadStatement readStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(WriteStatement writeStatements, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(ReturnStatement returnStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(LocalStatement localStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(NilConst nilConst, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(BooleanConst booleanConst, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(IntegerConst integerConst, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(FloatConst floatConst, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(StringConst stringConst, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(ArrayRead arrayRead, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(FunctionCall functionCall, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(MinusOp minusOp, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(PlusOp plusOp, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(TimesOp timesOp, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(DivOp divOp, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(AndRelop andRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(OrRelop orRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(EqualsRelop equalsRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(UminusExpr uminusExpr, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(NotExpr notExpr, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(SharpExpr sharpExpr, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(Id id, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(ArrayConst arrayConst, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(NopStatement nopStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(WhileStatement whileStatement, SymbolTable arg) {
    return null;
  }

  @Override
  public String visit(Variable variable, SymbolTable arg) {
    String variableName = variable.getName();
    return variableName;
  }
}
