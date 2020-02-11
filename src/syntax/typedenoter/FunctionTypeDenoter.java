package syntax.typedenoter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringJoiner;

import nodetype.CompositeNodeType;
import nodetype.FunctionNodeType;
import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
import org.w3c.dom.Node;
import syntax.TypeDenoter;
import visitor.Visitor;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class FunctionTypeDenoter extends TypeDenoter {

  private LinkedList<TypeDenoter> typeDenoters;
  private TypeDenoter returnTypeDenoter;

  public FunctionTypeDenoter(Location leftLocation, Location rightLocation, LinkedList<TypeDenoter> typeDenoters, TypeDenoter returnTypeDenoter) {
    super(leftLocation, rightLocation);
    this.returnTypeDenoter = returnTypeDenoter;
    this.typeDenoters = typeDenoters;
  }

  public FunctionTypeDenoter(Location leftLocation, Location rightLocation, TypeDenoter returnTypeDenoter) {
    super(leftLocation, rightLocation);
    this.returnTypeDenoter = returnTypeDenoter;
    this.typeDenoters =  new LinkedList<>();
  }

  /**
   * @return the returnType
   */
  public TypeDenoter getReturnType() {
    return returnTypeDenoter;
  }

  /**
   * @return the types
   */
  public LinkedList<TypeDenoter> getTypes() {
    return typeDenoters;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  public CompositeNodeType domain() {
    CompositeNodeType compositeNodeType = new CompositeNodeType(new ArrayList<>());
    this.getTypes().forEach(typeDenoter -> compositeNodeType.addNodeType(typeDenoter.typeFactory()));
    return compositeNodeType;
  }

  public NodeType codomain() {
    return this.getReturnType().typeFactory();
  }

  @Override
  public String cType() {
    StringJoiner joiner = new StringJoiner(", ");
    this.getTypes().stream().map((TypeDenoter t) -> t.typeFactory().toString()).forEach(joiner::add);
    return String.format("%s (*%s) (%s)", this.getReturnType().typeFactory(), "%s", joiner.toString());
  }

  @Override
  public NodeType typeFactory() {
    return new FunctionNodeType(domain(),  codomain());
  }


}