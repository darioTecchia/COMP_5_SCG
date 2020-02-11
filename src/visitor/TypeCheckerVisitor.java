package visitor;

import error.ErrorHandler;
import nodetype.*;
import semantic.SymbolTable;

import syntax.*;
import syntax.expr.*;
import syntax.typedenoter.*;
import syntax.statement.*;
import syntax.expr.unaryexpr.*;
import syntax.expr.binaryexpr.arithop.*;
import syntax.expr.binaryexpr.relop.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * TypeCheckingVisitor
 */
public class TypeCheckerVisitor implements Visitor<NodeType, SymbolTable> {

  private ErrorHandler errorHandler;

  private NodeType lastTypeToReturn = null;
  private HashMap<String, ArrayList<NodeType>> returnedValuesPerId = new HashMap<>();
  private String lastFunction = null;

  public TypeCheckerVisitor(ErrorHandler errorHandler) {
  this.errorHandler = errorHandler;
  }

  private Consumer<? super AstNode> typeCheck(SymbolTable arg){
    return (AstNode node) -> node.accept(this, arg);
  }

  @Override
  public NodeType visit(Program program, SymbolTable arg) {
    arg.enterScope();
    if(program.getGlobal() != null) {
      program.getGlobal().accept(this, arg);
    }
    program.getFunctions().forEach(this.typeCheck(arg));
    arg.exitScope();
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(Global global, SymbolTable arg) {
    global.getVarDecls().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(Function function, SymbolTable arg) {
    this.lastTypeToReturn = function.codomain();
    this.returnedValuesPerId.putIfAbsent(function.getVariable().getValue(), new ArrayList<>());
    this.lastFunction = function.getVariable().getValue();

    NodeType functionType = function.getVariable().accept(this, arg);
    arg.enterScope();
    function.getParDecls().forEach(this.typeCheck(arg));
    function.getStatements().forEach(this.typeCheck(arg));
    arg.exitScope();

    if((function.codomain().toString() != "void" && this.returnedValuesPerId.get(function.getVariable().getValue()).isEmpty())) {
      this.errorHandler.reportError(String.format("Function %s must have at least one return statement!", function.getVariable().getValue(), function));
    }
    this.lastTypeToReturn = null;

    return functionType;
  }

  @Override
  public NodeType visit(ParDecl parDecl, SymbolTable arg) {
    NodeType parDeclType = parDecl.getTypeDenoter().accept(this, arg);
    parDecl.getVariable().accept(this, arg);
    return parDeclType;
  }

  @Override
  public NodeType visit(VarDecl varDecl, SymbolTable arg) {
    NodeType varDeclType = varDecl.getTypeDenoter().accept(this, arg);
    varDecl.getVariable().accept(this, arg);
    if(varDecl.getVarInitValue() != null) {
      varDecl.getVarInitValue().accept(this, arg);
    }
    return varDeclType;
  }

  @Override
  public NodeType visit(VarInitValue varInitValue, SymbolTable arg) {
    NodeType varInitValueType = varInitValue.getExpr().accept(this, arg);
    return varInitValueType;
  }

  @Override
  public NodeType visit(PrimitiveTypeDenoter primitiveType, SymbolTable arg) {
    return primitiveType.typeFactory();
  }

  @Override
  public NodeType visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    return arrayType.typeFactory();
  }

  @Override
  public NodeType visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    return functionType.typeFactory();
  }

