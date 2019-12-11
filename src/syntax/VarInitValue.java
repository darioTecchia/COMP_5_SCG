package syntax;


import visitor.Visitor;

public class VarInitValue extends AstNode {

  private Expr expr;

  public VarInitValue(int leftLocation, int rightLocation, Expr expr) {
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
