package domain.model;

import java.util.ArrayList;
import java.util.List;

public class TodayWordle {

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

    // 모두 맞거나 6번 되면 끝
    public boolean isEnd() {
        return (!matches.isEmpty() && getLastAttempt().isEnd()) || matches.size() >= 6 ;
    }

    public Matches getLastAttempt() {
        return matches.get(matches.size() - 1);
    }

    public List<Matches> getAllAttempt() {
        return matches;
    }
}
