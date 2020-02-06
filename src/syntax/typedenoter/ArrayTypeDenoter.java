package syntax.typedenoter;

import nodetype.PrimitiveNodeType;
import syntax.TypeDenoter;
import visitor.Visitor;

public class ArrayTypeDenoter extends TypeDenoter {

  private TypeDenoter typeDenoter;

  public ArrayTypeDenoter(int leftLocation, int rightLocation, TypeDenoter typeDenoter) {
    super(leftLocation, rightLocation);
    this.typeDenoter = typeDenoter;
  }

  /**
   * @return the type
   */
  public TypeDenoter getTypeDenoter() {
    return typeDenoter;
  }

  public PrimitiveNodeType getElementsType() {
    return this.typeDenoter.typeFactory();
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public PrimitiveNodeType typeFactory() {
    return null;
  }
}