import java.util.SortedMap;

import actions.Action;
import actions.BetOrCheck;
import deck.Card;

public class TestBot extends AbstractBot {
    @Override
    public String getName() {
        return "TestBot";
    }

    @Override
    public Action selectAction(
        int bankroll,
        Card[] ownCards,
        Card[] uncoveredCards,
        int moneyInPot,
        SortedMap<Integer, Integer> currentBets
    ) {
        return new BetOrCheck();
    }
}
