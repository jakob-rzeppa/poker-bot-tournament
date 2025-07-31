import actions.Action;
import actions.BetOrCheck;

public class TestBot extends AbstractBot {
    @Override
    public String getName() {
        return "TestBot";
    }

    @Override
    public Action play(
        int ownPosition,
        int[] playerMoney,
        int moneyInPot,
        int[] currentBets,
        Card[] ownCards,
        Card[] uncoveredCards
    ) {
        return new BetOrCheck();
    }
}
