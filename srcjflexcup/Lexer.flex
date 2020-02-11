/* JFlex example: part of Java language lexer specification */
package dist;

import java_cup.runtime.Symbol;
import lexical.StringTable;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
/**
* This class is a simple example lexer.
*/


%%

%class Lexer
%public
%unicode

%cupsym ParserSym

%cup

%line
%column



%{
  private StringTable table;
  private StringBuffer string = new StringBuffer();
  private ComplexSymbolFactory symbolFactory;

  private Symbol generateTokenSym(String name, int type) {
    return this.symbolFactory.newSymbol(name, type, new Location(yyline + 1, yycolumn + 1 - yylength()), new Location(yyline + 1, yycolumn + 1));
  }
  private Symbol generateTokenSym(String name, int type, Object value) {
    this.table.install(value.toString());
    return this.symbolFactory.newSymbol(name, type, new Location(yyline + 1, yycolumn + 1 - yylength()), new Location(yyline + 1, yycolumn + 1), value);
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

  public Lexer(StringTable table, ComplexSymbolFactory symbolFactory) {
    this.symbolFactory = symbolFactory;
    this.table = table;
  }

  protected void emit_warning(String message) {
    System.out.println("scanner warning: " + message + " at : 2 "+ (yyline+1) + " " + (yycolumn+1));
  }

  protected void emit_error(String message) {
    System.out.println("scanner error: " + message + " at : 2" + (yyline+1) + " " + (yycolumn+1) + " " + yychar);
  }
%}

%eofval{
	return generateTokenSym("EOF", ParserSym.EOF);
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
  "function" { return generateTokenSym("FUNCTION", ParserSym.FUNCTION); }
  "end" { return generateTokenSym("END", ParserSym.END); }
  "if" { return generateTokenSym("IF", ParserSym.IF); }
  "then" { return generateTokenSym("THEN", ParserSym.THEN); }
  "else" { return generateTokenSym("ELSE", ParserSym.ELSE); }
  "while" { return generateTokenSym("WHILE", ParserSym.WHILE); }
  "do" { return generateTokenSym("DO", ParserSym.DO); }
  "for" { return generateTokenSym("FOR", ParserSym.FOR); }
  "local" { return generateTokenSym("LOCAL", ParserSym.LOCAL); }
  {GlobalKeyword} { return generateTokenSym("GLOBAL", ParserSym.GLOBAL); }
  "<==" { return generateTokenSym("READ", ParserSym.READ); }
  "==>" { return generateTokenSym("WRITE", ParserSym.WRITE); }
  "return" { return generateTokenSym("RETURN", ParserSym.RETURN); }
  "true" { return generateTokenSym("TRUE", ParserSym.TRUE, true); }
  "false" { return generateTokenSym("FALSE", ParserSym.FALSE, false); }
  "not" { return generateTokenSym("NOT", ParserSym.NOT); }
  "#" { return generateTokenSym("SHARP", ParserSym.SHARP); }


  /* Types Keywords */
  "nil" { return generateTokenSym("NIL", ParserSym.NIL); }
  "int" { return generateTokenSym("INT", ParserSym.INT); }
  "bool" { return generateTokenSym("BOOL", ParserSym.BOOL); }
  "float" { return generateTokenSym("FLOAT", ParserSym.FLOAT); }
  "string" { return generateTokenSym("STRING", ParserSym.STRING); }

  /* separators */
  "(" { return generateTokenSym("LPAR", ParserSym.LPAR); }
  ")" { return generateTokenSym("RPAR", ParserSym.RPAR); }
  "{" { return generateTokenSym("BLPAR", ParserSym.BLPAR); }
  "}" { return generateTokenSym("BRPAR", ParserSym.BRPAR); }
  "[" { return generateTokenSym("SLPAR", ParserSym.SLPAR); }
  "]" { return generateTokenSym("SRPAR", ParserSym.SRPAR); }
  "," { return generateTokenSym("COMMA", ParserSym.COMMA); }
  ";" { return generateTokenSym("SEMI", ParserSym.SEMI); }
  ":" { return generateTokenSym("COLON", ParserSym.COLON); }

  /* relop */
  "and" { return generateTokenSym("AND", ParserSym.AND); }
  "or" { return generateTokenSym("OR", ParserSym.OR); }
  "<" { return generateTokenSym("LT", ParserSym.LT); }
  "<=" { return generateTokenSym("LE", ParserSym.LE); }
  "<>" { return generateTokenSym("NE", ParserSym.NE); }
  ">" { return generateTokenSym("GT", ParserSym.GT); }
  ">=" { return generateTokenSym("GE", ParserSym.GE); }
  "->" { return generateTokenSym("ARROW", ParserSym.ARROW); }
  "=" { return generateTokenSym("ARROW", ParserSym.ASSIGN); }
  "==" { return generateTokenSym("EQ", ParserSym.EQ); }
  "!=" { return generateTokenSym("NE", ParserSym.NE); }
  "nop" { return generateTokenSym("NOP", ParserSym.NOP); }

  /* arop */
  "+" { return generateTokenSym("PLUS", ParserSym.PLUS); }
  "-" { return generateTokenSym("MINUS", ParserSym.MINUS); }
  "*" { return generateTokenSym("TIMES", ParserSym.TIMES); }
  "/" { return generateTokenSym("DIV", ParserSym.DIV); }


  /* identifiers */
  {Identifier} { return generateTokenSym("ID", ParserSym.ID, yytext()); }

  /* literals */
  {IntegerLiteral} { return generateTokenSym("INT_CONST", ParserSym.INT_CONST, Integer.parseInt(yytext())); }
  {FloatLiteral} { return generateTokenSym("FLOAT_CONST", ParserSym.FLOAT_CONST, Float.parseFloat(yytext())); }
  \" { string.setLength(0); yybegin(STRING); }

  /* comments */
  {Comment} {/* ignore */}

  /* whitespace */
  {WhiteSpace} { /* ignore */ }
}

<STRING> {
  \" {
    yybegin(YYINITIAL); 
    return generateTokenSym("STRING_CONST", ParserSym.STRING_CONST,
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