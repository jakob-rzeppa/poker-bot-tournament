import java.util.SortedMap;
import java.util.Map.Entry;

public class Game {
    private static final int SMALL_BLIND_AMOUNT = 10;
    private static final int BIG_BLIND_AMOUNT = 20;

    private SortedMap<Integer, Player> players;

    public Game(int initialMoney) {
    }

    private void playRound(int currentSmallBlindPlayerId) {
        RoundTable roundTable = new RoundTable(players);

        // Deal two cards to each player
        roundTable.dealCardsToPlayers();

        // Collect blinds
        try {
            Player smallBlindPlayer = this.players.get(currentSmallBlindPlayerId);
            smallBlindPlayer.updateBankroll(-SMALL_BLIND_AMOUNT);
            roundTable.changeBet(currentSmallBlindPlayerId, SMALL_BLIND_AMOUNT);

            Entry<Integer, Player> bigBlindEntry = this.players.tailMap(currentSmallBlindPlayerId).firstEntry();
            Player bigBlindPlayer = bigBlindEntry.getValue();
            bigBlindPlayer.updateBankroll(-BIG_BLIND_AMOUNT);
            roundTable.changeBet(bigBlindEntry.getKey(), BIG_BLIND_AMOUNT);
        } catch (Exception e) {
            System.err.println("Small blind player has insufficient funds.");
            return;
        }

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
