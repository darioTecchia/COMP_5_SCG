package syntax.expr;


import syntax.Expr;
import syntax.Leaf;
import visitor.Visitor;

/**
 * NilConst
 */
public class NilConst extends Expr implements Leaf<Object> {

  public NilConst(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public Object getValue() {
    return null;
  }

}