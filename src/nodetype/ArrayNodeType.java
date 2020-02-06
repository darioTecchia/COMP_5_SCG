package nodetype;

import java.util.Objects;

public class ArrayNodeType implements NodeType {

  private PrimitiveNodeType elementType;

  public ArrayNodeType(PrimitiveNodeType elementType) {
    this.elementType = elementType;
  }

  public PrimitiveNodeType getElementType() {
    return elementType;
  }

  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.elementType);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }else if (obj == null) {
      return false;
    } else if (getClass() != obj.getClass()) {
      return false;
    } else {
      final ArrayNodeType other = (ArrayNodeType) obj;
      return Objects.equals(this.elementType, other.elementType);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{" + this.elementType.toString() + "}");
    return sb.toString();
  }

  @Override
  public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
    return null;
  }

  @Override
  public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
    return null;
  }

  @Override
  public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
    return null;
  }

  @Override
  public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
    return null;
  }

  @Override
  public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
    return null;
  }
}
