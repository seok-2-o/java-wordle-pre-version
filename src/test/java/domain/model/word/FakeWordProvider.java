package domain.model.word;

import domain.model.word.Word;
import domain.model.word.WordProvider;

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
