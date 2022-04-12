package utility;

import domain.model.Word;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public final class WordPool {
    
    private static final String WORDS_TXT_PATH = "words.txt";

    private static final List<String> WORDS;

    static {
        List<String> liens = Collections.emptyList();
        try {
            ClassLoader loader = WordPool.class.getClassLoader();
            liens = Files.readAllLines(Paths.get(loader.getResource(WORDS_TXT_PATH).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        WORDS = liens;
    }

    private WordPool() {};

    public static Word getTodayWord() {
        int today = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        return new Word(WORDS.get(today % WORDS.size()));
    }
}
