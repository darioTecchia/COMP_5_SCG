package syntax.expr;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.AstNode;
import syntax.Expr;

public abstract class BinaryOp extends Expr {

  public BinaryOp(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }

}