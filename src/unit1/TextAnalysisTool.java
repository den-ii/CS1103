package unit1;
import java.util.*;

/**
 * Text analysis tool that will perform various operations on a given text input.
 * This tool will help users gain insights into the text data by performing
 * character and word analysis.
 * @author Deni Wisdom Ochiche
 */
public class TextAnalysisTool {
    /**
     * Entry point to the program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String logo = """
                 _____         _        _                _           _    \s
                |_   _|____  _| |_     / \\   _ __   __ _| |_   _ ___(_)___\s
                  | |/ _ \\ \\/ / __|   / _ \\ | '_ \\ / _` | | | | / __| / __|
                  | |  __/>  <| |_   / ___ \\| | | | (_| | | |_| \\__ \\ \\__ \\
                  |_|\\___/_/\\_\\\\__| /_/   \\_\\_| |_|\\__,_|_|\\__, |___/_|___/
                                                           |___/          \s
                """;
        System.out.println(logo);
        System.out.println("Welcome");
        try {
            boolean shouldLoop = startOrQuit();
            while (shouldLoop){
                String text = requestInput();
                countCharacter(text);
                countWords(text);
                mostCommonCharacter(text);
                characterFrequency(text);
                wordFrequency(text);
                uniqueWords(text);
                shouldLoop = startOrQuit();
            }
        } catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
        System.out.println("Quitting...");
    }

    /**
     * Request a response from System.in, starts or quits the program
     * based on that response.
     * @return true if program should start, otherwise false.
     */
    private static boolean startOrQuit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wish to input a text to gain analysis? (Y/N): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("Y");
    }

    /**
     * Ask user to input the text, analysis should be carried on
     * @return text - if blank request input again, otherwise the
     * String analysis should be carried on
     */
    private static String requestInput(){
        System.out.println("Please enter the text below: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        if (text.isBlank()) requestInput();
        return text;
    }

    /**
     * Counts the number of characters in a String.
     * @param text user inputted text, analysis should be carried on.
     */
    private static void countCharacter(String text){
        System.out.println("Counting characters...");
        System.out.printf("Number of characters: %d%n", text.length());
    }

    /**
     * Counts number of words in a text, and displays it.
     * @param text user inputted text, analysis should be carried on.
     */
    private static void countWords(String text){
        System.out.println("Counting words...");
        int length = text.strip().split(" ").length;
        System.out.printf("Number of words: %d%n", length);
    }

    /**
     * Finds the most common character and displays it
     * @param text user inputted text, analysis should be carried on.
     */
    private static void mostCommonCharacter(String text){
        System.out.println("Finding most characters...");
        var characterArray = List.of(text.strip().split(""));
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (var c : characterArray){
            if (map.containsKey(c)){
                int value = map.get(c);
                map.put(c, ++value);
                max =  Math.max(max, value);
            }
            else {
              map.put(c, 1);
              max =  Math.max(max, 1);
            }
        }
        System.out.println(map);
        for (var key: map.keySet()){
           if (map.get(key) == max){
               System.out.printf("Most Common Character: '%s'%n", key);
               break;
           };
        }
    }

    /**
     * Request a character from System.in, finds the frequency of the
     * character in the text and displays it.
     * @param text user inputted text, analysis should be carried on.
     */
    private static void characterFrequency(String text){
        System.out.println("Input the character you wish to know the frequency of: ");
        Scanner scanner = new Scanner(System.in);
        String input =  scanner.nextLine();
        String character = input.split("")[0];
        int frequency = 0;
        for (var c : text.split("")){
            if (c.equalsIgnoreCase(character)){
                frequency++;
            }
        }
        System.out.println("Checking character frequency...");
        System.out.printf("Character frequency '%s': %d%n", character, frequency);
    }

    /**
     * Request a word from System.in, finds the frequency of the
     * word in the text and displays it.
     * @param text user inputted text, analysis should be carried on.
     */
    private static void wordFrequency(String text){
        System.out.println("Input the word you wish to know the frequency of: ");
        Scanner scanner = new Scanner(System.in);
        String word =  scanner.nextLine().strip().split(" ")[0];
        int frequency = 0;
        for (var w : text.split("[\\s.,;:]+")){
            if (w.equalsIgnoreCase(word)){
                frequency++;
            }
        }
        System.out.println("Checking word frequency...");
        System.out.printf("Word frequency '%s': %d%n", word, frequency);
    }

    /**
     * Find unique words in the text.
     * @param text user inputted text, analysis should be carried on.
     */
    private static void uniqueWords(String text){
        Map<String, Integer> map = new HashMap<>();
        int frequency = 0;
        ArrayList<String> uniqueWordsList = new ArrayList<>();
        for (var w : text.split("[\\s.,;:]+")){
            String key = w.toLowerCase();
            if (map.containsKey(key)){
                int value = map.get(key);
                map.put(key, ++value);
            } else {
                map.put(key, 1);
            }
        }
        for (var key: map.keySet()){
            if (map.get(key) == 1){
                frequency++;
                uniqueWordsList.add(key);
            }
        }
        System.out.println(uniqueWordsList);
        System.out.println("Looking up unique words...");
        System.out.print("Number of unique words: " + frequency);
        uniqueWordsList.forEach(System.out::println);
    }
}
