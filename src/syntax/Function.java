package syntax;

import java.util.LinkedList;


import nodetype.PrimitiveNodeType;
import syntax.expr.Id;
import visitor.Visitor;

public class Function extends AstNode {

  private Id id;
  private LinkedList<ParDecl> parDecls;
  private TypeDenoter typeDenoter;
  private LinkedList<Statement> statements;

  /**
   * Function definition with params
   */
  public Function(int leftLocation, int rightLocation, Id id, LinkedList<ParDecl> parDecls, TypeDenoter typeDenoter, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.parDecls = parDecls;
    this.typeDenoter = typeDenoter;
    this.statements = statements;
  }

  /**
   * Function definition without params
   */
  public Function(int leftLocation, int rightLocation, Id id, TypeDenoter typeDenoter, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.id = id;
    this.parDecls = new LinkedList<>();
    this.typeDenoter = typeDenoter;
    this.statements = statements;
  }

  /**
   * @return the id
   */
  public Id getId() {
    return id;
  }

  /**
   * @return the parDecls
   */
  public LinkedList<ParDecl> getParDecls() {
    return parDecls;
  }

  /**
   * @return the statements
   */
  public LinkedList<Statement> getStatements() {
    return statements;
  }

  /**
   * @return the type
   */
  public TypeDenoter getTypeDenoter() {
    return typeDenoter;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
