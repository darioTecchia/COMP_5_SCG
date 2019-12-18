package syntax.typedenoter;

import nodetype.PrimitiveNodeType;
import syntax.Leaf;
import syntax.TypeDenoter;
import visitor.Visitor;

public class PrimitiveTypeDenoter extends TypeDenoter implements Leaf<String> {

  private String kind;

  public PrimitiveTypeDenoter(int leftLocation, int rightLocation, String kind) {
    super(leftLocation, rightLocation);
    this.kind = kind;
  }

  public String getKind() {
    return kind;
  }

  public PrimitiveNodeType typeFactory(){
    switch(this.kind){
      case "int":
        return PrimitiveNodeType.INT;
      case "double":
        return PrimitiveNodeType.DOUBLE;
      case "string":
        return PrimitiveNodeType.STRING;
      case "bool":
        return PrimitiveNodeType.BOOL;
      default:
        return PrimitiveNodeType.NULL;
    }
  }

  @Override
  public String getValue() {
    return this.kind;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}