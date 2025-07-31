import java.util.SortedMap;

import actions.Action;
import deck.Card;

abstract class AbstractBot {
    public abstract String getName();

    public abstract Action selectAction(
        int bankroll,
        Card[] ownCards,
        Card[] uncoveredCards,
        int moneyInPot,
        SortedMap<Integer, Integer> currentBets
    );
}