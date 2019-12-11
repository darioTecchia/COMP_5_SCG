package syntax.statement;

import java.util.LinkedList;


import visitor.Visitor;
import syntax.*;

public class IfThenElseStatement extends Statement {

  private Expr expr;
  private LinkedList<Statement> thenStatements;
  private LinkedList<Statement> elseStatements;

  public IfThenElseStatement(int leftLocation, int rightLocation, Expr expr, LinkedList<Statement> thenStatements, LinkedList<Statement> elseStatements) {
    super(leftLocation, rightLocation);
    this.expr = expr;
    this.thenStatements = thenStatements;
    this.elseStatements = elseStatements;
  }

  /**
   * @return the expr
   */
  public Expr getExpr() {
    return expr;
  }

  /**
   * @return the elseStatements
   */
  public LinkedList<Statement> getElseStatements() {
    return elseStatements;
  }

  /**
   * @return the thenStatements
   */
  public LinkedList<Statement> getThenStatements() {
    return thenStatements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}