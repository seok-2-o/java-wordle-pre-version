package domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchesTest {

    @DisplayName("모든 문자가 일치하면 게임은 종료 된다.")
    @Test
    void isEnd() {
        Assertions.assertThat(Matches.PERFECT.isEnd()).isTrue();
    }

}
