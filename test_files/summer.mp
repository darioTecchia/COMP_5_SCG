global
    a : int = 0;
    b : int = 0;
    result: int
end

function main( ): nil
    a, b <== ;
    result = addInc( a, b );
    "la somma risulta: ", result ==>
end

function addInc( x : int , y : int ): int
  return x + y
end