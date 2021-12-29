package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.Score;

public class FirstBowl implements State {
    private static final int ZERO = 0;

    private final KnockedPinCount pinCount;

    public FirstBowl(int pinCount) {
        this.pinCount = new KnockedPinCount(pinCount);
    }

    @Override
    public State bowl(int count) {
        KnockedPinCount newPinCount = new KnockedPinCount(count);

        if (this.pinCount.sum(newPinCount).equals(KnockedPinCount.TEN_COUNT)) {
            return new Spare(this.pinCount, newPinCount);
        }

        return new Miss(this.pinCount, newPinCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }

    @Override
    public Score makeScore() {
        return new Score(pinCount.value(), ZERO);
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        return beforeScore.bowl(pinCount.value());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public String display() {
        return pinCount.display();
    }
}