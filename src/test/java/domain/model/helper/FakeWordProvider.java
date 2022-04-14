package domain.model.helper;

import infra.WordProvider;

import java.util.List;

public class FakeWordProvider implements WordProvider {

    private List<String> pool = List.of("apple", "palep", "ddddd", "alpha");

    @Override
    public String getTodayWord() {
        return "apple";
    }

    @Override
    public boolean contains(String word) {
        return pool.contains(word);
    }
}
