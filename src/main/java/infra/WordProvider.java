package infra;

import java.time.LocalDate;

public interface WordProvider {

    String getTodayWord();
    boolean contains(String word);
}
