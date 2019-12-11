/* JFlex example: part of Java language lexer specification */
package dist;


import java_cup.runtime.Symbol;
/**
* This class is a simple example lexer.
*/


%%

%class Lexer
%cupsym ParserSym

%cup

%unicode

%public

%line
%column

%{
  StringBuffer string = new StringBuffer();

  private Symbol generateTokenSym(int type) {
    return new Symbol(type);
  }
  private Symbol generateTokenSym(int type, Object value) {
    return new Symbol(type, value);
  }

  // prepara file input per lettura e controlla errori
  public boolean initialize(String filePath) {
    try {
      this.zzReader = new java.io.FileReader(filePath);
      return true;
    } catch (java.io.FileNotFoundException e) {
      return false;
    }
  }

  public Lexer() { }
%}

%eofval{
	return generateTokenSym(ParserSym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

IntegerLiteral = 0 | [1-9][0-9]*

FloatLiteral = (0|[1-9][0-9]*)(\.[0-9]*)?([eE][+\-]?[0-9]+)?

GlobalKeyword = [gG][lL][oO][bB][aA][lL]

%state STRING


%%


<YYINITIAL> {

  /* keywords */
  "function" { return generateTokenSym(ParserSym.FUNCTION); }
  "end" { return generateTokenSym(ParserSym.END); }
  "if" { return generateTokenSym(ParserSym.IF); }
  "then" { return generateTokenSym(ParserSym.THEN); }
  "else" { return generateTokenSym(ParserSym.ELSE); }
  "while" { return generateTokenSym(ParserSym.WHILE); }
  "do" { return generateTokenSym(ParserSym.DO); }
  "for" { return generateTokenSym(ParserSym.FOR); }
  "local" { return generateTokenSym(ParserSym.LOCAL); }
  {GlobalKeyword} { return generateTokenSym(ParserSym.GLOBAL); }
  "<==" { return generateTokenSym(ParserSym.READ); }
  "==>" { return generateTokenSym(ParserSym.WRITE); }
  "return" { return generateTokenSym(ParserSym.RETURN); }
  "true" { return generateTokenSym(ParserSym.TRUE, true); }
  "false" { return generateTokenSym(ParserSym.FALSE, false); }
  "not" { return generateTokenSym(ParserSym.NOT); }
  "#" { return generateTokenSym(ParserSym.SHARP); }


  /* Types Keywords */
  "nil" { return generateTokenSym(ParserSym.NIL); }
  "int" { return generateTokenSym(ParserSym.INT); }
  "bool" { return generateTokenSym(ParserSym.BOOL); }
  "float" { return generateTokenSym(ParserSym.FLOAT); }
  "string" { return generateTokenSym(ParserSym.STRING); }

  /* separators */
  "(" { return generateTokenSym(ParserSym.LPAR); }
  ")" { return generateTokenSym(ParserSym.RPAR); }
  "{" { return generateTokenSym(ParserSym.BLPAR); }
  "}" { return generateTokenSym(ParserSym.BRPAR); }
  "[" { return generateTokenSym(ParserSym.SLPAR); }
  "]" { return generateTokenSym(ParserSym.SRPAR); }
  "," { return generateTokenSym(ParserSym.COMMA); }
  ";" { return generateTokenSym(ParserSym.SEMI); }
  ":" { return generateTokenSym(ParserSym.COLON); }

  /* relop */
  "and" { return generateTokenSym(ParserSym.AND); }
  "or" { return generateTokenSym(ParserSym.OR); }
  "<" { return generateTokenSym(ParserSym.LT); }
  "<=" { return generateTokenSym(ParserSym.LE); }
  "<>" { return generateTokenSym(ParserSym.NE); }
  ">" { return generateTokenSym(ParserSym.GT); }
  ">=" { return generateTokenSym(ParserSym.GE); }
  "->" { return generateTokenSym(ParserSym.ARROW); }
  "=" { return generateTokenSym(ParserSym.ASSIGN); }
  "==" { return generateTokenSym(ParserSym.EQ); }
  "!=" { return generateTokenSym(ParserSym.NE); }
  "nop" { return generateTokenSym(ParserSym.NOP); }

  /* arop */
  "+" { return generateTokenSym(ParserSym.PLUS); }
  "-" { return generateTokenSym(ParserSym.MINUS); }
  "*" { return generateTokenSym(ParserSym.TIMES); }
  "/" { return generateTokenSym(ParserSym.DIV); }


  /* identifiers */
  {Identifier} { return generateTokenSym(ParserSym.ID, yytext()); }

  /* literals */
  {IntegerLiteral} { return generateTokenSym(ParserSym.INT_CONST, Integer.parseInt(yytext())); }
  {FloatLiteral} { return generateTokenSym(ParserSym.FLOAT_CONST, Float.parseFloat(yytext())); }
  \" { string.setLength(0); yybegin(STRING); }

  /* comments */
  {Comment} {/* ignore */}

  /* whitespace */
  {WhiteSpace} { /* ignore */ }
}

<STRING> {
  \" {
    yybegin(YYINITIAL); 
    return generateTokenSym(ParserSym.STRING_CONST, 
    string.toString()); 
  }
  [^\n\r\"\\]+ { string.append( yytext() ); }
  \\t { string.append('\t'); }
  \\n { string.append('\n'); }
  \\r { string.append('\r'); }
  \\\" { string.append('\"'); }
  \\  { string.append('\\'); }
}

/* error fallback */
[^] {
  throw new RuntimeException("Error:(" + yyline + ":" + yycolumn + ") Cannot resolve symbol '"+yytext()+"'"); 
}