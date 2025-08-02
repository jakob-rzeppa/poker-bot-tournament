import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import deck.Card;
import deck.Deck;

public class RoundTable {
    private static final int MAX_UNCOVERED_CARDS = 5;
    private static final int[] ALLOWED_NUMBER_OF_CARDS_TO_UNCOVER_AT_ONCE = { 1, 3 };
    
    private Deck deck;
    private SortedMap<Integer, Player> players;
    
    private int moneyInPot;
    private SortedMap<Integer, Integer> currentBets;

    private List<Card> uncoveredCards;

    public RoundTable(SortedMap<Integer, Player> players) {
        this.deck = new Deck();
        this.players = players;

        this.moneyInPot = 0;
        this.currentBets = new TreeMap<>();
        for (Integer playerId : players.keySet()) {
            this.currentBets.put(playerId, 0);
        }

        this.uncoveredCards = new ArrayList<>(5);
    }

    public void dealCardsToPlayers() {
        for (Player player : this.players.values()) {
            player.receiveCards(deck.drawCard(), deck.drawCard());
        }
    }

    public void uncoverCards(int numberOfCards) throws IllegalStateException {
        if (!java.util.Arrays.stream(ALLOWED_NUMBER_OF_CARDS_TO_UNCOVER_AT_ONCE).anyMatch(n -> n == numberOfCards)) {
            throw new IllegalArgumentException("Number of uncovered cards must be in " + java.util.Arrays.toString(ALLOWED_NUMBER_OF_CARDS_TO_UNCOVER_AT_ONCE));
        }

        for (int i = 0; i < numberOfCards; i++) {
            uncoveredCards.add(deck.drawCard());
        }

        if (uncoveredCards.size() > MAX_UNCOVERED_CARDS) {
            throw new IllegalStateException("Cannot uncover more than " + MAX_UNCOVERED_CARDS + " cards");
        }
    }

    public void changeBet(int playerId, int amount) throws IllegalStateException {
        if (!currentBets.containsKey(playerId)) {
            throw new IllegalStateException("Player with ID " + playerId + " does not exist");
        }

        int currentBet = currentBets.get(playerId);
        currentBets.put(playerId, currentBet + amount);
    }

    public void resetTable() {
        this.deck = new Deck();
        this.uncoveredCards.clear();
        this.players.forEach((_, player) -> player.clearHand());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Money in pot: ").append(moneyInPot).append("; ");
        sb.append("Current bets: ").append(currentBets).append("; ");
        sb.append("Uncovered cards: ").append(uncoveredCards).append("\n");
        for (Integer playerId : players.keySet()) {
            sb.append(players.get(playerId).getName())
              .append(" - Bet: ").append(currentBets.get(playerId))
              .append(" - Hand: ").append(java.util.Arrays.toString(players.get(playerId).getHand()))
              .append("\n");
        }
        sb.append("\n");
        return sb.toString();
    
    }
}
