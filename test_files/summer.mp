global
    a : int = 0;
    b : int = 0;
    result: int
end

function main( ): nil
    a, b <== ;
    result = add( a, b );
    "la somma risulta: ", result ==>
end

function add( x : int , y : int ): int
  return x + y
end