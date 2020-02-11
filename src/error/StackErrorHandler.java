package error;

import syntax.AstNode;

import java.util.Stack;

public class StackErrorHandler implements ErrorHandler {

  private final Stack<String> catchedErrors;

  public StackErrorHandler() {
    this.catchedErrors = new Stack<>();
  }

  @Override
  public void reportError(String msg) {
    StringBuilder errorBuilder = new StringBuilder();
    errorBuilder.append(msg);
    this.catchedErrors.push(errorBuilder.toString());
  }

  @Override
  public void reportError(String msg, AstNode node) {
    StringBuilder errorBuilder = new StringBuilder();
    errorBuilder.append(msg);
    errorBuilder.append(" at ");
    errorBuilder.append("(L:");
    errorBuilder.append(node.getLeftLocation().getLine());
    errorBuilder.append(",C:");
    errorBuilder.append(node.getRightLocation().getColumn());
    errorBuilder.append(")");
    this.catchedErrors.push(errorBuilder.toString());
  }

  @Override
  public void logErrors() {
    if (this.catchedErrors.empty()) {
      System.out.println("No errors present");
    } else {
      this.catchedErrors.forEach(System.out::println);
    }
  }

  @Override
  public boolean haveErrors() {
    return !this.catchedErrors.isEmpty();
  }

}
