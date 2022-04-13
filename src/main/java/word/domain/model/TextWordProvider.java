package word.domain.model;

import word.domain.model.Word;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


public final class TextWordProvider implements WordProvider {
    
    private static final String WORDS_TXT_PATH = "words.txt";
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
    public Word getTodayWord() {
        return getWord(LocalDate.now());
    }

    @Override
    public Word getWord(LocalDate date) {
        int seed = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) % words.size();
        return new Word(words.get(seed));
    }

    @Override
    public boolean contains(String word) {
        return words.contains(word);
    }

    @Override
    public Word wrap(String word) {
        if(!contains(word)) {
            throw new IllegalArgumentException("등록된 단어만 입력 가능합니다.");
        }
        return new Word(word);
    }
}
