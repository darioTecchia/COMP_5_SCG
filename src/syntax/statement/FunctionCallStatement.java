package syntax.statement;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;

import nodetype.FunctionNodeType;
import nodetype.NodeType;
import syntax.expr.Id;
import visitor.Visitor;
import syntax.*;

public class FunctionCallStatement extends Statement {

  private Id id;
  private LinkedList<Expr> functionParams;

  public FunctionCallStatement(Location leftLocation, Location rightLocation, Id id, LinkedList<Expr> functionParams) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.functionParams = functionParams;
  }

  public FunctionCallStatement(Location leftLocation, Location rightLocation, Id id) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.functionParams = new LinkedList<>();
  }

  /**
   * @return the functionParams
   */
  public LinkedList<Expr> getFunctionParams() {
    return functionParams;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}