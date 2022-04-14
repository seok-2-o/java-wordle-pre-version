package domain.model;

import domain.model.helper.FakeWordProvider;
import infra.WordProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.model.Matches.Type.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {

    private WordProvider provider = new FakeWordProvider();

    private Word fixture;

    @BeforeEach
    void setup() {
        fixture = new Word("apple", provider);
    }

    @DisplayName("단어 목록에 없는 단어는 사용할 수 없습니다.")
    @ValueSource(strings = {"aaaaa", "bbbbbb"})
    @NullAndEmptySource
    @ParameterizedTest
    void create02(String word) {
        assertThatThrownBy(() -> new Word(word, provider))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매칭 테스트 - 완전일치")
    @Test
    void match01() {
        Matches matches = fixture.match(new Word("apple", provider));
        assertThat(matches).isEqualTo(Matches.PERFECT);
    }

    @DisplayName("매칭 테스트 - 위치가 다른 경우")
    @Test
    void match02() {
        Matches matches = fixture.match(new Word("palep", provider));
        assertThat(matches).isEqualTo(new Matches(new Matches.Type[]{EXIST, EXIST, EXIST, EXIST, EXIST}));
    }

    @DisplayName("매칭 테스트 - 모두 일치하지 않는 경우")
    @Test
    void match03() {
        Matches matches = fixture.match(new Word("ddddd", provider));
        assertThat(matches).isEqualTo(new Matches(new Matches.Type[]{MISMATCH, MISMATCH, MISMATCH, MISMATCH, MISMATCH}));
    }

    @DisplayName("매칭 테스트 - 복합")
    @Test
    void match04() {
        Matches matches = fixture.match(new Word("alpha", provider));
        assertThat(matches).isEqualTo(new Matches(new Matches.Type[]{MATCH, EXIST, MATCH, MISMATCH, MISMATCH}));
    }


}
