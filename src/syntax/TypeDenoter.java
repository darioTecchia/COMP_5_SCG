package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

import nodetype.NodeType;

public abstract class TypeDenoter extends AstNode {

  public TypeDenoter(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }

  public abstract String cType();

  public abstract NodeType typeFactory();
  
}