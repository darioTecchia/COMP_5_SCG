package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

import visitor.Visitor;

public class Variable extends AstNode implements Leaf<String> {

  private String name;

  public Variable(Location leftLocation, Location rightLocation, String name) {
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
