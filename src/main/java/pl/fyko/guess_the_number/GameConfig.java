package pl.fyko.guess_the_number;

/**
 * GameConfig class contains a runtime game configuration
 *
 * @author Filip Kołodziejczyk
 */
class GameConfig {
    public final static int DEFAULT_LOWER_BOUND = 1;
    public final static int DEFAULT_HIGHER_BOUND = 100;
    public final static int DEFAULT_GUESS_COUNT = 10;

    private int lowerBound;
    private int higherBound;
    private int maxGuessCount;

    GameConfig(int lowerBound, int higherBound, int maxGuessCount) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
        this.maxGuessCount = maxGuessCount;

        verifyBounds();
        verifyMaxGuessCount();
    }

    int getLowerBound() {
        return lowerBound;
    }

    void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    int getHigherBound() {
        return higherBound;
    }

    void setHigherBound(int higherBound) {
        this.higherBound = higherBound;
    }

    int getMaxGuessCount() {
        return maxGuessCount;
    }

    void setMaxGuessCount(int maxGuessCount) {
        this.maxGuessCount = maxGuessCount;
    }

    private void verifyBounds() {
        if (higherBound <= lowerBound) {
            lowerBound = GameConfig.DEFAULT_LOWER_BOUND;
            higherBound = GameConfig.DEFAULT_HIGHER_BOUND;
        }
    }

    private void verifyMaxGuessCount() {
        if (maxGuessCount < 0 || maxGuessCount > 25) {
            maxGuessCount = GameConfig.DEFAULT_GUESS_COUNT;
        }
    }
}
