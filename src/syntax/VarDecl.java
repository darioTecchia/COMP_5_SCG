package syntax;


import syntax.expr.Id;
import visitor.Visitor;

public class VarDecl extends AstNode {

  private Id id;
  private TypeDenoter typeDenoter;
  private VarInitValue varInitValue;

  public VarDecl(int leftLocation, int rightLocation, Id id, TypeDenoter typeDenoter, VarInitValue varInitValue) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.typeDenoter = typeDenoter;
    this.varInitValue = varInitValue;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
  }

  /**
   * @return the typeDenoter
   */
  public TypeDenoter getTypeDenoter() {
    return typeDenoter;
  }

  /**
   * @return the varInitValue
   */
  public VarInitValue getVarInitValue() {
    return varInitValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
