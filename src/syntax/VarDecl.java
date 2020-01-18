package syntax;


import syntax.expr.Id;
import visitor.Visitor;

public class VarDecl extends AstNode {

  private Variable variable;
  private TypeDenoter typeDenoter;
  private VarInitValue varInitValue;

  public VarDecl(int leftLocation, int rightLocation, Variable variable, TypeDenoter typeDenoter, VarInitValue varInitValue) {
    super(leftLocation, rightLocation);
    this.variable = variable;
    this.typeDenoter = typeDenoter;
    this.varInitValue = varInitValue;
  }

  /**
   * @return the id
   */
  public Variable getVariable() {
    return variable;
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
