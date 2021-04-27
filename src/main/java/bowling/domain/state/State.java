package bowling.domain.state;

import bowling.dto.StateDTO;

public interface State {
    boolean isFinished();
    String state();
    State bowl(int pitch);
    StateDTO exportStateDTO();
}