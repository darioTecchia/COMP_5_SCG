package visitor;

import java.util.List;

import error.ErrorHandler;

import nodekind.NodeKind;
import nodetype.PrimitiveNodeType;
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
    if(nodes.isEmpty()){
      return true;
    }
    else {
      return nodes.stream().allMatch(node -> node.accept(this, arg));
    }
  }

  /**
   * Program visit
   * @param program
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(Program program, SymbolTable arg) {
    arg.enterScope();
    boolean isGlobalSafe = (program.getGlobal() != null) ? program.getGlobal().accept(this, arg) : true;
    boolean areFunctionsSafe = this.checkContext(program.getFunctions(), arg);
    boolean isProgramSafe = isGlobalSafe && areFunctionsSafe;
    if(!isProgramSafe) {
      this.errorHandler.reportError("Program Error", program);
    }
    arg.exitScope();
    return isProgramSafe;
  }

  /**
   * Global visit
   * @param global
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(Global global, SymbolTable arg) {
    boolean isGlobalSafe = this.checkContext(global.getVarDecls(), arg);
    if(!isGlobalSafe) {
      this.errorHandler.reportError("Global Error", global);
    }
    return isGlobalSafe;
  }

  /**
   * Function definition visit
   * @param function
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(Function function, SymbolTable arg) {
    arg.enterScope();
    boolean areParDeclsSafe = this.checkContext(function.getParDecls(), arg);
    boolean areStatementsSafe = this.checkContext(function.getStatements(), arg);
    boolean areTypeDenoterSafe = function.getTypeDenoter().accept(this, arg);
    boolean isFunctionSafe = areParDeclsSafe && areStatementsSafe && areTypeDenoterSafe;
    if(!isFunctionSafe) {
      this.errorHandler.reportError("Function Declaration error", function);
    }
    arg.exitScope();
    return isFunctionSafe;
  }

  /**
   * Function parameter in function declaration
   * @param parDecl
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ParDecl parDecl, SymbolTable arg) {
    boolean isVariableSafe = parDecl.getVariable().accept(this, arg);
    boolean isTypeDenoterSafe = parDecl.getTypeDenoter().accept(this, arg);
    boolean isParDeclSafe = isTypeDenoterSafe && isVariableSafe;
    if(!isParDeclSafe) {
      this.errorHandler.reportError("Parameter Declaration Error", parDecl);
    } else {
      arg.addEntry(parDecl.getVariable().getValue(), new SymbolTableRecord(parDecl.getVariable().getName(), parDecl.getTypeDenoter().typeFactory(), NodeKind.VARIABLE));
    }
    return isParDeclSafe;
  }

  /**
   * Variable declaration visit
   * @param varDecl
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(VarDecl varDecl, SymbolTable arg) {
    boolean isVariableSafe = varDecl.getVariable().accept(this, arg);
    boolean isTypeDenoter = varDecl.getTypeDenoter().accept(this, arg);
    boolean isVarInitValueSafe = (varDecl.getVarInitValue() != null) ? varDecl.getVarInitValue().accept(this, arg) : true;
    boolean isVarDeclSafe = isVariableSafe && isTypeDenoter && isVarInitValueSafe;
    if(!isVarDeclSafe) {
      this.errorHandler.reportError("Variable Declaration Error", varDecl);
    } else {
      arg.addEntry(varDecl.getVariable().getValue(), new SymbolTableRecord(varDecl.getVariable().getName(), varDecl.getTypeDenoter().typeFactory(), NodeKind.VARIABLE));
    }
    return isVarDeclSafe;
  }

  /**
   * Variable init value visit
   * @param varInitValue
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(VarInitValue varInitValue, SymbolTable arg) {
    boolean isVarInitValueSafe = varInitValue.getExpr().accept(this, arg);
    if(!isVarInitValueSafe) {
      this.errorHandler.reportError("Variable Initial Value", varInitValue);
    }
    return isVarInitValueSafe;
  }

  /**
   * Primitive type denoter: nil, int, bool, float, string
   * @param primitiveTypeDenoter
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(PrimitiveTypeDenoter primitiveTypeDenoter, SymbolTable arg) {
    return true;
  }

  /**
   * Array type denoter
   * @param arrayType
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ArrayTypeDenoter arrayType, SymbolTable arg) {
    return true;
  }

  /**
   * Function type denoter
   * @param functionType
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(FunctionTypeDenoter functionType, SymbolTable arg) {
    return true;
  }

  /**
   * If then statement
   * @param ifThenStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    boolean areStatementsSafe = this.checkContext(ifThenStatement.getStatements(), arg);
    boolean isIfThenStatementSafe = ifThenStatement.getExpr().accept(this, arg);
    boolean isIfSafe = areStatementsSafe && isIfThenStatementSafe;
    if(!isIfSafe) {
      this.errorHandler.reportError("If Error", ifThenStatement);
    }
    return isIfSafe;
  }

  /**
   * If then else statement
   * @param ifThenElseStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    boolean areStatementsSafe = this.checkContext(ifThenElseStatement.getThenStatements(), arg);
    boolean areElseStatementsSafe = this.checkContext(ifThenElseStatement.getElseStatements(), arg);
    boolean isIfThenElseStatementSafe = ifThenElseStatement.getExpr().accept(this, arg);
    boolean isIfElseSafe = areStatementsSafe && isIfThenElseStatementSafe && areElseStatementsSafe;
    if(!isIfElseSafe) {
      this.errorHandler.reportError("If Else Error", ifThenElseStatement);
    }
    return isIfElseSafe;
  }

  /**
   * For statement
   * @param forStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ForStatement forStatement, SymbolTable arg) {
    arg.enterScope();
    boolean isVariableSafe = forStatement.getVariable().accept(this, arg);
    if(isVariableSafe) {
      arg.addEntry(forStatement.getVariable().getValue(), new SymbolTableRecord(forStatement.getVariable().getName(), PrimitiveNodeType.INT, NodeKind.VARIABLE));
    }
    boolean isInitExprSafe = forStatement.getInitExpr().accept(this, arg);
    boolean isPostCondSafe = forStatement.getPostConditionExpr().accept(this, arg);
    boolean areStatementsSafe = this.checkContext(forStatement.getStatements(), arg);
    boolean isForSafe = isVariableSafe && isInitExprSafe && isPostCondSafe && areStatementsSafe;
    if(!isForSafe) {
      this.errorHandler.reportError("For Statement Error", forStatement);
    }
    arg.exitScope();
    return isForSafe;
  }

  /**
   * While statement
   * @param whileStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(WhileStatement whileStatement, SymbolTable arg) {
    boolean isExprSafe = whileStatement.getExpr().accept(this, arg);
    boolean areStatementsSafe = this.checkContext(whileStatement.getStatements(), arg);
    boolean isWhileSafe = isExprSafe && areStatementsSafe;
    if(!isWhileSafe)
      this.errorHandler.reportError("While Statement Error", whileStatement);
    return isWhileSafe;
  }

  /**
   * Assign statement
   * @param assignStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(AssignStatement assignStatement, SymbolTable arg) {
    boolean isLeftSafe = assignStatement.getId().accept(this, arg);
    boolean isRightSafe = assignStatement.getExpr().accept(this, arg);
    boolean isAssignSafe = isLeftSafe && isRightSafe;
    if(!isAssignSafe) {
      this.errorHandler.reportError("Assign Statement Error", assignStatement);
    }
    return isAssignSafe;
  }

  /**
   * Function call statement
   * @param functionCallStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(FunctionCallStatement functionCallStatement, SymbolTable arg) {
    boolean isFunCallSafe = functionCallStatement.getId().accept(this, arg);
    if(!isFunCallSafe){
      this.errorHandler.reportNotDefined(functionCallStatement);
    } else {
      boolean areParamsSafe = this.checkContext(functionCallStatement.getFunctionParams(), arg);
      isFunCallSafe = areParamsSafe;
      if(!isFunCallSafe)
        this.errorHandler.reportError("Function Call Error", functionCallStatement);
    }
    return isFunCallSafe;
  }

  /**
   * Read statement (scanf)
   * @param readStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ReadStatement readStatement, SymbolTable arg) {
    boolean areVarsSafe = this.checkContext(readStatement.getVars(), arg);
    if(!areVarsSafe)
      this.errorHandler.reportError("Read Statement Error", readStatement);
    return areVarsSafe;
  }

  /**
   * Write statement (printf)
   * @param writeStatements
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(WriteStatement writeStatements, SymbolTable arg) {
    boolean areExprsSafe = this.checkContext(writeStatements.getExprs(), arg);
    if(!areExprsSafe)
      this.errorHandler.reportError("Write Statement Error", writeStatements);
    return areExprsSafe;
  }

  /**
   * Return statement
   * @param returnStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ReturnStatement returnStatement, SymbolTable arg) {
    boolean isExprSafe = returnStatement.getExpr().accept(this, arg);
    if(!isExprSafe) {
      this.errorHandler.reportError("Return Statement Error", returnStatement);
    }
    return isExprSafe;
  }

  /**
   * Local statement
   * @param localStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(LocalStatement localStatement, SymbolTable arg) {
    arg.enterScope();
    boolean areVarDeclsSafe = this.checkContext(localStatement.getVarDecls(), arg);
    boolean areStatementsSafe = this.checkContext(localStatement.getStatements(), arg);
    boolean isLocalSafe = areVarDeclsSafe && areStatementsSafe;
    if (!isLocalSafe) {
      this.errorHandler.reportError("Local Error", localStatement);
    }
    arg.exitScope();
    return isLocalSafe;
  }

  /**
   * NIL const value
   * @param nilConst
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(NilConst nilConst, SymbolTable arg) {
    return true;
  }

  /**
   * Boolean const: true/false
   * @param booleanConst
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(BooleanConst booleanConst, SymbolTable arg) {
    return true;
  }

  /**
   * Integer const: 2
   * @param integerConst
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(IntegerConst integerConst, SymbolTable arg) {
    return true;
  }

  /**
   * Float const: 2.42
   * @param floatConst
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(FloatConst floatConst, SymbolTable arg) {
    return true;
  }

  /**
   * String const: "Hello, World!"
   * @param stringConst
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(StringConst stringConst, SymbolTable arg) {
    return true;
  }

  /**
   * Array elem assign: array[0] = 12
   * @param arrayElemAssignStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ArrayElemAssignStatement arrayElemAssignStatement, SymbolTable arg) {
    boolean isArrayExprSafe = arrayElemAssignStatement.getArrayExpr().accept(this, arg);
    boolean isArrayIndexSafe = arrayElemAssignStatement.getArrayPointExpr().accept(this, arg);
    boolean isAssegneeSafe = arrayElemAssignStatement.getAssigneeExpr().accept(this, arg);
    boolean isArrayElemAssignSafe = isArrayExprSafe && isArrayIndexSafe && isAssegneeSafe;
    if(!isArrayElemAssignSafe) {
      this.errorHandler.reportError("Array Element Assignment Error", arrayElemAssignStatement);
    }
    return isArrayElemAssignSafe;
  }

  /**
   * Array read: array[0]
   * @param arrayRead
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ArrayRead arrayRead, SymbolTable arg) {
    boolean isArrayExprSafe = arrayRead.getArrayExpr().accept(this, arg);
    boolean isArrayIndexSafe = arrayRead.getArrayPointerExpr().accept(this, arg);
    boolean isArrayReadSafe = isArrayExprSafe && isArrayIndexSafe;
    if(!isArrayReadSafe) {
      this.errorHandler.reportError("Array Read Error", arrayRead);
    }
    return isArrayReadSafe;
  }

  /**
   * Function call in expression
   * @param functionCall
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(FunctionCall functionCall, SymbolTable arg) {
    boolean isFunCallSafe = functionCall.getId().accept(this, arg);
    if(!isFunCallSafe){
      this.errorHandler.reportNotDefined(functionCall);
    } else {
      boolean areParamsSafe = this.checkContext(functionCall.getExprs(), arg);
      isFunCallSafe = areParamsSafe;
      if(!isFunCallSafe)
        this.errorHandler.reportError("Function Call Error", functionCall);
    }
    return isFunCallSafe;
  }

  /**
   * Minus operation: 2-2
   * @param minusOp
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(MinusOp minusOp, SymbolTable arg) {
    boolean isLeftSafe = minusOp.getLeftOperand().accept(this, arg);
    boolean isRightSafe = minusOp.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", minusOp);
    return isBinarySafe;
  }

  /**
   * Plus operation: 2+2
   * @param plusOp
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(PlusOp plusOp, SymbolTable arg) {
    boolean isLeftSafe = plusOp.getLeftOperand().accept(this, arg);
    boolean isRightSafe = plusOp.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", plusOp);
    return isBinarySafe;
  }

  /**
   * Times operation: 2*2
   * @param timesOp
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(TimesOp timesOp, SymbolTable arg) {
    boolean isLeftSafe = timesOp.getLeftOperand().accept(this, arg);
    boolean isRightSafe = timesOp.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", timesOp);
    return isBinarySafe;
  }

  /**
   * Divid operation: 2/2
   * @param divOp
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(DivOp divOp, SymbolTable arg) {
    boolean isLeftSafe = divOp.getLeftOperand().accept(this, arg);
    boolean isRightSafe = divOp.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", divOp);
    return isBinarySafe;
  }

  /**
   * AND rel operation: a and b
   * @param andRelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(AndRelop andRelop, SymbolTable arg) {
    boolean isLeftSafe = andRelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = andRelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", andRelop);
    return isBinarySafe;
  }

  /**
   * Greather than rel operation: a > b
   * @param greaterThanRelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(GreaterThanRelop greaterThanRelop, SymbolTable arg) {
    boolean isLeftSafe = greaterThanRelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = greaterThanRelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", greaterThanRelop);
    return isBinarySafe;
  }

  /**
   * OR rel operation: a or b
   * @param orRelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(OrRelop orRelop, SymbolTable arg) {
    boolean isLeftSafe = orRelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = orRelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", orRelop);
    return isBinarySafe;
  }

  /**
   * Greather than equals rel operation: a >= b
   * @param greaterThanERelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(GreaterThanERelop greaterThanERelop, SymbolTable arg) {
    boolean isLeftSafe = greaterThanERelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = greaterThanERelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", greaterThanERelop);
    return isBinarySafe;
  }

  /**
   * Less then rel operation: a < b
   * @param lessThenRelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(LessThenRelop lessThenRelop, SymbolTable arg) {
    boolean isLeftSafe = lessThenRelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = lessThenRelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", lessThenRelop);
    return isBinarySafe;
  }

  /**
   * Less then equals rel operation: a <= b
   * @param lessThenERelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(LessThenERelop lessThenERelop, SymbolTable arg) {
    boolean isLeftSafe = lessThenERelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = lessThenERelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", lessThenERelop);
    return isBinarySafe;
  }

  /**
   * Equals rel operation: a == b
   * @param equalsRelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(EqualsRelop equalsRelop, SymbolTable arg) {
    boolean isLeftSafe = equalsRelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = equalsRelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", equalsRelop);
    return isBinarySafe;
  }

  /**
   * Not equals rel operation: a != b
   * @param notEqualsRelop
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(NotEqualsRelop notEqualsRelop, SymbolTable arg) {
    boolean isLeftSafe = notEqualsRelop.getLeftOperand().accept(this, arg);
    boolean isRightSafe = notEqualsRelop.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.errorHandler.reportError("Binary Expression Error", notEqualsRelop);
    return isBinarySafe;
  }

  /**
   * Uminus expression: -1
   * @param uminusExpr
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(UminusExpr uminusExpr, SymbolTable arg) {
    boolean isExprSafe = uminusExpr.getExpr().accept(this, arg);
    if(!isExprSafe)
      this.errorHandler.reportError("UminusExpression Error", uminusExpr);
    return isExprSafe;
  }

  /**
   * Not expression: not true
   * @param notExpr
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(NotExpr notExpr, SymbolTable arg) {
    boolean isExprSafe = notExpr.getExpr().accept(this, arg);
    if(!isExprSafe)
      this.errorHandler.reportError("UminusExpression Error", notExpr);
    return isExprSafe;
  }

  /**
   * Sharp expression: #a, #{1, 2, 3}
   * @param sharpExpr
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(SharpExpr sharpExpr, SymbolTable arg) {
    boolean isExprSafe = sharpExpr.getExpr().accept(this, arg);
    if(!isExprSafe)
      this.errorHandler.reportError("UminusExpression Error", sharpExpr);
    return isExprSafe;
  }

  /**
   * ID
   * @param id
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(Id id, SymbolTable arg) {
    return arg.lookup(id.getValue()).isPresent();
  }

  /**
   * Variable
   * @param variable
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(Variable variable, SymbolTable arg) {
    return !arg.probe(variable.getValue());
  }

  /**
   * Array const
   * @param arrayConst
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(ArrayConst arrayConst, SymbolTable arg) {
    return true;
  }

  /**
   * NOP statement
   * @param nopStatement
   * @param arg
   * @return
   */
  @Override
  public Boolean visit(NopStatement nopStatement, SymbolTable arg) {
    return true;
  }

}