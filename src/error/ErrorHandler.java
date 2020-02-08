package error;

import syntax.AstNode;

import nodetype.NodeType;

public interface ErrorHandler {

  static final String TYPE_MISMATCH = "Type mismatch: Expected %s but found %s";
  static final String NOT_DEFINED = "Variable not defined";
  static final String YET_DEFINED = "Variable yet defined";

  void reportError(String msg, AstNode node);

  void reportError(String msg);

  void logErrors();

  default void reportTypeMismatch(NodeType expected, NodeType actual, AstNode node) {
    String msg = String.format(TYPE_MISMATCH, expected, actual);
    this.reportError(msg, node);
  }

  default void reportNotDefined(AstNode node) {
    this.reportError(NOT_DEFINED, node);
  }

  default void reportYetDefined(AstNode node) {
    this.reportError(YET_DEFINED, node);
  }

  boolean haveErrors();
}
