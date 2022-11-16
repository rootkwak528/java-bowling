package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static User user1;
    public static User user2;
    public static Question question1_1;
    public static Question question1_2;
    public static Question question2_1;
    public static Answer answer1_1_1;
    public static Answer answer1_2_1;
    public static Answer answer1_2_2;

    @BeforeEach
    public void beforeEach() {
        user1 = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        user2 = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        question1_1 = new Question("title1", "contents1_1").writeBy(user1);  // user1 답변
        question1_2 = new Question("title2", "contents1_2").writeBy(user1);  // user1, user2 답변
        question2_1 = new Question("title2", "contents2_1").writeBy(user2);  // 답변 X

        answer1_1_1 = new Answer(user1, question1_1, "Answers Contents1_1_1");
        answer1_2_1 = new Answer(user1, question1_2, "Answers Contents1_1_2");
        answer1_2_2 = new Answer(user2, question1_2, "Answers Contents1_1_2");

        question1_1.addAnswer(answer1_1_1);
        question1_2.addAnswer(answer1_2_1);
        question1_2.addAnswer(answer1_2_2);
    }

    @Test
    @DisplayName("작성자가 답변 없는 질문 삭제할 경우 삭제 성공")
    public void delete_성공() throws CannotDeleteException {
        question2_1.delete(user2);
        assertThat(question2_1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 유저가 삭제할 경우 삭제 실패")
    public void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> question1_1.delete(user2))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThat(question1_1.isDeleted()).isFalse();
        assertThatThrownBy(() -> question2_1.delete(user1))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThat(question2_1.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("작성자가 단 답변만 있을 경우 삭제 성공")
    public void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        question1_1.delete(user1);
        assertThat(question1_1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 유저가 단 답변이 있을 경우 삭제 실패")
    public void delete_답변_중_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> question1_2.delete(user1))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThat(question1_2.isDeleted()).isFalse();
    }
}
