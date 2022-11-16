package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {
    public static final User user1 = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User user2 = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @ParameterizedTest
    @MethodSource("provideUserForEqualTest")
    @DisplayName("동일한 유저일 때 equals() return true")
    public void equalTest(User user1, User user2) {
        assertThat(user1.equals(user2)).isTrue();
    }

    private static Stream<Arguments> provideUserForEqualTest() {
        return Stream.of(
                Arguments.of(user1, user1),
                Arguments.of(user2, user2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideUserForNotEqualTest")
    @DisplayName("서로 다른 유저일 때 equals() return false")
    public void notEqualTest(User user1, User user2) {
        assertThat(user1.equals(user2)).isFalse();
    }

    private static Stream<Arguments> provideUserForNotEqualTest() {
        return Stream.of(
                Arguments.of(user1, user2),
                Arguments.of(user2, user1)
        );
    }
}
