package pl.fyko.guess_the_number;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * UI class is responsible for showing user interface and getting information from user.
 *
 * @see GameConfig
 * @see MessageGenerator
 * @see Game
 * @author Filip Kołodziejczyk
 */
class UI {
    private final GameConfig gameConfig;
    private final MessageGenerator messageGenerator;
    private final Scanner scanner;

    UI(String language, int lowerBound, int higherBound, int maxGuessCount) {
        scanner = new Scanner(System.in);
        gameConfig = new GameConfig(lowerBound, higherBound, maxGuessCount);
        messageGenerator = new MessageGenerator(ResourceBundle.getBundle("messages", new Locale(language)));
    }

    void start() {
        System.out.println(messageGenerator.getWelcomeMessage());
        mainMenu();
    }

    private void mainMenu() {
        System.out.println(messageGenerator.getMainMenu());
        int option = readInt();
        selectMainMenu(option);
    }

    private void selectMainMenu(int option) {
        switch (option) {
            case 1 -> game();
            case 2 -> settingsMenu();
            case 3 -> {
                System.out.println(messageGenerator.getGoodbyeMessage());
                scanner.close();
            }
            default -> {
                System.out.println(messageGenerator.getMenuWrongOption());
                mainMenu();
            }
        }
    }

    private void game() {
        Game game = new Game(gameConfig);

        while(true) {
            System.out.print(messageGenerator.getGameTypeGuessMessage(game));
            int guess = readInt();
            System.out.println();

            game.setGuess(guess);
            System.out.println(messageGenerator.getGameResultMessage(game));
            if (game.isGameOver()) {
                break;
            } else {
                game.updateBounds();
            }
        }

        mainMenu();
    }

    private void settingsMenu() {
        System.out.println(messageGenerator.getSettingsMenu());
        int option = readInt();
        selectSettingsMenu(option);
    }

    private void selectSettingsMenu(int option) {
        switch (option) {
            case 1 -> lowerBoundSetting();
            case 2 -> higherBoundSetting();
            case 3 -> maxGuessCountSetting();
            case 4 -> {
                mainMenu();
                return;
            }
            default -> System.out.println(messageGenerator.getMenuWrongOption());
        }
        settingsMenu();
    }

    private void lowerBoundSetting() {
        int minimumValue = 1;
        int maximumValue = gameConfig.getHigherBound() - 1;

        System.out.print(messageGenerator.getSettingInfo(Setting.LOWER_BOUND,
                gameConfig.getLowerBound(), minimumValue, maximumValue));
        int newValue = readInt();

        if (newValue >= minimumValue && newValue <= maximumValue) {
            gameConfig.setLowerBound(newValue);
        } else {
            // ERROR, try again
            lowerBoundSetting();
        }
    }

    private void higherBoundSetting() {
        int minimumValue = gameConfig.getLowerBound() + 1;
        int maximumValue = 1000;

        System.out.print(messageGenerator.getSettingInfo(Setting.HIGHER_BOUND,
                gameConfig.getHigherBound(), minimumValue, maximumValue));
        int newValue = readInt();

        if (newValue >= minimumValue && newValue <= maximumValue) {
            gameConfig.setHigherBound(newValue);
        } else {
            // ERROR, try again
            higherBoundSetting();
        }
    }

    private void maxGuessCountSetting() {
        int minimumValue = 0;
        int maximumValue = 25;

        System.out.print(messageGenerator.getSettingInfo(Setting.MAX_GUESS_COUNT,
                gameConfig.getMaxGuessCount(), minimumValue, maximumValue));
        int newValue = readInt();

        if (newValue >= minimumValue && newValue <= maximumValue) {
            gameConfig.setMaxGuessCount(newValue);
        } else {
            // ERROR, try again
            maxGuessCountSetting();
        }
    }

    private int readInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next();
            return -1;
        }
    }
}
