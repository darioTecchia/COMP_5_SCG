global
    array : {int} = {}: int;
    result: int
end

function main( ): nil
    result = conta( array );
    "risultato: ", result ==>
end

function conta( a: {int} ): int
  return #a
end