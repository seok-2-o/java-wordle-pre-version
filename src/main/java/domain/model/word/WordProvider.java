package domain.model.word;

import java.time.LocalDate;

public interface WordProvider {

    Word getTodayWord();
    Word getWord(LocalDate date);
    boolean contains(String word);
    Word wrap(String word);
}