package syntax;


import visitor.Visitable;

public abstract class AstNode implements Visitable {

  private int leftLocation;
  private int rightLocation;

  public AstNode(int leftLocation, int rightLocation) {
    this.leftLocation = leftLocation;
    this.rightLocation = rightLocation;
  }

  public int getLeftLocation() {
    return leftLocation;
  }

  public int getRightLocation() {
    return rightLocation;
  }
}
