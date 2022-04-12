package domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TodayWordleTest {

    private TodayWordle wordle;

    @BeforeEach
    void setup() {
        this.wordle = new TodayWordle(new Word("apple"));
    }

    @DisplayName("단어를 맞춘 경우 게임이 종료된다.")
    @Test
    void isEnd01() {
        wordle.attempt(new Word("apple"));
        assertThat(wordle.isEnd())
                .isTrue();
    }

    @DisplayName("6번 시도하면 게임이 종료된다.")
    @Test
    void isEnd02() {
        wordle.attempt(new Word("abced"));
        wordle.attempt(new Word("abced"));
        wordle.attempt(new Word("abced"));
        wordle.attempt(new Word("abced"));
        wordle.attempt(new Word("abced"));
        wordle.attempt(new Word("abced"));

        assertThat(wordle.isEnd())
                .isTrue();
    }
}
