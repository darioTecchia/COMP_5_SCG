package syntax.typedenoter;

import nodetype.ArrayNodeType;
import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
import org.w3c.dom.Node;
import syntax.TypeDenoter;
import visitor.Visitor;

public class ArrayTypeDenoter extends TypeDenoter {

  private TypeDenoter typeDenoter;

  public ArrayTypeDenoter(int leftLocation, int rightLocation, TypeDenoter typeDenoter) {
    super(leftLocation, rightLocation);
    this.typeDenoter = typeDenoter;
  }

  /**
   * @return the type
   */
  public TypeDenoter getTypeDenoter() {
    return typeDenoter;
  }

  public NodeType getElementsType() {
    return this.typeDenoter.typeFactory();
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public NodeType typeFactory() {
    return new ArrayNodeType((PrimitiveNodeType) this.typeDenoter.typeFactory());
  }

  public String cType(){
    switch((PrimitiveNodeType) this.getElementsType()){
      case STRING:
        return "char *";
      case FLOAT:
        return "float";
      case BOOL:
        return "bool";
      case INT:
        return "int";
      default:
        return "";
    }
  }
}