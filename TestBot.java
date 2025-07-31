import actions.Action;
import actions.Check;

public class TestBot extends AbstractBot {
    @Override
    Action play(
        int ownPosition,
        int[] playerMoney,
        boolean isBigBlind,
        boolean isSmallBlind,
        int moneyInPot,
        int[] currentBets,
        Card[] ownCards,
        Card[] uncoveredCards
    ) {
        return new Check();
    }
}
