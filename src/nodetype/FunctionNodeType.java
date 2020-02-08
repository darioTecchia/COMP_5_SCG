package nodetype;

import org.w3c.dom.Node;

import java.util.Objects;

public class FunctionNodeType implements NodeType {

  private CompositeNodeType paramsType;
  private NodeType nodeType;

  public FunctionNodeType(CompositeNodeType paramsType, NodeType nodeType) {
    this.paramsType = paramsType;
    this.nodeType = nodeType;
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

  public CompositeNodeType getParamsType() {
    return paramsType;
  }

  public NodeType getNodeType() {
    return nodeType;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.paramsType) + Objects.hashCode(this.nodeType);
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
      final FunctionNodeType other = (FunctionNodeType) obj;
      return Objects.equals(this.nodeType, other.nodeType) && Objects.equals(this.paramsType, other.paramsType);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(" + this.paramsType.toString() + ")");
    sb.append(" -> ");
    sb.append(this.nodeType.toString());
    return sb.toString();
  }
}
