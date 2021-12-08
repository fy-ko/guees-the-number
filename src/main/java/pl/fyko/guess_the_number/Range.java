package pl.fyko.guess_the_number;

/**
 * @author Filip Kołodziejczyk
 */
class Range {
    private int lowerBound;
    private int higherBound;

    Range(int lowerBound, int higherBound) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
    }

    /**
     * Updates top bound if latest guess is higher than that or bottom bound if latest guess is lower than that.
     * Not necessary for game to work, just allows to give the user more information.
     *
     * @param guess latest guess made by player
     * @param number the number to be guessed
     */
    void updateBounds(int guess, int number) {
        if (!isGuessInBounds(guess)) return;

        updateTooHighGuess(guess, number);
        updateTooLowGuess(guess, number);
    }

    /**
     * Gets current lower bound.
     * @return lower bound
     */
    int getLowerBound() {
        return lowerBound;
    }

    /**
     * Gets current higher bound
     * @return higher bound
     */
    int getHigherBound() {
        return higherBound;
    }

    /**
     * Gets information if latest guess is in set bounds.
     * @return true if latest guess is between lower and higher bound
     */
    boolean isGuessInBounds(int guess) {
        return guess >= lowerBound && guess <= higherBound;
    }

    private void updateTooHighGuess(int guess, int number) {
        if (guess > number) {
            higherBound = guess - 1;
        }
    }

    private void updateTooLowGuess(int guess, int number) {
        if (guess < number) {
            lowerBound = guess + 1;
        }
    }
}
