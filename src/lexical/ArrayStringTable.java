package lexical;

import java.util.ArrayList;

/**
 * ArrayStringTable
 */
public class ArrayStringTable implements StringTable {

  private ArrayList<String> lexicalSymbols;

  public ArrayStringTable() {
    this.lexicalSymbols = new ArrayList<String>();
  }

  @Override
  public boolean install(String lexeme) {
    if(this.getAddress(lexeme) == -1) {
      return this.lexicalSymbols.add(lexeme);
    } else {
      return false;
    }
  }

  @Override
  public int getAddress(String lexeme) {
    return this.lexicalSymbols.indexOf(lexeme);
  }

  @Override
  public String getLexeme(int address) {
    return this.lexicalSymbols.get(address);
  }

  
}