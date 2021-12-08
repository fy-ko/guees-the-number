package pl.fyko.guess_the_number;

/**
 * Game class controls the flow of the game.
 *
 * Every new instance of this class creates a new Guess the Number game by generating a random number and
 * loading lower and higher bounds from {@link GameConfig}.
 *
 * @see GameConfig
 * @see NumberGenerator
 * @author Filip Kołodziejczyk
 */
class Game {
    private final GameConfig gameConfig;
    private final int number;
    private int lowerBound;
    private int higherBound;
    private int guessCount;
    private int guess;

    Game(GameConfig gameConfig) {
        this.gameConfig = gameConfig;

        NumberGenerator numberGenerator = new NumberGenerator(gameConfig);
        number = numberGenerator.getRandomNumber();

        lowerBound = this.gameConfig.getLowerBound();
        higherBound = this.gameConfig.getHigherBound();
    }

    /**
     * Returns count of typed guesses.
     *
     * @return count of typed guesses
     */
    int getRemainingGuessCount() {
        return gameConfig.getMaxGuessCount() - guessCount;
    }

    /**
     * Sets current guess and increments the count of typed guesses
     * if maximum guess count has not been exceeded.
     *
     * @param guess guessed number
     */
    void setGuess(int guess) {
        if (!isGameOver()) {
            this.guess = guess;
            guessCount++;
        }
    }

    /**
     * Updates top bound if latest guess is higher than that or bottom bound if latest guess is lower than that.
     * Not necessary for game to work, just allows to give the user more information.
     */
    void updateBounds() {
        if (!isGuessInBounds()) return;

        if (guess > number) {
            // too high
            higherBound = guess - 1;
        } else if (guess < number) {
            // too low
            lowerBound = guess + 1;
        }
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
     * Returns number to guess if the game is over, otherwise returns -1.
     * @return number to guess if the game is over, otherwise returns -1
     */
    int getNumber() {
        return isGameOver() ? number : -1;
    }

    /**
     * Gets information if the game has been won.
     * @return true if latest guess is equal to generated number
     */
    boolean isGameWon() {
        return guess == number;
    }

    /**
     * Gets information if the game has been lost.
     * @return true game is not won and {@link GameConfig#getMaxGuessCount()} hasn't been exceeded (if it is set)
     */
    boolean isGameLost() {
        return !isGameWon()
                && gameConfig.getMaxGuessCount() > 0
                && guessCount >= gameConfig.getMaxGuessCount();
    }

    /**
     * Gets information if the game is over - either won or lost.
     * @return true if game is won or lost
     */
    boolean isGameOver() {
        return isGameWon() || isGameLost();
    }

    /**
     * Gets information if latest guess is in set bounds.
     * @return true if latest guess is between lower and higher bound
     */
    boolean isGuessInBounds() {
        return guess >= lowerBound && guess <= higherBound;
    }

    /**
     * Gets information if latest guess was higher than generated number. There's no "isGuessTooLow()" method
     * because this method in connection with {@link Game#isGameWon()} can tell you that.
     * @return true if latest guess is higher than generated number
     */
    boolean isGuessTooHigh() {
        return guess > number;
    }
}
