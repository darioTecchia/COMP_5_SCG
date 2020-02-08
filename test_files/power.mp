global
    a : int = 0;
    b : int = 0;
    result: int
end

function main( ): nil
    a, b <== ;
    result = power( a, b );
    "risultato: ", result ==>
end

function power( x : int , y : int ): int
  local
    toReturn: int = 1;
    for i = 0, y do
      toReturn = toReturn * x
    end;
    return toReturn
  end
end