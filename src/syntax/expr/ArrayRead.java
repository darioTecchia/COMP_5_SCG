package syntax.expr;


import syntax.Expr;
import visitor.Visitor;

public class ArrayRead extends Expr {

  private Expr arrayExpr;
  private Expr arrayPointerExpr;

  public ArrayRead(int leftLocation, int rightLocation, Expr arrayExpr, Expr arrayPointerExpr) {
    super(leftLocation, rightLocation);
    this.arrayExpr = arrayExpr;
    this.arrayPointerExpr = arrayPointerExpr;
  }

  /**
   * @return the arrayExpr
   */
  public Expr getArrayExpr() {
    return arrayExpr;
  }

  /**
   * @return the arrayPointerExpr
   */
  public Expr getArrayPointerExpr() {
    return arrayPointerExpr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }


  
}