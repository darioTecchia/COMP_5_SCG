global

  result: int
end

function main( ): nil
  executor(sott, 1, 2);
  executor(somma, 1, 2);
  nop
end

function somma(a: int, b: int): int
  return a + b
end

function sott(a: int, b: int): int
  return a - b
end

function executor(ptr: (int, int) -> int, a: int, b: int): nil
  ptr(a, b) ==>
end