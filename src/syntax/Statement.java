package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.AstNode;

public abstract class Statement extends AstNode {

  public Statement(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }
  
  
}