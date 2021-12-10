package pl.fyko.guess_the_number;

import java.util.ResourceBundle;

/**
 * MessageGenerator contains static messages for game UIs. It also allows to generated messages based on game state.
 *
 * @see Game
 * @see Setting
 * @author Filip Kołodziejczyk
 */
class MessageGenerator {
    ResourceBundle resourceBundle;

    MessageGenerator(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    String getWelcomeMessage() {
        return resourceBundle.getString("welcome");
    }

    String getGoodbyeMessage() {
        return resourceBundle.getString("goodbye");
    }

    String getMainMenu() {
        return "\n" + resourceBundle.getString("menu.main_menu") + "\n"
                + "1: " + resourceBundle.getString("menu.main_menu.play") + "\n"
                + "2: " + resourceBundle.getString("menu.main_menu.settings") + "\n"
                + "3: " + resourceBundle.getString("menu.main_menu.exit");
    }

    String getSettingsMenu() {
        return "\n" + resourceBundle.getString("menu.settings_menu") + "\n"
                + "1: " + resourceBundle.getString("menu.settings_menu.lower_bound") + "\n"
                + "2: " + resourceBundle.getString("menu.settings_menu.higher_bound") + "\n"
                + "3: " + resourceBundle.getString("menu.settings_menu.max_guess_count") + "\n"
                + "4: " + resourceBundle.getString("menu.settings_menu.back");
    }

    String getMenuWrongOption() {
        return resourceBundle.getString("menu.wrong_option");
    }

    String getSettingInfo(Setting setting, int value, int minValue, int maxValue) {
        return switch (setting) {
            case LOWER_BOUND -> resourceBundle.getString("menu.settings_menu.settings.lower_bound");
            case HIGHER_BOUND -> resourceBundle.getString("menu.settings_menu.settings.higher_bound");
            case MAX_GUESS_COUNT -> resourceBundle.getString("menu.settings_menu.settings.max_guess_count");
        } + "\n"
                + String.format(resourceBundle.getString("menu.settings_menu.info.current_value"), value) + "\n"
                + String.format(resourceBundle.getString("menu.settings_menu.info.min_value"), minValue) + "\n"
                + String.format(resourceBundle.getString("menu.settings_menu.info.max_value"), maxValue) + "\n"
                + resourceBundle.getString("menu.settings_menu.info.new_value") + " ";
    }

    String getGameTypeGuessMessage(Game game) {
        return String.format(resourceBundle.getString("game.bounds"),
                    game.getBounds().getLowerBound(), game.getBounds().getHigherBound()) + "\n"
                + resourceBundle.getString("game.type_guess") + " ";
    }

    String getGameResultMessage(Game game) {
        if (game.isGameOver()) {
            return getGameOverMessage(game.isGameWon(), game.getNumber());
        } else if (!game.isGuessInBounds()) {
            return resourceBundle.getString("game.out_of_bounds")
                    + getRemainingMessage(game.getRemainingGuessCount());
        } else if (game.isGuessTooHigh()) {
            return resourceBundle.getString("game.too_high")
                    + getRemainingMessage(game.getRemainingGuessCount());
        } else {
            return resourceBundle.getString("game.too_low")
                    + getRemainingMessage(game.getRemainingGuessCount());
        }
    }

    private String getGameOverMessage(boolean won, int number) {
        return resourceBundle.getString(won ? "game.won" : "game.lost")
                + " " + String.format(resourceBundle.getString("game.over"), number);
    }

    private String getRemainingMessage(int remainingGuesses) {
        return remainingGuesses > 0 ? " " + String.format(resourceBundle.getString("game.remaining_guess_count"),
                                                remainingGuesses) : "";
    }
}
