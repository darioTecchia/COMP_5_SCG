package syntax.statement;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.Expr;
import syntax.Statement;
import visitor.Visitor;

public class WhileStatement extends Statement {

  private Expr expr;
  private LinkedList<Statement> statements;

  public WhileStatement(Location leftLocation, Location rightLocation, Expr expr, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.expr = expr;
    this.statements = statements;
  }

  /**
   * @return the expr
   */
  public Expr getExpr() {
    return expr;
  }

  /**
   * @return the statements
   */
  public LinkedList<Statement> getStatements() {
    return statements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}