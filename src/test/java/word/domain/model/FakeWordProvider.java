package word.domain.model;

import word.domain.model.Word;
import word.domain.model.WordProvider;

import java.time.LocalDate;

public class FakeWordProvider implements WordProvider {
    @Override
    public Word getTodayWord() {
        return null;
    }

    @Override
    public Word getWord(LocalDate date) {
        return null;
    }

    @Override
    public boolean contains(String word) {
        return false;
    }

    @Override
    public Word wrap(String word) {
        return new Word(word);
    }
}
