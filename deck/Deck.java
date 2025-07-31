package deck;

import java.util.HashSet;
import java.util.Set;

public class Deck {
    private Set<Card> cards;

    public Deck() {
        this.cards = new HashSet<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck");
        }

        return cards.iterator().next();
    }
}
