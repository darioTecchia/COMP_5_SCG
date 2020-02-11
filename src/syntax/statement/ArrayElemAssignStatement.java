package syntax.statement;

import java_cup.runtime.ComplexSymbolFactory.Location;

import visitor.Visitor;
import syntax.*;

public class ArrayElemAssignStatement extends Statement {

  private Expr arrayExpr;
  private Expr arrayPointExpr;
  private Expr assigneeExpr; 

  public ArrayElemAssignStatement(Location leftLocation, Location rightLocation, Expr arrayExpr, Expr arrayPointExpr, Expr assigneeExpr) {
    super(leftLocation, rightLocation);
    this.arrayExpr = arrayExpr;
    this.arrayPointExpr = arrayPointExpr;
    this.assigneeExpr = assigneeExpr;
  }

  /**
   * @return the arrayExpr
   */
  public Expr getArrayExpr() {
    return arrayExpr;
  }

  /**
   * @return the arrayPointExpr
   */
  public Expr getArrayPointExpr() {
    return arrayPointExpr;
  }

  /**
   * @return the assigneeExpr
   */
  public Expr getAssigneeExpr() {
    return assigneeExpr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}