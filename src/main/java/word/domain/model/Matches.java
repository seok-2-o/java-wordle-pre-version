package word.domain.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static word.domain.model.Matches.Type.MATCH;

public class Matches {

    public enum Type {
        MATCH,
        EXIST,
        MISMATCH
    }

    public static final Matches PERFECT = new Matches(new Type[]{MATCH, MATCH, MATCH, MATCH, MATCH});

    private List<Type> elements;

    public Matches(Type[] elements) {
        this.elements = Arrays.asList(elements);
    }

    public boolean isEnd() {
        return elements.stream()
                .allMatch(it -> it.equals(MATCH));
    }

    public Stream<Type> stream() {
        return elements.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matches matches = (Matches) o;
        return Objects.equals(elements, matches.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return "Matches{" +
                "elements=" + elements +
                '}';
    }
}
