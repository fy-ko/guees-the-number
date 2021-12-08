package pl.fyko.guess_the_number;

/**
 * GameConfig class contains a runtime game configuration
 *
 * @author Filip Kołodziejczyk
 */
class GameConfig {
    private int lowerBound;
    private int higherBound;
    private int maxGuessCount;

    GameConfig(int lowerBound, int higherBound, int maxGuessCount) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
        this.maxGuessCount = maxGuessCount;
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
}
