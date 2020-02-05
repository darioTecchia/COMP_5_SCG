package template;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author didacus
 */
public class CTemplate implements Template<String> {

  @Override
  public void write(String filePath, String model) {
    filePath = filePath.replace(".mp", ".c");
    try (FileWriter fw = new FileWriter(filePath)) {
      fw.write(model);
    } catch (IOException ex) {
      System.err.println("Input/Output Error during C Template rendering");
    }
  }

  @Override
  public Optional<String> create() {
    String builder = null;
    try {
      builder = new String(this.getClass().getResourceAsStream("/resources/program.c").readAllBytes());
    } catch (IOException ex) {
      System.err.println("Input/Output Errror during model creation");
    }
    return Optional.ofNullable(builder);
  }

}
