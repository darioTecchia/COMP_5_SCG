package syntax.expr;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.Expr;
import syntax.Leaf;
import visitor.Visitor;

public class FloatConst extends Expr implements Leaf<Float> {

  private float value;

  public FloatConst(Location leftLocation, Location rightLocation, float value) {
    super(leftLocation, rightLocation);
    this.value = value;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public Float getValue() {
    return this.value;
  }

}