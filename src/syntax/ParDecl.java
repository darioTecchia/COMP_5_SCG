package syntax;


import syntax.expr.Id;
import visitor.Visitor;

public class ParDecl extends AstNode {

  private Id id;
  private TypeDenoter typeDenoter;

  public ParDecl(int leftLocation, int rightLocation, Id id, TypeDenoter type) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.typeDenoter = typeDenoter;
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
  public TypeDenoter getTypeDenoter() {
    return typeDenoter;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
