package syntax.expr;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.*;
import visitor.Visitor;

public class ArrayConst extends Expr {

  private TypeDenoter typeDenoter;

  public ArrayConst(Location leftLocation, Location rightLocation, TypeDenoter typeDenoter) {
    super(leftLocation, rightLocation);
    this.typeDenoter = typeDenoter;
  }

  /**
   * @return the type
   */
  public TypeDenoter getTypeDenoter() {
    return typeDenoter;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}