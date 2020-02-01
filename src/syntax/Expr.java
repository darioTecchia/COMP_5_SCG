package syntax;


import nodetype.NodeType;

public abstract class Expr extends AstNode {

  NodeType type;

  public Expr(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

  public void setType(NodeType type) {
    this.type = type;
  }

  public NodeType getType() {
    return type;
  }
}
