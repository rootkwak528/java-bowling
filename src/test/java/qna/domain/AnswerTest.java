package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static User user1;
    public static User user2;
    public static Question question1_1;
    public static Answer answer1_1_1;
    public static Answer answer1_1_2;

    @BeforeEach
    public void beforeEach() {
        user1 = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        user2 = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        question1_1 = new Question("title1", "contents1_1").writeBy(user1);

        answer1_1_1 = new Answer(user1, question1_1, "Answers Contents1_1_1");
        answer1_1_2 = new Answer(user2, question1_1, "Answers Contents1_1_2");

        question1_1.addAnswer(answer1_1_1);
        question1_1.addAnswer(answer1_1_2);
    }

    @Test
    @DisplayName("동일한 유저일 때 equals() return true")
    public void delete_성공() throws CannotDeleteException {
        answer1_1_1.deleteValidate(user1);
        answer1_1_1.delete();
        answer1_1_2.deleteValidate(user2);
        answer1_1_2.delete();

        assertThat(answer1_1_1.isDeleted()).isTrue();
        assertThat(answer1_1_2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("동일한 유저일 때 equals() return true")
    public void delete_다른_사람이_쓴_답변() throws CannotDeleteException {
        assertThatThrownBy(() -> answer1_1_1.deleteValidate(user2))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> answer1_1_2.deleteValidate(user1))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }
}
