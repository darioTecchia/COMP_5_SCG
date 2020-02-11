package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.expr.Id;
import visitor.Visitor;

public class ParDecl extends AstNode {

  private Variable variable;
  private TypeDenoter typeDenoter;

  public ParDecl(Location leftLocation, Location rightLocation, Variable variable, TypeDenoter typeDenoter) {
    super(leftLocation, rightLocation);
    this.variable = variable;
    this.typeDenoter = typeDenoter;
  }

  /**
   * @return the id
   */
  public Variable getVariable() {
    return variable;
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
