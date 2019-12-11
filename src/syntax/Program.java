package syntax;

import java.util.LinkedList;


import visitor.Visitor;

public class Program extends AstNode {

  private Global global;
  private LinkedList<Function> functions;

  public Program(int leftLocation, int rightLocation, Global global, LinkedList<Function> functions) {
    super(leftLocation, rightLocation);
    this.global = global;
    this.functions = functions;
  }

  /**
   * @return the global
   */
  public Global getGlobal() {
    return this.global;
  }

  /**
   * @return the functions
   */
  public LinkedList<Function> getFunctions() {
    return this.functions;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}