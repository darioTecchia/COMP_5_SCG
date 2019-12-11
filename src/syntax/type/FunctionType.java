package syntax.type;

import java.util.LinkedList;


import syntax.Type;
import visitor.Visitor;

public class FunctionType extends Type {

  private LinkedList<Type> types;
  private Type returnType;

  public FunctionType(int leftLocation, int rightLocation, LinkedList<Type> types, Type returnType) {
    super(leftLocation, rightLocation);
    this.returnType = returnType;
    this.types = types;
  }

  public FunctionType(int leftLocation, int rightLocation, Type returnType) {
    super(leftLocation, rightLocation);
    this.returnType = returnType;
    this.types =  new LinkedList<>();
  }

  /**
   * @return the returnType
   */
  public Type getReturnType() {
    return returnType;
  }

  /**
   * @return the types
   */
  public LinkedList<Type> getTypes() {
    return types;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}