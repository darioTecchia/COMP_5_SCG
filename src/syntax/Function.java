package syntax;

import java.util.ArrayList;
import java.util.LinkedList;


import nodetype.ArrayNodeType;
import nodetype.CompositeNodeType;
import nodetype.PrimitiveNodeType;
import syntax.typedenoter.ArrayTypeDenoter;
import visitor.Visitor;

public class Function extends AstNode {

  private Variable variable;
  private LinkedList<ParDecl> parDecls;
  private TypeDenoter typeDenoter;
  private LinkedList<Statement> statements;

  /**
   * Function definition with params
   */
  public Function(int leftLocation, int rightLocation, Variable variable, LinkedList<ParDecl> parDecls, TypeDenoter typeDenoter, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.variable = variable;
    this.parDecls = parDecls;
    this.typeDenoter = typeDenoter;
    this.statements = statements;
  }

  /**
   * Function definition without params
   */
  public Function(int leftLocation, int rightLocation, Variable variable, TypeDenoter typeDenoter, LinkedList<Statement> statements) {
    super(leftLocation, rightLocation);
    this.variable = variable;
    this.parDecls = new LinkedList<>();
    this.typeDenoter = typeDenoter;
    this.statements = statements;
  }

  /**
   * @return the id
   */
  public Variable getVariable() {
    return variable;
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

  public CompositeNodeType domain() {
    CompositeNodeType compositeNodeType = new CompositeNodeType(new ArrayList<>());
    this.getParDecls().forEach(parDecl -> {
      if(parDecl.getTypeDenoter() instanceof ArrayTypeDenoter) {
        System.out.println(parDecl.getVariable());
        ArrayNodeType arrayNodeType = new ArrayNodeType(parDecl.getTypeDenoter().typeFactory());
        compositeNodeType.addNodeType(arrayNodeType);
      } else {
        compositeNodeType.addNodeType(parDecl.getTypeDenoter().typeFactory());
      }
    });
    return compositeNodeType;
  }

  public PrimitiveNodeType codomain() {
    return this.getTypeDenoter().typeFactory();
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
