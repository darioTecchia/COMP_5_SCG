package syntax;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;

import visitor.Visitor;

public class Global extends AstNode {

  private LinkedList<VarDecl> varDecls;

  public Global(Location leftLocation, Location rightLocation, LinkedList<VarDecl> varDecls) {
    super(leftLocation, rightLocation);
    this.varDecls = varDecls;
  }

  public Global(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
    this.varDecls = new LinkedList<VarDecl>();
  }

  /**
   * @return the varDecls
   */
  public LinkedList<VarDecl> getVarDecls() {
    return varDecls;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
