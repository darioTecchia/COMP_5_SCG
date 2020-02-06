# MyPallene

Programming language definited for the Compiler course from University.

## Full Project
JFlex and JavaCup integration for MyPallene language implementation.

## Lexical Specification

| Token | Lexeme |
|-------|--------|
| FUNCTION | "function" |
| MAIN | "main" |
| LPAR | "(" |
| RPAR | ")" |
| END | "end" |
| ID | Java id format |
| COLON | ":" |
| GLOBAL | {"global"} |
| SEMI | ";" |
| COMMA | "," |
| NIL | "nil" |
| INT | "int" |
| BOOL | "bool" |
| FLOAT | "float" |
| STRING | "string" |
| BLPAR | "{" |
| BRPAR | "}" |
| ARROW | "->" |
| ASSIGN | "=" |
| NOP | "nop" |
| WHILE | "while" |
| DO | "do" |
| IF | "if" |
| THEN | "then" |
| ELSE | "else" |
| FOR | "for" |
| LOCAL | "local" |
| SLPAR | "[" |
| SRPAR | "]" |
| READ | "<==" |
| WRITE | "==>" |
| RETURN | "return" |
| TRUE | "true" |
| FALSE | "false" |
| INT_CONST | Java Decimal integer format |
| FLOAT_CONST | Java float format |
| STRING_CONST | Java format constant format |
| PLUS | "+" |
| MINUS | "-" |
| TIMES | "*" |
| DIV | "/" |
| AND | "and" |
| OR | "or" |
| GT | ">" | 
| GE | ">=" |
| LT | "<" |
| LE | "<=" |
| EQ | "==" |
| NE | "!=" |
| NOT | "not" |
| SHARP | "#" |

## Grammar Specification

```cup
Program ::= Global Functions
;

Global ::= GLOBAL Var_decls END
        | /* empty */
;

Functions ::= Def_fun Functions
            | Def_fun
;

Def_fun ::= FUNCTION ID LPAR Par_decls RPAR COLON TypeDenoter Statements END
          | FUNCTION ID LPAR RPAR COLON TypeDenoter Statements END
;

Par_decls ::= ID COLON TypeDenoter COMMA Par_decls
            | ID COLON TypeDenoter
;

Var_decls ::= Var_decls SEMI ID COLON TypeDenoter Var_init_value  
            | ID COLON TypeDenoter Var_init_value
;

Var_init_value ::= ASSIGN Expr
                | /* empty */
;

TypeDenoter ::= NIL | INT | BOOL | FLOAT | STRING
      | BLPAR TypeDenoter BRPAR
      | LPAR Types RPAR ARROW TypeDenoter
      | LPAR RPAR ARROW TypeDenoter
;

Types ::= TypeDenoter COMMA Types
        | TypeDenoter
;

Statements ::= Stat SEMI Statements
            | Stat
;

Stat ::= NOP
      | WHILE Expr DO Statements END
      | IF Expr THEN Statements END
      | IF Expr THEN Statements ELSE Statements END
      | FOR ID ASSIGN Expr COMMA Expr DO Statements END
      | LOCAL Var_decls SEMI Statements END
      | ID ASSIGN Expr
      | Expr SLPAR Expr SRPAR ASSIGN Expr
      | ID LPAR Exprs RPAR
      | ID LPAR RPAR
      | Vars READ
      | Exprs WRITE
      | RETURN Expr
      | error
;

Vars ::= ID COMMA Vars
      | ID
;

Exprs ::= Expr COMMA Exprs
        | Expr
;

Expr ::=  NIL 
      | TRUE 
      | FALSE 
      | INT_CONST 
      | FLOAT_CONST 
      | STRING_CONST 
      | BLPAR BRPAR COLON TypeDenoter 
      | ID 
      | Expr SLPAR Expr SRPAR 
      | ID LPAR Exprs RPAR
      | ID LPAR RPAR
      | Expr PLUS Expr 
      | Expr MINUS Expr 
      | Expr TIMES Expr 
      | Expr DIV Expr 
      | Expr AND Expr 
      | Expr OR Expr 
      | Expr GT Expr 
      | Expr GE Expr 
      | Expr LT Expr 
      | Expr LE Expr 
      | Expr EQ Expr 
      | Expr NE Expr 
      | MINUS Expr 
      | NOT Expr 
      | SHARP Expr
        
;
```

## Notes
```
java -jar C:\CUP\java-cup-11b.jar -dump -progress -expect 5 -destdir .\src\dist\ .\srcjflexcup\Parser.cup 2>.\out.txt

..\jflex-1.7.0\bin\jflex -d .\src\dist\ srcjflexcup\Lexer.flex
```
