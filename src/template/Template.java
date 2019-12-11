package template;

import java.util.Optional;

public interface Template<T> {

  void write(String filePath, T model);

  Optional<T> create();

}