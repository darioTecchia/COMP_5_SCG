package syntax.statement;


import visitor.Visitor;
import syntax.*;
import syntax.expr.*;

public class AssignStatement extends Statement {

  private Id id;
  private Expr expr;

  public AssignStatement(int leftLocation, int rightLocation, Id id, Expr expr) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.expr = expr;
  }

  /**
   * @return the expr
   */
  public Expr getExpr() {
    return expr;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}