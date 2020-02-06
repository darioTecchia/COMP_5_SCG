global
    result : float = 0.0;
    buffer : float = 0.0;
    array : {float} = {}: float
end

function main( ): nil
    buffer = array[0];
    array[0] = buffer;
    buffer, result <== ;
    result = addInc( result + 1, buffer );
    "la somma risulta: ", result ==>
end

function addInc( x : float , y : float ): float

    x = x + 1;

    for i = 1, 10 do
        i ==>
    end;

    while true do
        nop
    end;

    if true then
        nop
    end;

    if true then
        nop
    else
      nop
     end;

    local
        pippo : float = 3.0 ;
        i: int = 1;
        pippo = 1.0
    end;

    local
        i : float = 2.0;
        pluto : string = "ciao";
        i = i + 1 ;

        local
            i : float = 1.0;
            paperino : float;
            y = y * i
        end;

        buffer ==>;

        return x +y + i
    end
end