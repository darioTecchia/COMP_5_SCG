package syntax.statement;

import java_cup.runtime.ComplexSymbolFactory.Location;

import visitor.Visitor;

import java.util.LinkedList;

import syntax.*;

public class IfThenStatement extends Statement {

  private Expr expr;
  private LinkedList<Statement> statements;

  public IfThenStatement(Location leftLocation, Location rightLocation, Expr expr, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.expr = expr;
    this.statements = statements;
  }

  /**
   * @return the statements
   */
  public LinkedList<Statement> getStatements() {
    return statements;
  }

  /**
   * @return the expr
   */
  public Expr getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}