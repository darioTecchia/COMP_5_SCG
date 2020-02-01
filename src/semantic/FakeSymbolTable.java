package semantic;

import lexical.StringTable;
import java.util.Optional;

public class FakeSymbolTable implements SymbolTable {

  private final StackSymbolTable table;
  private final StringTable sTable;
  private int currentLevel;

  public FakeSymbolTable(StackSymbolTable table, StringTable sTable) {
    this.table = table;
    this.sTable = sTable;
    this.currentLevel = -1;
  }

  @Override
  public void enterScope() {
    this.currentLevel++;
  }

  @Override
  public void exitScope() {
    this.currentLevel--;
  }

  @Override
  public int getScopeLevel() {
    return this.currentLevel;
  }

  @Override
  public boolean probe(String lexeme) {
    int address = this.sTable.getAddress(lexeme);
    return this.table.get(this.currentLevel).containsKey(address);
  }

  @Override
  public Optional<SymbolTableRecord> lookup(String lexeme) {
    int address = this.sTable.getAddress(lexeme);
    int level = this.currentLevel;
    while(level >= 0){
      if(this.table.get(level).containsKey(address)) {
        return Optional.of(this.table.get(level).get(address));
      }
      level--;
    }
    return Optional.empty();
  }

  @Override
  public void addEntry(String lexeme, SymbolTableRecord str) {
    int address = this.sTable.getAddress(lexeme);
    this.table.get(this.currentLevel).put(address, str);
  }

}
