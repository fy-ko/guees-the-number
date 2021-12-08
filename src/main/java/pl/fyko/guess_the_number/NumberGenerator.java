package pl.fyko.guess_the_number;

import java.util.Random;

/**
 * NumberGenerator class is responsible for generating new numbers for {@link Game}
 *
 * @see GameConfig
 * @author Filip Kołodziejczyk
 */
class NumberGenerator {
    private final Random random = new Random();
    private final GameConfig gameConfig;

    public NumberGenerator(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    /**
     * Gets a new random generated number based on {@link GameConfig}.
     * @return random generator number
     */
    int getRandomNumber() {
        return random.nextInt(gameConfig.getHigherBound() - gameConfig.getLowerBound())
                + gameConfig.getLowerBound();
    }
}
