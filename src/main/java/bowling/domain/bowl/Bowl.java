package bowling.domain.bowl;

import bowling.domain.NumberOfPins;

public class Bowl {

    private final NumberOfPins numberOfPins = new NumberOfPins();

    private BowlStatus bowlStatus = BowlStatus.NONE;

    public Bowl() {

    }

    public Bowl(int firstNumberOfPin) {
        bowl(firstNumberOfPin);
    }

    public Bowl(int firstNumberOfPin, int secondNumberOfPin) {
        bowl(firstNumberOfPin);
        bowl(secondNumberOfPin);
    }

    public void bowl(int numberOfPin) {
        BowlValidator.validateBowlCount(getBowlCount());
        BowlValidator.validateTotalNumberOfPins(getTotalNumberOfPin(), numberOfPin);
        addNumberOfPins(numberOfPin);
        updateNormalBowlResult();
    }

    private void addNumberOfPins(int numberOfPin) {
        numberOfPins.addNumberOfPins(numberOfPin);
    }

    private void updateNormalBowlResult() {
        bowlStatus = BowlStatus.getType(this);
    }

    public boolean isNone() {
        return numberOfPins.isNone();
    }

    public boolean isCompleted() {
        return bowlStatus.isCompleted();
    }

    public boolean isIncomplete() {
        return !isCompleted();
    }

    public boolean isBonus() {
        return bowlStatus.isBonus();
    }

    public int getFirstNumberOfPins() {
        return numberOfPins.getFirstNumberOfPins();
    }

    public int getSecondNumberOfPins() {
        return numberOfPins.getSecondNumberOfPins();
    }

    public int getBowlCount() {
        return numberOfPins.getBowlCount();
    }

    public int getTotalNumberOfPin() {
        return numberOfPins.getTotalNumberOfPin();
    }

    public String format() {
        return bowlStatus.format(this);
    }

}