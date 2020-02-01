package nodetype;

import java.util.List;
import java.util.Objects;

public class CompositeNodeType implements NodeType {

  private final List<NodeType> types;

  public CompositeNodeType(List<NodeType> types) {
    this.types = types;
  }

  @Override
  public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
    return PrimitiveNodeType.NULL;
  }

  @Override
  public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
    return PrimitiveNodeType.NULL;
  }

  @Override
  public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
    return PrimitiveNodeType.NULL;
  }

  @Override
  public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
    return PrimitiveNodeType.NULL;
  }

  @Override
  public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
    return PrimitiveNodeType.NULL;
  }

  public void addNodeType(NodeType type){
    this.types.add(type);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.types);
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
      final CompositeNodeType other = (CompositeNodeType) obj;
      return !Objects.equals(this.types, other.types);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    this.types.forEach(t -> sb.append(t.toString()));
    return sb.toString();
  }



}
