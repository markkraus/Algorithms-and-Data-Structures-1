import java.util.Scanner;

public class Rec11 {
  public static void main(String[] args) {
    // Create a Scanner object for user input
    Scanner scanner = new Scanner(System.in);

    System.out.print("Initial size of the HashedDictionary > ");
    int init_M = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    System.out.print("Maximum alpha value for resizing (in a double) > ");
    double alpha = scanner.nextDouble();
    scanner.nextLine(); // Consume newline

    // Create HashedDictionary and set maximum load factor
    HashedDictionary<String, String> dictionary = new HashedDictionary<>(init_M);
    dictionary.setLoadFactor(alpha);

    // Calculate the number of keys to add to the table
    int N = (int) (alpha * init_M);

    // Variables to keep track of probes
    int maxProbes = 0;
    int totalProbes = 0;

    // Add N/2 random words to the table
    for (int i = 0; i < N / 2; i++) {
      String randomWord = StringHelp.randWord(10);
      int probes = addWord(dictionary, randomWord);
      totalProbes += probes;
      if (probes > maxProbes) {
        maxProbes = probes;
      }
    }

    // Output max and average probes for first N/2 adds
    System.out.println("For first " + N / 2 + " adds:");
    System.out.println("Maximum probes: " + maxProbes);
    System.out.println("Average probes: " + (double) totalProbes / (N / 2));

    // Reset probes for next set of adds
    maxProbes = 0;
    totalProbes = 0;

    // Add next N/2 random words to the table
    for (int i = 0; i < N / 2; i++) {
      String randomWord = StringHelp.randWord(10);
      int probes = addWord(dictionary, randomWord);
      totalProbes += probes;
      if (probes > maxProbes) {
        maxProbes = probes;
      }
    }

    // Output max and average probes for second N/2 adds
    System.out.println("\nFor second " + N / 2 + " adds:");
    System.out.println("Maximum probes: " + maxProbes);
    System.out.println("Average probes: " + (double) totalProbes / (N / 2));

    // Variables to keep track of probes for getValue() calls
    int maxGetValueProbes = 0;
    int totalGetValueProbes = 0;
    int getValueCalls = 1000;

    // Perform 1000 getValue() calls
    for (int i = 0; i < getValueCalls; i++) {
      String randomWord = StringHelp.randWord(10);
      int probes = getValue(dictionary, randomWord);
      totalGetValueProbes += probes;
      if (probes > maxGetValueProbes) {
        maxGetValueProbes = probes;
      }
    }

    // Output max and average probes for getValue() calls
    System.out.println("\nFor " + getValueCalls + " getValue() calls:");
    System.out.println("Maximum probes: " + maxGetValueProbes);
    System.out.println("Average probes: " + (double) totalGetValueProbes / getValueCalls);

    // Close the Scanner
    scanner.close();
  }

  private static int addWord(HashedDictionary<String, String> dictionary, String word) {
    int probes = dictionary.getProbes();
    dictionary.add(word, "");
    return probes;
  }

  private static int getValue(HashedDictionary<String, String> dictionary, String key) {
    int probes = dictionary.getProbes();
    dictionary.getValue(key);
    return probes;
  }
}
