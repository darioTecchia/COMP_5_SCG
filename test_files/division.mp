global
    a : int = 0;
    b : int = 0;
    result: float
end

function main( ): nil
    a, b <== ;
    result = division( a, b );
    "la divisione risulta: ", result ==>
end

function division( x : int , y : int ): float
  return x / y
end