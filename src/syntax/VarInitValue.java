package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

import visitor.Visitor;

public class VarInitValue extends AstNode {

  private Expr expr;

  public VarInitValue(Location leftLocation, Location rightLocation, Expr expr) {
    super(leftLocation, rightLocation);
    this.expr = expr;
  }

  /**
   * @return the expr
   */
  public Expr getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }


}
