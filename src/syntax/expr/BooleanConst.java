package syntax.expr;

import syntax.Expr;
import syntax.Leaf;
import visitor.Visitor;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class BooleanConst extends Expr implements Leaf<Boolean> {

  private boolean value;

  public BooleanConst(Location leftLocation, Location rightLocation, boolean value) {
    super(leftLocation, rightLocation);
    this.value = value;
  }

  @Override
  public Boolean getValue() {
    return this.value;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}