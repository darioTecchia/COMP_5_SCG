package lexical;

/**
 * A string table is used to memorize the identifier encountered during lexical
 * analysis
 */
public interface StringTable {

  boolean install(String lexeme);

  int getAddress(String lexeme);

  String getLexeme(int address);
}
