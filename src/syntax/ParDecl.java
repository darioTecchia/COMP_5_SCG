package syntax;


import syntax.expr.Id;
import visitor.Visitor;

public class ParDecl extends AstNode {

  private Id id;
  private Type type;

  public ParDecl(int leftLocation, int rightLocation, Id id, Type type) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.type = type;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
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
