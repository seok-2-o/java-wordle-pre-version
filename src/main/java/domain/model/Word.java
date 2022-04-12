package domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Word {

    private static final int MAX_WORD_SIZE = 5;
    private static final Pattern WORD_RULE = Pattern.compile("^[a-zA-Z]*$");

    private List<String> words;

    public Word(String word) {
        validate(word);
        this.words = Arrays.asList(word.toLowerCase().split(""));
    }

    private void validate(String word) {
        if (Objects.isNull(word) || word.length() != MAX_WORD_SIZE) {
            throw new IllegalArgumentException("다섯글자 단어만 가능합니다.");
        }
        if (!WORD_RULE.matcher(word).matches()) {
            throw new IllegalArgumentException("영어 단어만 입력 가능합니다.");
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
            if (other.words.get(idx).equals(this.words.get(idx))) {
                result[idx] = Matches.Type.MATCH;
                temp.remove(idx);
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
