package domain.model;

import java.util.ArrayList;
import java.util.List;

public class TodayWordle {

    private static final int MAX_NUMBER_OF_ATTEMPTS = 6;

    private final Word word; // 오늘의 단어
    private List<Matches> matches = new ArrayList<>();

    public TodayWordle(Word word) {
        this.word = word;
    }

    public void attempt(Word word) {
        if(isEnd()) {
            throw new IllegalStateException("더이상 게임을 진행할 수 없습니다.");
        }
        matches.add(this.word.match(word));
    }

    public boolean isEnd() {
        return (!matches.isEmpty() && getLastAttempt().isEnd()) || matches.size() >= MAX_NUMBER_OF_ATTEMPTS;
    }

    public Matches getLastAttempt() {
        return matches.get(matches.size() - 1);
    }

    public List<Matches> getAllAttempt() {
        return matches;
    }
}
