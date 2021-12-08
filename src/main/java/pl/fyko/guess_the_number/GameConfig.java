package pl.fyko.guess_the_number;

/**
 * GameConfig class contains a runtime game configuration
 *
 * @author Filip Kołodziejczyk
 */
class GameConfig {
    private int lowerBound = 1;
    private int higherBound = 100;
    private int maxGuessCount = 10;

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
