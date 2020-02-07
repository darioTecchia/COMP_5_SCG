global
    a : int = 0;
    b : int = 0;
    result: int
end

function main( ): nil
    a, b <== ;
    result = mul( a, b );
    "la moltiplicazione risulta: ", result ==>
end

function mul( x : int , y : int ): int
  local
    toReturn: int = 0;
    for i = 0, y do
      toReturn = toReturn + x
    end;
    return toReturn
  end
end