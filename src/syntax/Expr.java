package syntax;


import java_cup.runtime.ComplexSymbolFactory.Location;
import nodetype.NodeType;

public abstract class Expr extends AstNode {

  NodeType type;

  public Expr(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }

  public void setType(NodeType type) {
    this.type = type;
  }

  public NodeType getType() {
    return type;
  }
}
