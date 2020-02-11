package syntax.expr;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.AstNode;
import syntax.Expr;

public abstract class UnaryOp extends Expr {

  public UnaryOp(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }

}