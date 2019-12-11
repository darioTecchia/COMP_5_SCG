package visitor;

public interface Visitable {
  <T, P> T accept(Visitor<T, P> visitor, P arg);
}
