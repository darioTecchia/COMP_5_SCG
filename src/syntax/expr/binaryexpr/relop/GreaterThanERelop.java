package syntax.expr.binaryexpr.relop;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.Expr;
import syntax.expr.BinaryOp;
import visitor.Visitor;

public class GreaterThanERelop extends BinaryOp {

  private Expr leftOperand;
  private Expr rightOperand;

  public GreaterThanERelop(Location leftLocation, Location rightLocation, Expr leftOperand, Expr rightOperand) {
    super(leftLocation, rightLocation);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  /**
   * @return the leftOperand
   */
  public Expr getLeftOperand() {
    return leftOperand;
  }

  /**
   * @return the rightOperand
   */
  public Expr getRightOperand() {
    return rightOperand;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}