
import java.util.*; // Import java.util package for List and ArrayList

public class Bruteforce {

    // Method to find all occurrences of a pattern in a given text
    public static List<Integer> findAllOccurrences(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        List<Integer> occurrences = new ArrayList<>(); // List to store indices of matches

        // Iterate through the text to search for the pattern
        for (int i = 0; i <= textLength - patternLength; i++) {
            int j = 0;

            // Compare the pattern with the current substring of text
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            // If the entire pattern matches, record the starting index
            if (j == patternLength) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get user input for text
        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        // Get user input for the pattern
        System.out.print("Enter the pattern to match: ");
        String pattern = sc.nextLine();

        // Find all occurrences of the pattern
        List<Integer> positions = findAllOccurrences(text, pattern);

        // Print the results
        if (positions.isEmpty()) {
            System.out.println("Pattern not found in the text.");
        } else {
            System.out.println("Pattern found at the following positions: ");
            for (int pos : positions) {
                System.out.println("Start: " + pos + ", End: " + (pos + pattern.length() - 1));
            }
        }

        sc.close();
    }
}

   