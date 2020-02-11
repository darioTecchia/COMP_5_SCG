package syntax.statement;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;

import syntax.expr.Id;
import visitor.Visitor;
import syntax.*;

public class ReadStatement extends Statement {

  LinkedList<Id> vars;

  public ReadStatement(Location leftLocation, Location rightLocation, LinkedList<Id> vars) {
    super(leftLocation, rightLocation);
    this.vars = vars;
  }

  /**
   * @return the vars
   */
  public LinkedList<Id> getVars() {
    return vars;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}