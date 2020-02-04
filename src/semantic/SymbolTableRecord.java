package semantic;

import nodekind.NodeKind;
import nodetype.NodeType;

/**
 * SymbolTableRecord
 */
public class SymbolTableRecord {

  private NodeType nodeType;
  private NodeKind nodeKind;
  private String lexeme;

  public SymbolTableRecord(String lexeme, NodeType nodeType, NodeKind nodeKind) {
    this.lexeme = lexeme;
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
    return String.format("Lexeme: %s, Type: %s, Kind: %s", this.lexeme, this.nodeType.toString(), this.nodeKind);
  }
}