  @Override
  public NodeType visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    NodeType condIf = ifThenStatement.getExpr().accept(this, arg);
    if(!condIf.equals(PrimitiveNodeType.BOOL)) {
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.BOOL, condIf, ifThenStatement);
    }
    ifThenStatement.getStatements().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    NodeType condIf = ifThenElseStatement.getExpr().accept(this, arg);
    if(!condIf.equals(PrimitiveNodeType.BOOL)) {
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.BOOL, condIf, ifThenElseStatement);
    }
    ifThenElseStatement.getThenStatements().forEach(this.typeCheck(arg));
    ifThenElseStatement.getElseStatements().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(ForStatement forStatement, SymbolTable arg) {
    arg.enterScope();
    NodeType exprType = forStatement.getInitExpr().accept(this, arg);
    NodeType postCondType = forStatement.getPostConditionExpr().accept(this, arg);
    if(!exprType.equals(PrimitiveNodeType.INT) && !postCondType.equals(PrimitiveNodeType.INT)) {
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.INT, exprType, forStatement);
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.INT, postCondType, forStatement);
    }
    forStatement.getStatements().forEach(this.typeCheck(arg));
    arg.exitScope();
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(AssignStatement assignStatement, SymbolTable arg) {
    NodeType left = assignStatement.getId().accept(this, arg);
    NodeType right = assignStatement.getExpr().accept(this, arg);
    if(!left.equals(right))
      this.errorHandler.reportTypeMismatch(left, right, assignStatement);
    return right;
  }

  @Override
  public NodeType visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    CompositeNodeType input = new CompositeNodeType(new ArrayList<>());
    functionCallStatement.getFunctionParams().forEach(e -> input.addNodeType(e.accept(this, arg)));
    FunctionNodeType functionCallType = (FunctionNodeType) functionCallStatement.getId().accept(this, arg);
    if (!functionCallType.getParamsType().equals(input)) {
      this.errorHandler.reportTypeMismatch(functionCallType, input, functionCallStatement);
    }
    return functionCallType.getNodeType();
  }

  @Override
  public NodeType visit(ReadStatement readStatement, SymbolTable arg) {
    readStatement.getVars().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(WriteStatement writeStatement, SymbolTable arg) {
    writeStatement.getExprs().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(ReturnStatement returnStatement, SymbolTable arg) {
    NodeType returnType = returnStatement.getExpr().accept(this, arg);

    this.returnedValuesPerId.get(this.lastFunction).add(returnType);
    if(!returnType.equals(this.lastTypeToReturn)) {
      this.errorHandler.reportError(String.format("Invalid return type %s, expected %s", returnType, this.lastTypeToReturn), returnStatement);
    }

    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(LocalStatement localStatement, SymbolTable arg) {
    arg.enterScope();
    localStatement.getVarDecls().forEach(this.typeCheck(arg));
    localStatement.getStatements().forEach(this.typeCheck(arg));
    arg.exitScope();
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(NilConst nilConst, SymbolTable arg) {
    nilConst.setType(PrimitiveNodeType.NULL);
    return nilConst.getType();
  }

  @Override
  public NodeType visit(BooleanConst booleanConst, SymbolTable arg) {
    booleanConst.setType(PrimitiveNodeType.BOOL);
    return booleanConst.getType();
  }

  @Override
  public NodeType visit(IntegerConst integerConst, SymbolTable arg) {
    integerConst.setType(PrimitiveNodeType.INT);
    return integerConst.getType();
  }

  @Override
  public NodeType visit(FloatConst floatConst, SymbolTable arg) {
    floatConst.setType(PrimitiveNodeType.FLOAT);
    return floatConst.getType();
  }

  @Override
  public NodeType visit(StringConst stringConst, SymbolTable arg) {
    stringConst.setType(PrimitiveNodeType.STRING);
    return stringConst.getType();
  }

  @Override
  public NodeType visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    NodeType indexType = arrayElemAssignStatement.getArrayPointExpr().accept(this, arg);
    if(!indexType.equals(PrimitiveNodeType.INT)) {
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.INT, indexType, arrayElemAssignStatement);
    }

    ArrayNodeType arrayNodeType = (ArrayNodeType) arrayElemAssignStatement.getArrayExpr().accept(this, arg);
    if(!arrayNodeType.equals(arrayElemAssignStatement.getArrayExpr().getType())) {
      this.errorHandler.reportTypeMismatch(arrayNodeType, arrayElemAssignStatement.getArrayExpr().getType(), arrayElemAssignStatement);
    }

    NodeType assigneeType = arrayElemAssignStatement.getAssigneeExpr().accept(this, arg);
    if(!assigneeType.equals(arrayNodeType.getElementType())) {
      this.errorHandler.reportTypeMismatch(arrayNodeType.getElementType(), assigneeType, arrayElemAssignStatement);
    }
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(ArrayRead arrayRead, SymbolTable arg) {
    NodeType indexType = arrayRead.getArrayPointerExpr().accept(this, arg);
    if(!indexType.equals(PrimitiveNodeType.INT)) {
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.INT, indexType, arrayRead);
    }
    ArrayNodeType arrayNodeType = (ArrayNodeType) arrayRead.getArrayExpr().accept(this, arg);
    return arrayNodeType.getElementType();
  }

  @Override
  public NodeType visit(FunctionCall functionCall, SymbolTable arg) {
    CompositeNodeType input = new CompositeNodeType(new ArrayList<>());
    functionCall.getExprs().forEach(e -> input.addNodeType(e.accept(this, arg)));
    FunctionNodeType functionCallType = (FunctionNodeType) functionCall.getId().accept(this, arg);
    if (!functionCallType.getParamsType().equals(input)) {
      this.errorHandler.reportTypeMismatch(functionCallType, input, functionCall);
    }
    functionCall.setType(functionCallType);
    return functionCallType.getNodeType();
  }

  @Override
  public NodeType visit(MinusOp minusOp, SymbolTable arg) {
    NodeType lType = minusOp.getLeftOperand().accept(this, arg);
    NodeType rType = minusOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkSub((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, minusOp);
    minusOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(PlusOp plusOp, SymbolTable arg) {
    NodeType lType = plusOp.getLeftOperand().accept(this, arg);
    NodeType rType = plusOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkAdd((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, plusOp);
    plusOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(TimesOp timesOp, SymbolTable arg) {
    NodeType lType = timesOp.getLeftOperand().accept(this, arg);
    NodeType rType = timesOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkSub((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, timesOp);
    timesOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(DivOp divOp, SymbolTable arg) {
    NodeType lType = divOp.getLeftOperand().accept(this, arg);
    NodeType rType = divOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkSub((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, divOp);
    divOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(AndRelop andRelop, SymbolTable arg) {
    NodeType lType = andRelop.getLeftOperand().accept(this, arg);
    NodeType rType = andRelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, andRelop);
    andRelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    NodeType lType = greaterThanRelop.getLeftOperand().accept(this, arg);
    NodeType rType = greaterThanRelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, greaterThanRelop);
    greaterThanRelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(OrRelop orRelop, SymbolTable arg) {
    NodeType lType = orRelop.getLeftOperand().accept(this, arg);
    NodeType rType = orRelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, orRelop);
    orRelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    NodeType lType = greaterThanERelop.getLeftOperand().accept(this, arg);
    NodeType rType = greaterThanERelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, greaterThanERelop);
    greaterThanERelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    NodeType lType = lessThenRelop.getLeftOperand().accept(this, arg);
    NodeType rType = lessThenRelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, lessThenRelop);
    lessThenRelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    NodeType lType = lessThenERelop.getLeftOperand().accept(this, arg);
    NodeType rType = lessThenERelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, lessThenERelop);
    lessThenERelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(EqualsRelop equalsRelop, SymbolTable arg) {
    NodeType lType = equalsRelop.getLeftOperand().accept(this, arg);
    NodeType rType = equalsRelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, equalsRelop);
    equalsRelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    NodeType lType = notEqualsRelop.getLeftOperand().accept(this, arg);
    NodeType rType = notEqualsRelop.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NULL;
    result = lType.checkRel((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NULL))
      this.errorHandler.reportTypeMismatch(lType, result, notEqualsRelop);
    notEqualsRelop.setType(result);
    return result;
  }

  @Override
  public NodeType visit(UminusExpr uminusExpr, SymbolTable arg) {
    NodeType type = uminusExpr.getExpr().accept(this, arg);
    if(!type.equals(PrimitiveNodeType.INT) && !type.equals(PrimitiveNodeType.FLOAT))
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.FLOAT, type, uminusExpr);
    return type;
  }

  @Override
  public NodeType visit(NotExpr notExpr, SymbolTable arg) {
    NodeType type = notExpr.getExpr().accept(this, arg);
    if(!type.equals(PrimitiveNodeType.BOOL))
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.BOOL, type, notExpr);
    return type;
  }

  @Override
  public NodeType visit(SharpExpr sharpExpr, SymbolTable arg) {
    NodeType type = sharpExpr.getExpr().accept(this, arg);
    if(!type.equals(PrimitiveNodeType.STRING) && !(type instanceof ArrayNodeType)) {
      this.errorHandler.reportError(String.format("Type mismatch: Expected %s or %s but found %s", PrimitiveNodeType.STRING, "array", type), sharpExpr);
    }
    return PrimitiveNodeType.INT;
  }

  @Override
  public NodeType visit(Variable variable, SymbolTable arg) {
    return arg.lookup(variable.getValue()).get().getNodeType();
  }

  @Override
  public NodeType visit(Id id, SymbolTable arg) {
    NodeType iType = arg.lookup(id.getValue()).get().getNodeType();
    id.setType(iType);
    return iType;
  }

  @Override
  public NodeType visit(ArrayConst arrayConst, SymbolTable arg) {
    arrayConst.setType(arrayConst.getTypeDenoter().typeFactory());
    return arrayConst.getType();
  }

  @Override
  public NodeType visit(NopStatement nopStatement, SymbolTable arg) {
    return PrimitiveNodeType.NULL;
  }

  @Override
  public NodeType visit(WhileStatement whileStatement, SymbolTable arg) {
    NodeType condType = whileStatement.getExpr().accept(this, arg);
    if(!condType.equals(PrimitiveNodeType.BOOL))
      this.errorHandler.reportTypeMismatch(PrimitiveNodeType.BOOL, condType, whileStatement);
    whileStatement.getStatements().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NULL;
  }

}