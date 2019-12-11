package syntax.statement;


import syntax.expr.Id;
import visitor.Visitor;

import java.util.LinkedList;

import syntax.*;

public class ForStatement extends Statement {

  private Id id;
  private Expr initExpr;
  private Expr postConditionExpr;
  private LinkedList<Statement> statements;

  public ForStatement(int leftLocation, int rightLocation, Id id, Expr initExpr, Expr postConditionExpr, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.initExpr = initExpr;
    this.postConditionExpr = postConditionExpr;
    this.statements = statements;
  }

  /**
   * @return the statements
   */
  public LinkedList<Statement> getStatements() {
    return statements;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
  }

  /**
   * @return the initExpr
   */
  public Expr getInitExpr() {
    return initExpr;
  }

  /**
   * @return the postConditionExpr
   */
  public Expr getPostConditionExpr() {
    return postConditionExpr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }


  
}