package bowling.domain.state;

import bowling.domain.Pins;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Strike extends FinishedState{
    private static final String state = "STRIKE";
    private final Pins pins;
    private Strike(Pins pins){
        this.pins = pins;
    }

    public static Strike of(Pins pins) {
        return new Strike(pins);
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        pins.add(this.pins.pins());
        return new StateDTO(state(),pins);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Strike)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}