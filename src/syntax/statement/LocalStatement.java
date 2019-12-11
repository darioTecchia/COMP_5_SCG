package syntax.statement;

import java.util.LinkedList;


import visitor.Visitor;
import syntax.*;

public class LocalStatement extends Statement {

  private LinkedList<VarDecl> varDecls;
  private LinkedList<Statement> statements;

  public LocalStatement(int leftLocation, int rightLocation, LinkedList<VarDecl> varDecls, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.varDecls = varDecls;
    this.statements = statements;
  }

  /**
   * @return the statements
   */
  public LinkedList<Statement> getStatements() {
    return statements;
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