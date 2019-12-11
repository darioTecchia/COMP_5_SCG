package syntax.type;


import syntax.Type;
import visitor.Visitor;

public class PrimitiveType extends Type {

  private String kind;

  public PrimitiveType(int leftLocation, int rightLocation, String kind) {
    super(leftLocation, rightLocation);
    this.kind = kind;
  }

  /**
   * @return the kind
   */
  public String getKind() {
    return kind;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}