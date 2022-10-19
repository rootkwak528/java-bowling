package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerNameTest {
    private static final String PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE = "플레이어 이름 형식이 잘못되었습니다. 다시 입력해주세요.";
    public static final PlayerName PLAYER_NAME = new PlayerName("ABC");
    
    @Test
    @DisplayName("플레이어 이름 반환 받기")
    void input_player_name() {
        assertThat(PLAYER_NAME).isNotNull();
    }
    
    @ParameterizedTest(name = "{displayName}")
    @DisplayName("\"\" 입력 시 예외")
    @EmptySource
    void input_empty_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName(input))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("숫자 입력 시 예외")
    void input_number_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName("A2C"))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("특수 문자 입력 시 예외")
    void input_special_characters_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName("A@C"))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
    
    @ParameterizedTest(name = "{displayName} = {0}")
    @DisplayName("공백 입력 시 예외")
    @ValueSource(strings = {"AB C", "A C"})
    void input_space_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName(input))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("소문자 입력 시 예외")
    void input_small_letter_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName("AbC"))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
    
    @Test
    @DisplayName("3자 초과 입력 시 예외")
    void input_length_exceed_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName("ABCD"))
                .withMessage(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
}