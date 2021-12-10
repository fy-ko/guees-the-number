package pl.fyko.guess_the_number;

import java.util.*;

/**
 * Starting point of the application. Additionally parses arguments into map.
 *
 * @author Filip Kołodziejczyk
 */
class Main {
    private final static Set<String> SETTINGS = Set.of("l", "L", "H", "C");
    private final static Map<String, String> LANGUAGES = Map.of("en", "english",
                                                                "pl", "polski");

    public static void main(String... args) {
        Map<String, Object> map = argsToMap(args);
        UI ui = new UI((String) map.computeIfAbsent("l", s -> "en"),
                (Integer) map.computeIfAbsent("L", s -> 1),
                (Integer) map.computeIfAbsent("H", s -> 100),
                (Integer) map.computeIfAbsent("C", s -> 10));
        ui.start();
    }

    private static Map<String, Object> argsToMap(String... args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i+=2) {
            if (args[i].startsWith("-")) {
                try {
                    putIfValid(map, args[i].substring(1), args[i+1]);
                } catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    printHelp();
                    System.exit(1);
                }
            }
        }
        return map;
    }

    private static void printHelp() {
        System.out.println("Usage: java pl.fyko.guess_the_number.Main [OPTIONS]");
        System.out.println("If no options are passed, the default bounds are set (1-100)");
        System.out.println();
        System.out.println("Avaiable options:");
        System.out.println(" -l\t Language setting. Available languages:");
        printLanguages();
        System.out.println(" -L\t Lower bound setting (default 1)");
        System.out.println(" -H\t Higher bound setting (default 100)");
        System.out.println(" -H\t Max guess count setting (default 10)");
    }

    private static void printLanguages() {
        LANGUAGES.forEach((k, v) -> {
            System.out.printf("\t\t%s - %s%n", k, v);
        });
    }

    private static void putIfValid(Map<String, Object> map, String argument, Object value) throws IllegalArgumentException {
        if (SETTINGS.contains(argument) && isSettingValid(argument, value)) {
            map.put(argument, value);
        } else {
            throw new IllegalArgumentException(String.format("Invalid argument or value -%s", argument));
        }
    }

    private static boolean isSettingValid(String key, Object value) {
        if (!key.equals("l") && !(value instanceof Integer)) {
            return false;
        } else return !key.equals("l") || value instanceof String;
    }
}
