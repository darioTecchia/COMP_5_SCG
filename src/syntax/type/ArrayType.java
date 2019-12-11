package syntax.type;


import syntax.Type;
import visitor.Visitor;

public class ArrayType extends Type {

  private Type type;

  public ArrayType(int leftLocation, int rightLocation, Type type) {
    super(leftLocation, rightLocation);
    this.type = type;
  }

  /**
   * @return the type
   */
  public Type getType() {
    return type;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
  
}