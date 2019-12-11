package syntax.expr;


import syntax.AstNode;
import syntax.Expr;

public abstract class UnaryOp extends Expr {

  public UnaryOp(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

}