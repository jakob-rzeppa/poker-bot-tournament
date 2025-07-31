import java.util.SortedMap;

import actions.Action;

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