package syntax.expr;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.Expr;
import syntax.Leaf;
import visitor.Visitor;

public class Id extends Expr implements Leaf<String> {

  private String name;

  public Id(Location leftLocation, Location rightLocation, String name) {
    super(leftLocation, rightLocation);
    this.name = name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  @Override
  public String getValue() {
    return this.name;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
