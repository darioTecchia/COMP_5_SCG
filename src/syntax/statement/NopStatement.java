package syntax.statement;

import syntax.Statement;
import visitor.Visitor;

public class NopStatement extends Statement {

  public NopStatement(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}