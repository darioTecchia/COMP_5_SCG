package nodetype;

/**
 * This is a singleton base class
 * @author didacus
 */
public interface NodeType {
  
  PrimitiveNodeType checkAdd(PrimitiveNodeType type);
  PrimitiveNodeType checkSub(PrimitiveNodeType type);
  PrimitiveNodeType checkMul(PrimitiveNodeType type);
  PrimitiveNodeType checkDiv(PrimitiveNodeType type);
  PrimitiveNodeType checkRel(PrimitiveNodeType type);
  
}
