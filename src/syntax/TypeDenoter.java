package syntax;


import nodetype.PrimitiveNodeType;

public abstract class TypeDenoter extends AstNode {

  public TypeDenoter(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

  public abstract PrimitiveNodeType typeFactory();
  
}