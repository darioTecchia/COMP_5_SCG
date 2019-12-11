package syntax.statement;

import java.util.LinkedList;


import syntax.expr.Id;
import visitor.Visitor;
import syntax.*;

public class FunctionCallStatement extends Statement {

  private Id id;
  private LinkedList<Expr> functionParams;

  public FunctionCallStatement(int leftLocation, int rightLocation, Id id, LinkedList<Expr> functionParams) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.functionParams = functionParams;
  }

  /**
   * @return the functionParams
   */
  public LinkedList<Expr> getFunctionParams() {
    return functionParams;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }


}