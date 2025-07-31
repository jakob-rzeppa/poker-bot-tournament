import actions.Action;

abstract class AbstractBot {
    abstract Action play(
        int ownPosition, 
        int[] playerMoney,
        boolean isBigBlind,
        boolean isSmallBlind,
        int moneyInPot, 
        int[] currentBets, 
        Card[] ownCards, 
        Card[] uncoveredCards
    );
}