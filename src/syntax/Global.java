package syntax;

import java.util.LinkedList;


import visitor.Visitor;

public class Global extends AstNode {

  private LinkedList<VarDecl> varDecls;

  public Global(int leftLocation, int rightLocation, LinkedList<VarDecl> varDecls) {
    super(leftLocation, rightLocation);
    this.varDecls = varDecls;
  }

  public Global(int leftLocation, int rightLocation) {
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
