package domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.model.Matches.Type.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {

    private static final Word FIXTURE = new Word("apple");

    @DisplayName("영어 단어만 사용할 수 있습니다.")
    @Test
    void create01() {
        assertThatThrownBy(() -> new Word("한글은안돼"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("영어 단어만 입력 가능합니다.");
    }

    @DisplayName("영어 단어만 사용할 수 있습니다.")
    @ValueSource(strings = {"aaaa", "bbbbbb"})
    @NullAndEmptySource
    @ParameterizedTest
    void create02(String word) {
        assertThatThrownBy(() -> new Word(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다섯글자 단어만 가능합니다.");
    }

    @DisplayName("매칭 테스트 - 완전일치")
    @Test
    void match01() {
        Matches matches = FIXTURE.match(new Word("apple"));
        assertThat(matches).isEqualTo(Matches.PERFECT);
    }

    @DisplayName("매칭 테스트 - 위치가 다른 경우")
    @Test
    void match02() {
        Matches matches = FIXTURE.match(new Word("palep"));
        assertThat(matches).isEqualTo(new Matches(new Matches.Type[]{EXIST, EXIST, EXIST, EXIST, EXIST}));
    }

    @DisplayName("매칭 테스트 - 모두 일치하지 않는 경우")
    @Test
    void match03() {
        Matches matches = FIXTURE.match(new Word("ddddd"));
        assertThat(matches).isEqualTo(new Matches(new Matches.Type[]{MISMATCH, MISMATCH, MISMATCH, MISMATCH, MISMATCH}));
    }

    @DisplayName("매칭 테스트 - 복합")
    @Test
    void match04() {
        Matches matches = FIXTURE.match(new Word("alpha"));
        assertThat(matches).isEqualTo(new Matches(new Matches.Type[]{MATCH, EXIST, MATCH, MISMATCH, MISMATCH}));
    }


}
