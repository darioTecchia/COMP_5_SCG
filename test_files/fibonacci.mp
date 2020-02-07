global
    a : int = 0;
    result: int
end

function main( ): nil
    a <== ;
    result = fibonacci( a );
    "risultato: ", result ==>
end

function fibonacci( x : int): int
  if x < 0 then return -1 end;
  if x == 0 then return 0 end;
  if x == 1 then return 1 end;
  return fibonacci( x - 1 ) + fibonacci( x - 2)
end