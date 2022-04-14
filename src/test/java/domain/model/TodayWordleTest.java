package domain.model;

import domain.model.helper.FakeWordProvider;
import infra.WordProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodayWordleTest {

    private WordProvider provider = new FakeWordProvider();
    private Wordle wordle;

    @BeforeEach
    void setup() {
        this.wordle = new Wordle(new Word("apple", provider));
    }

    @DisplayName("단어를 맞춘 경우 게임이 종료된다.")
    @Test
    void isEnd01() {
        wordle.attempt(new Word("apple", provider));
        assertThat(wordle.isEnd())
                .isTrue();
    }

    @DisplayName("6번 시도하면 게임이 종료된다.")
    @Test
    void isEnd02() {
        wordle.attempt(new Word("ddddd",provider));
        wordle.attempt(new Word("ddddd",provider));
        wordle.attempt(new Word("ddddd",provider));
        wordle.attempt(new Word("ddddd",provider));
        wordle.attempt(new Word("ddddd",provider));
        wordle.attempt(new Word("ddddd",provider));
        wordle.attempt(new Word("ddddd",provider));
        assertThat(wordle.isEnd())
                .isTrue();
    }
}
