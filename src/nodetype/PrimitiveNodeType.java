package nodetype;

public enum PrimitiveNodeType implements NodeType {

  BOOL {
    @Override
    public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
      switch(type){
        case BOOL:
          return INT;
        case FLOAT:
          return FLOAT;
        case INT:
          return INT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
      switch(type){
        case BOOL:
          return INT;
        case FLOAT:
          return FLOAT;
        case INT:
          return INT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
      switch(type){
        case BOOL: case INT:
          return INT;
        case FLOAT:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
      switch(type){
        case BOOL: case INT:
          return INT;
        case FLOAT:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
      switch(type){
        case INT: case FLOAT: case BOOL:
          return BOOL;
        default:
          return NULL;
      }
    }
  },

  FLOAT {
    @Override
    public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
      switch(type){
        case INT: case FLOAT: case BOOL:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
      switch(type){
        case INT: case FLOAT: case BOOL:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
      switch(type){
        case INT: case FLOAT: case BOOL:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
      switch(type) {
        case INT: case FLOAT: case BOOL:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
      switch(type){
        case INT: case BOOL: case FLOAT:
          return BOOL;
        default:
          return NULL;
      }
    }

  },
  INT {
    @Override
    public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
      switch(type) {
        case INT: case BOOL:
          return INT;
        case FLOAT:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
      switch(type) {
        case INT: case BOOL:
          return INT;
        case FLOAT:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
      switch(type){
        case INT: case BOOL:
          return INT;
        case FLOAT:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
      switch(type){
        case INT: case BOOL:
          return INT;
        case FLOAT:
          return FLOAT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
      switch(type) {
        case INT: case FLOAT: case BOOL:
          return BOOL;
        default:
          return NULL;
      }
    }

  },
  NULL {
    @Override
    public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
      return NULL;
    }

  },
  STRING {
    @Override
    public PrimitiveNodeType checkAdd(PrimitiveNodeType type) {
      switch(type){
        case NULL:
          return NULL;
        default:
          return STRING;
      }
    }

    @Override
    public PrimitiveNodeType checkSub(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkMul(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkDiv(PrimitiveNodeType type) {
      return NULL;
    }

    @Override
    public PrimitiveNodeType checkRel(PrimitiveNodeType type) {
      return NULL;
    }
  };

  public String cType(){
    switch(this){
      case STRING:
        return "char *";
      case FLOAT:
        return "double";
      case BOOL:
        return "bool";
      case INT:
        return "int";
      default:
        return "";
    }
  }

  @Override
  public String toString() {
    switch(this){
      case BOOL:
        return "bool";
      case INT:
        return "int";
      case FLOAT:
        return "double";
      case STRING:
        return "string";
      default:
        return "undefined";
    }
  }
}
