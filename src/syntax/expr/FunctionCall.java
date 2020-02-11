package syntax.expr;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.Expr;
import visitor.Visitor;

public class FunctionCall extends Expr {

  private Id id;
  private LinkedList<Expr> exprs;

  public FunctionCall(Location leftLocation, Location rightLocation, Id id, LinkedList<Expr> exprs) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.exprs = exprs;
  }

  public FunctionCall(Location leftLocation, Location rightLocation, Id id) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.exprs = new LinkedList<>();
  }

  /**
   * @return the exprs
   */
  public LinkedList<Expr> getExprs() {
    return exprs;
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