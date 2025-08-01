import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import deck.Card;
import deck.Deck;

public class RoundTable {
    private static final int MAX_UNCOVERED_CARDS = 5;
    private static final int[] ALLOWED_NUMBER_OF_CARDS_TO_UNCOVER_AT_ONCE = { 1, 3 };
    
    private Deck deck;
    private SortedMap<Integer, Player> players;
    

    private List<Card> uncoveredCards;

    public RoundTable(SortedMap<Integer, Player> players) {
        this.deck = new Deck();
        this.uncoveredCards = new ArrayList<>(5);
        this.players = players;
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

    public void resetTable() {
        this.deck = new Deck();
        this.uncoveredCards.clear();
        this.players.forEach((_, player) -> player.clearHand());
    }
}
