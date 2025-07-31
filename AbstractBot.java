import actions.Action;

abstract class AbstractBot {
    public abstract String getName();

    public abstract Action play(
        int ownPosition, 
        int[] playerMoney,
        int moneyInPot, 
        int[] currentBets, 
        Card[] ownCards, 
        Card[] uncoveredCards
    );
}