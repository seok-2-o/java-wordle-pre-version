package infra;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;


public final class TextWordProvider implements WordProvider {

    private static final String WORDS_TXT_PATH = "words.txt";
    private static final long WORDLE_DAY = LocalDate.of(2021, 6, 19).toEpochDay();
    private final List<String> words;

    public TextWordProvider() {
        List<String> liens = Collections.emptyList();
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            liens = Files.readAllLines(Paths.get(loader.getResource(WORDS_TXT_PATH).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        words = liens;
    }

    @Override
    public String getTodayWord() {
        return getWord(ZonedDateTime.now(ZoneId.of("America/New_York")).toLocalDate());
    }

    private String getWord(LocalDate date) {
        int diff = Math.toIntExact(date.toEpochDay() - WORDLE_DAY);
        int seed = diff % words.size();
        return words.get(seed);
    }

    @Override
    public boolean contains(String word) {
        return words.contains(word);
    }


}
