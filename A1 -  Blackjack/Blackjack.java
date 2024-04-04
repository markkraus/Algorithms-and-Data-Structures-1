import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            RandIndexQueue<String> deck = new RandIndexQueue<>(52);
            initializeDeck(deck);

            RandIndexQueue<String> playerHand = new RandIndexQueue<>(10);
            RandIndexQueue<String> dealerHand = new RandIndexQueue<>(10);

            dealInitialCards(deck, playerHand, dealerHand);

            // Player's turn
            while (true) {
                System.out.println("Your hand: " + playerHand.toString());
                System.out.println("Your score: " + calculateScore(playerHand));
                if (isBust(calculateScore(playerHand))) {
                    System.out.println("Bust! You lose.");
                    playAgain = askToPlayAgain(scanner);
                    break;
                }
                System.out.println("Do you want to hit or stand? (Enter 'hit' or 'stand')");
                String choice = scanner.nextLine();
                if (choice.equals("hit")) {
                    hit(deck, playerHand);
                } else if (choice.equals("stand")) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'hit' or 'stand'.");
                }
            }

            if (playAgain) {
                // Dealer's turn
                while (calculateScore(dealerHand) < 17) {
                    hit(deck, dealerHand);
                }

                // Determine the winner
                int playerScore = calculateScore(playerHand);
                int dealerScore = calculateScore(dealerHand);
                System.out.println("Player's hand: " + playerHand.toString());
                System.out.println("Player's score: " + playerScore);
                System.out.println("Dealer's hand: " + dealerHand.toString());
                System.out.println("Dealer's score: " + dealerScore);

                if (isBust(playerScore)) {
                    System.out.println("Dealer wins!");
                } else if (isBust(dealerScore)) {
                    System.out.println("Player wins!");
                } else if (playerScore == dealerScore) {
                    System.out.println("It's a tie!");
                } else if (playerScore > dealerScore) {
                    System.out.println("Player wins!");
                } else {
                    System.out.println("Dealer wins!");
                }
                playAgain = askToPlayAgain(scanner);
            }
        }
    }

    private static void initializeDeck(RandIndexQueue<String> deck) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.enqueue(rank + " of " + suit);
            }
        }
        deck.shuffle();
    }

    private static void dealInitialCards(RandIndexQueue<String> deck, RandIndexQueue<String> playerHand, RandIndexQueue<String> dealerHand) {
        hit(deck, playerHand);
        hit(deck, dealerHand);
        hit(deck, playerHand);
        hit(deck, dealerHand);
    }

    private static void hit(RandIndexQueue<String> deck, RandIndexQueue<String> hand) {
        hand.enqueue(deck.dequeue());
    }

    private static int calculateScore(RandIndexQueue<String> hand) {
        int score = 0;
        int numAces = 0;
        for (int i = 0; i < hand.size(); i++) {
            String card = hand.get(i);
            if (card.contains("Ace")) {
                numAces++;
                score += 11;
            } else if (card.contains("King") || card.contains("Queen") || card.contains("Jack")) {
                score += 10;
            } else {
                score += Integer.parseInt(card.split(" ")[0]);
            }
        }
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }

    private static boolean isBust(int score) {
        return score > 21;
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.println("Do you want to play another round? (Enter 'yes' or 'no')");
        String choice = scanner.nextLine().toLowerCase();
        return choice.equals("yes");
    }
}
