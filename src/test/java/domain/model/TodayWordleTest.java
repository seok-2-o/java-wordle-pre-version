package domain.model;

import word.domain.model.FakeWordProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import word.domain.model.WordProvider;

import static org.assertj.core.api.Assertions.assertThat;

class TodayWordleTest {

    private WordProvider provider = new FakeWordProvider();
    private Wordle wordle;

    @BeforeEach
    void setup() {
        this.wordle = new Wordle(provider.wrap("apple"));
    }

    @DisplayName("단어를 맞춘 경우 게임이 종료된다.")
    @Test
    void isEnd01() {
        wordle.attempt(provider.wrap("apple"));
        assertThat(wordle.isEnd())
                .isTrue();
    }

    @DisplayName("6번 시도하면 게임이 종료된다.")
    @Test
    void isEnd02() {
        wordle.attempt(provider.wrap("abced"));
        wordle.attempt(provider.wrap("abced"));
        wordle.attempt(provider.wrap("abced"));
        wordle.attempt(provider.wrap("abced"));
        wordle.attempt(provider.wrap("abced"));
        wordle.attempt(provider.wrap("abced"));

        assertThat(wordle.isEnd())
                .isTrue();
    }
}
