import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import deck.Card;
import deck.Deck;

public class RoundTable {
    private Deck deck;

    private List<Card> uncoveredCards;

    public RoundTable() {
        this.deck = new Deck();
        this.uncoveredCards = new ArrayList<>(5);
    }

    public void dealCardsToPlayers(SortedMap<Integer, Player> players) {
        for (Player player : players.values()) {
            player.receiveCards(deck.drawCard(), deck.drawCard());
        }
    }

    public void uncoverCards(int numberOfCards) throws IllegalStateException {
        if (numberOfCards != 1 && numberOfCards != 3) {
            throw new IllegalArgumentException("Number of uncovered cards must be either 1 or 3");
        }

        for (int i = 0; i < numberOfCards; i++) {
            uncoveredCards.add(deck.drawCard());
        }

        if (uncoveredCards.size() > 5) {
            throw new IllegalStateException("Cannot uncover more than 5 cards");
        }
    }

    public void resetTable(SortedMap<Integer, Player> players) {
        this.deck = new Deck();
        this.uncoveredCards.clear();
        players.forEach((_, player) -> player.clearHand());
    }
}
