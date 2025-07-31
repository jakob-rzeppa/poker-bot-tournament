import actions.Action;

abstract class AbstractBot {
    abstract Action play(
        int ownPosition, 
        int[] playerMoney, 
        int moneyInPot, 
        int[] currentBets, 
        String[] ownCards, 
        String[] currentCards
    );
}