package pl.fyko.guess_the_number;

/**
 * MessageGenerator contains static messages for game UIs. It also allows to generated messages based on game state.
 *
 * @see Game
 * @see Setting
 * @author Filip Kołodziejczyk
 */
class MessageGenerator {
    private static final String WELCOME = "Welcome to Guess The Number Game";
    private static final String GOODBYE = "Thanks for playing. Goodbye!";

    private static final String MAIN_MENU = "=== MENU ===";
    private static final String MAIN_MENU_PLAY = "Start game";
    private static final String MAIN_MENU_SETTINGS = "Game settings";
    private static final String MAIN_MENU_EXIT = "Quit game";

    private static final String SETTINGS_MENU = "=== SETTINGS ===";
    private static final String SETTINGS_MENU_LOWER_BOUND = "Set lower bound";
    private static final String SETTINGS_MENU_HIGHER_BOUND = "Set higher bound";
    private static final String SETTINGS_MENU_MAX_GUESS_COUNT = "Set maxium guess count";
    private static final String SETTINGS_MENU_BACK = "Back";

    private static final String MENU_WRONG_OPTION = "Wrong option";

    private static final String LOWER_BOUND = "Lower bound";
    private static final String HIGHER_BOUND = "Higher bound";
    private static final String MAX_GUESS_COUNT = "Max. guess count (0 - unlimited)";

    private static final String SETTING_CURRENT_VALUE = "- current value: %d";
    private static final String SETTING_MIN_VALUE = "- min. value: %d";
    private static final String SETTING_MAX_VALUE = "- max. value: %d";
    private static final String SETTING_NEW_VALUE = "- new value: ";

    private static final String GAME_TYPE_GUESS = "Type in your guess: ";
    private static final String GAME_BOUNDS = "Number is between %d and %d.";
    private static final String GAME_WON = "You've won!";
    private static final String GAME_LOST = "You've lost!";
    private static final String GAME_OVER = "The number was %d";
    private static final String GAME_OUT_OF_BOUNDS = "Guess is out of bounds.";
    private static final String GAME_TOO_HIGH = "Too high!";
    private static final String GAME_TOO_LOW = "Too low!";
    private static final String GAME_REMAINING_GUESS_COUNT = "%d guesses remaining.";

    String getWelcomeMessage() {
        return WELCOME;
    }

    String getGoodbyeMessage() {
        return GOODBYE;
    }

    String getMainMenu() {
        return "\n" + MAIN_MENU + "\n"
                + "1: " + MAIN_MENU_PLAY + "\n"
                + "2: " + MAIN_MENU_SETTINGS + "\n"
                + "3: " + MAIN_MENU_EXIT;
    }

    String getSettingsMenu() {
        return "\n" + SETTINGS_MENU + "\n"
                + "1: " + SETTINGS_MENU_LOWER_BOUND + "\n"
                + "2: " + SETTINGS_MENU_HIGHER_BOUND + "\n"
                + "3: " + SETTINGS_MENU_MAX_GUESS_COUNT + "\n"
                + "4: " + SETTINGS_MENU_BACK;
    }

    String getMenuWrongOption() {
        return MENU_WRONG_OPTION;
    }

    String getSettingInfo(Setting setting, int value, int minValue, int maxValue) {
        return switch (setting) {
            case LOWER_BOUND -> LOWER_BOUND;
            case HIGHER_BOUND -> HIGHER_BOUND;
            case MAX_GUESS_COUNT -> MAX_GUESS_COUNT;
        } + "\n"
                + String.format(SETTING_CURRENT_VALUE, value) + "\n"
                + String.format(SETTING_MIN_VALUE, minValue) + "\n"
                + String.format(SETTING_MAX_VALUE, maxValue) + "\n"
                + SETTING_NEW_VALUE;
    }

    String getGameTypeGuessMessage(Game game) {
        return String.format(GAME_BOUNDS, game.getBounds().getLowerBound(), game.getBounds().getHigherBound()) + "\n"
                + GAME_TYPE_GUESS;
    }

    String getGameResultMessage(Game game) {
        if (game.isGameWon()) {
            return GAME_WON + " " + String.format(GAME_OVER, game.getNumber());
        } else if (game.isGameLost()) {
            return GAME_LOST + " " + String.format(GAME_OVER, game.getNumber());
        } else if (!game.isGuessInBounds()) {
            return GAME_OUT_OF_BOUNDS
                    + getRemainingMessage(game.getRemainingGuessCount());
        } else if (game.isGuessTooHigh()) {
            return GAME_TOO_HIGH
                    + getRemainingMessage(game.getRemainingGuessCount());
        } else {
            return GAME_TOO_LOW
                    + getRemainingMessage(game.getRemainingGuessCount());
        }
    }

    private String getRemainingMessage(int remainingGuesses) {
        return remainingGuesses > 0 ? " " + String.format(GAME_REMAINING_GUESS_COUNT, remainingGuesses) : "";
    }
}
