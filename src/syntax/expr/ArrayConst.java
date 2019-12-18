package syntax.expr;


import syntax.*;
import visitor.Visitor;

public class ArrayConst extends Expr {

  private TypeDenoter typeDenoter;

  public ArrayConst(int leftLocation, int rightLocation, TypeDenoter typeDenoter) {
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