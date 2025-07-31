import actions.Action;
import actions.Check;

public class TestBot extends AbstractBot {
    @Override
    Action play(
        int ownPosition,
        int[] playerMoney,
        int moneyInPot,
        int[] currentBets,
        Card[] ownCards,
        Card[] uncoveredCards
    ) {
        return new Check();
    }
}
