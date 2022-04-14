package domain.model;

import infra.WordProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word {

    private static final int MAX_WORD_SIZE = 5;

    private List<String> words;

    private Word(String word) {
        this.words = split(word);
    }

    public Word(String word, WordProvider provider) {
        validate(word, provider);
        this.words = split(word);
    }

    private List<String> split(String word) {
        return Arrays.asList(word.toLowerCase().split(""));
    }

    public static Word todayWord(WordProvider provider) {
        return new Word(provider.getTodayWord());
    }

    private void validate(String word, WordProvider provider) {
        if(!provider.contains(word)) {
            throw new IllegalArgumentException("등록된 단어만 입력 가능합니다.");
        }
    }

    public Matches match(Word other) {

        if (this.words.equals(other.words)) {
            return Matches.PERFECT;
        }

        List<String> temp = new ArrayList<>(this.words);
        Matches.Type[] result = new Matches.Type[MAX_WORD_SIZE];
        Arrays.fill(result, Matches.Type.MISMATCH);

        for (int idx = 0; idx < MAX_WORD_SIZE; idx++) {
            String current = other.words.get(idx);
            if (current.equals(this.words.get(idx))) {
                result[idx] = Matches.Type.MATCH;
                temp.remove(current);
            }
        }

        for (int idx = 0; idx < MAX_WORD_SIZE; idx++) {
            if (result[idx] != Matches.Type.MATCH && temp.remove(other.words.get(idx))) {
                result[idx] = Matches.Type.EXIST;
            }
        }
        return new Matches(result);
    }

    @Override
    public String toString() {
        return String.join("", words);
    }
}
