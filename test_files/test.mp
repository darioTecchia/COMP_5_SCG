global
  array: {int} = {}: int;
  result: int
end

function main( ): nil
  executor(somma, array, array);
  nop
end

function somma(a: {int}, b: {int}): int
  return #a
end

function executor(ptr: ({int}, {int}) -> int, a: {int}, b: {int}): nil
  ptr(a, a) ==>
end