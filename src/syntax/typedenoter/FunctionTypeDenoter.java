package syntax.typedenoter;

import java.util.ArrayList;
import java.util.LinkedList;

import nodetype.CompositeNodeType;
import nodetype.PrimitiveNodeType;
import syntax.TypeDenoter;
import visitor.Visitor;

public class FunctionTypeDenoter extends TypeDenoter {

  private LinkedList<TypeDenoter> typeDenoters;
  private TypeDenoter returnTypeDenoter;

  public FunctionTypeDenoter(int leftLocation, int rightLocation, LinkedList<TypeDenoter> typeDenoters, TypeDenoter returnTypeDenoter) {
    super(leftLocation, rightLocation);
    this.returnTypeDenoter = returnTypeDenoter;
    this.typeDenoters = typeDenoters;
  }

  public FunctionTypeDenoter(int leftLocation, int rightLocation, TypeDenoter returnTypeDenoter) {
    super(leftLocation, rightLocation);
    this.returnTypeDenoter = returnTypeDenoter;
    this.typeDenoters =  new LinkedList<>();
  }

  /**
   * @return the returnType
   */
  public TypeDenoter getReturnType() {
    return returnTypeDenoter;
  }

  /**
   * @return the types
   */
  public LinkedList<TypeDenoter> getTypes() {
    return typeDenoters;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  public CompositeNodeType domain() {
    CompositeNodeType compositeNodeType = new CompositeNodeType(new ArrayList<>());
    this.getTypes().forEach(typeDenoter -> compositeNodeType.addNodeType(typeDenoter.typeFactory()));
    return compositeNodeType;
  }

  public PrimitiveNodeType codomain() {
    return this.getReturnType().typeFactory();
  }

  @Override
  public PrimitiveNodeType typeFactory() {
    return null;
  }
}