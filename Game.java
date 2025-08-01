import java.util.SortedMap;

public class Game {
    private SortedMap<Integer, Player> players;

    public Game(int initialMoney) {
    }

    private void playRound(int currentSmallBlindPlayer) {
        RoundTable roundTable = new RoundTable(players);

        // Deal two cards to each player
        roundTable.dealCardsToPlayers();

        // Collect blinds

        // make Bets starting with the big blind player and ending with the small blind player

        System.out.println("Deal first three uncovered cards");
        roundTable.uncoverCards(3);

        // make Bets starting with the small blind player

        System.out.println("Deal fourth uncovered card");
        roundTable.uncoverCards(1);

        // make Bets starting with the small blind player

        System.out.println("Deal fifth uncovered card");
        roundTable.uncoverCards(1);

        // make Bets starting with the small blind player

        // Collect the pot

        // Cleanup
        System.out.println("Resetting the table for the next round");
        roundTable.resetTable();
    }

    public void startGame() {

    }
}
