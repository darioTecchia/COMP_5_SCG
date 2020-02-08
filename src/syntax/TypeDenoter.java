package syntax;


import nodetype.NodeType;

public abstract class TypeDenoter extends AstNode {

  public TypeDenoter(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

  public abstract NodeType typeFactory();
  
}