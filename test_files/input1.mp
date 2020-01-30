global
    result : float = 0.0;
    buffer : float = 0.0
end

function main( ): nil
    buffer, result <== ;
    result = addInc( result+1, buffer );
    "la somma risulta: ", result ==>
end

function addInc( x : float , y : float ): float
    x = x +1;
    local
        pippo : float = 3.0 ;
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