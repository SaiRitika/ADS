import java.util.Scanner;

public class BoyerMoore {

    private static int[] buildBadCharacterTable(String pattern) {
        int m = pattern.length();
        int[] badChar = new int[256]; // Assuming extended ASCII
        for (int i = 0; i < 256; i++) {
            badChar[i] = -1; // Initialize all to -1
        }
        for (int i = 0; i < m; i++) {
            badChar[(int) pattern.charAt(i)] = i; // Store the index of the character
        }
        return badChar;
    }

    public static void boyerMooreSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            System.out.println("Pattern is empty.");
            return;
        }
        if (n < m) {
            System.out.println("Text is shorter than the pattern.");
            return;
        }

        int[] badChar = buildBadCharacterTable(pattern);
        int s = 0; // s is the shift of the pattern with respect to text

        while (s <= n - m) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }
            if (j < 0) {
                System.out.println("Pattern found at index " + s);
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the pattern: ");
        String pattern = scanner.nextLine();

        scanner.close();

        boyerMooreSearch(text, pattern);
    }
}