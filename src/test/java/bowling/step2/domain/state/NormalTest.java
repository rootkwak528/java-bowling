package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalTest {
    public Normal normal;
    
    @BeforeEach
    void setUp() {
        normal = new Normal(new Score(3));
    }
    
    @Test
    @DisplayName("스페어")
    void spare() {
        assertThat(normal.bowl(7)).isExactlyInstanceOf(Spare.class);
    }
    
    @Test
    @DisplayName("미스")
    void miss() {
        assertThat(normal.bowl(6)).isExactlyInstanceOf(Miss.class);
    }
    
    @Test
    @DisplayName("프레임 진행")
    void frame_running() {
        assertThat(normal.isFinished()).isFalse();
    }
    
    @Test
    @DisplayName("점수 확인")
    void display() {
        assertThat(normal.getScores()).isEqualTo(List.of(new Score(3)));
    }
}