package syntax.expr;


import syntax.AstNode;
import syntax.Expr;

public abstract class BinaryOp extends Expr {

  public BinaryOp(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

}