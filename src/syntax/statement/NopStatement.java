package syntax.statement;

import syntax.Statement;
import visitor.Visitor;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class NopStatement extends Statement {

  public NopStatement(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}