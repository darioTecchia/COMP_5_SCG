package syntax.expr;


import syntax.Expr;
import syntax.Leaf;
import visitor.Visitor;

public class StringConst extends Expr implements Leaf<String> {

  private String value;

  public StringConst(int leftLocation, int rightLocation, String value) {
    super(leftLocation, rightLocation);
    this.value = value;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public String getValue() {
    return this.value;
  }

}