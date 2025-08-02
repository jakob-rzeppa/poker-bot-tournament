import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Game {
    private static final int SMALL_BLIND_AMOUNT = 10;
    private static final int BIG_BLIND_AMOUNT = 20;

    private SortedMap<Integer, Player> players;

    public Game(int initialMoney) {
        this.players = new TreeMap<>();
    }

    private void playRound(int currentSmallBlindPlayerId) {
        RoundTable roundTable = new RoundTable(players);

        // Deal two cards to each player
        roundTable.dealCardsToPlayers();

        // Collect small blind
        try {
            Player smallBlindPlayer = this.players.get(currentSmallBlindPlayerId);
            smallBlindPlayer.updateBankroll(-SMALL_BLIND_AMOUNT);
            roundTable.changeBet(currentSmallBlindPlayerId, SMALL_BLIND_AMOUNT);
        } catch (Exception e) {
            System.err.println("Small blind player has insufficient funds.");
            return;
        }

        // Collect big blind
        try {
            Entry<Integer, Player> bigBlindEntry = this.players.tailMap(currentSmallBlindPlayerId + 1).firstEntry();
            Player bigBlindPlayer = bigBlindEntry.getValue();
            bigBlindPlayer.updateBankroll(-BIG_BLIND_AMOUNT);
            roundTable.changeBet(bigBlindEntry.getKey(), BIG_BLIND_AMOUNT);
        } catch (Exception e) {
            System.err.println("Big blind player has insufficient funds.");
            return;
        }

        
        System.out.println(roundTable);

        // make Bets starting with the big blind player and ending with the small blind player

        System.out.println("Deal first three uncovered cards");
        System.out.println();
        roundTable.uncoverCards(3);

        // make Bets starting with the small blind player

        System.out.println("Deal fourth uncovered card");
        System.out.println();
        roundTable.uncoverCards(1);

        // make Bets starting with the small blind player

        System.out.println("Deal fifth uncovered card");
        System.out.println();
        roundTable.uncoverCards(1);

        
        System.out.println(roundTable);

        // make Bets starting with the small blind player

        // Collect the pot

        // Cleanup
        System.out.println("Resetting the table for the next round");
        System.out.println();
        roundTable.resetTable();
        
        System.out.println(roundTable);
    }

    public void startGame() {
        // Initialize players and start the first round
        this.players.put(0, new Player(new TestBot(), 1000));
        this.players.put(2, new Player(new TestBot(), 1000));
        this.players.put(3, new Player(new TestBot(), 1000));

        playRound(0);
    }
}
