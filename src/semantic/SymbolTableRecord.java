package semantic;

import nodekind.NodeKind;
import nodetype.NodeType;

/**
 * SymbolTableRecord
 */
public class SymbolTableRecord {

  private NodeType nodeType;
  private NodeKind nodeKind;

  public SymbolTableRecord(NodeType nodeType, NodeKind nodeKind) {
    this.nodeType = nodeType;
    this.nodeKind = nodeKind;
  }

  public NodeType getNodeType() {
    return nodeType;
  }
  public void setNodeType(NodeType nodeType) {
    this.nodeType = nodeType;
  }
  public NodeKind getKind() {
    return nodeKind;
  }

  public void setKind(NodeKind nodeKind) {
    this.nodeKind = nodeKind;
  }

  @Override
  public String toString() {
    return String.format("Type: %s, Kind: %s", this.nodeType.toString(), this.nodeKind);
  }

  
}