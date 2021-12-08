package pl.fyko.guess_the_number;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Starting point of the application. Additionally parses arguments into map.
 *
 * @author Filip Kołodziejczyk
 */
class Main {
    private final static Set<String> SETTINGS = Set.of("l", "h");

    public static void main(String... args) {
        Map<String, Integer> map = argsToMap(args);
        UI ui = new UI(map.computeIfAbsent("l", s -> 1),
                        map.computeIfAbsent("h", s -> 100));
        ui.start();
    }

    private static Map<String, Integer> argsToMap(String... args) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i+=2) {
            if (args[i].startsWith("-")) {
                try {
                    putIfValid(map, args[i].substring(1), Integer.parseInt(args[i+1]));
                } catch(NumberFormatException e) {
                    System.out.printf("Invalid value for argument %s%n", args[i]);
                }
            }
        }
        return map;
    }

    private static void putIfValid(Map<String, Integer> map, String argument, Integer value) throws IllegalArgumentException {
        if (SETTINGS.contains(argument)) {
            map.put(argument, value);
        } else {
            throw new IllegalArgumentException(String.format("Invalid argument -%s", argument));
        }
    }
}
