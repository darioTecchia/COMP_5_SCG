package syntax;



public abstract class Expr extends AstNode {

  public Expr(int leftLocation, int rightLocation) {
    super(leftLocation, rightLocation);
  }

}
