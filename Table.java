import actions.Action;

public class Table {
    private int smallBlind = 10;
    private int bigBlind = 20;

    private AbstractBot[] players;
    private int[] playerMoney;

    public Table(int initialMoney) {
        players = new AbstractBot[1];
        players[0] = new TestBot();

        playerMoney = new int[players.length];
        for (int i = 0; i < playerMoney.length; i++) {
            playerMoney[i] = initialMoney;
        }
    }

    private void makeBets(int currentSmallBlind, int moneyInPot, int[] currentBets, Card[] uncoveredCards, Card[][] playersCards) {

        for (int i = 0; i < players.length; i++) {
            int playerIndex = (currentSmallBlind + i) % players.length;

            Action action = players[playerIndex].play(
                playerIndex,
                playerMoney.clone(),
                moneyInPot,
                currentBets,
                playersCards[playerIndex],
                uncoveredCards
            );

            System.out.println(players[playerIndex].getName() + " chose action: " + action);
        }
    }

    private void playRound(int currentSmallBlind) {
        int moneyInPot = 0;
        int[] currentBets = new int[players.length];

        Card[][] playersCards = new Card[players.length][2];
        Card[] uncoveredCards = new Card[5];

        // Deal two cards to each player

        // Collect blinds
        playerMoney[currentSmallBlind] -= smallBlind;
        if (playerMoney[currentSmallBlind] < 0) {
            throw new IllegalStateException("Not enough money for small blind");
        }
        currentBets[currentSmallBlind] += smallBlind;

        playerMoney[(currentSmallBlind + 1) % players.length] -= bigBlind;
        if (playerMoney[(currentSmallBlind + 1) % players.length] < 0) {
            throw new IllegalStateException("Not enough money for big blind");
        }
        currentBets[(currentSmallBlind + 1) % players.length] += bigBlind;

        makeBets(currentSmallBlind, moneyInPot, currentBets, uncoveredCards, playersCards);

        // Deal three uncovered cards

        makeBets(currentSmallBlind, moneyInPot, currentBets, uncoveredCards, playersCards);

        // Deal fourth uncovered card

        makeBets(currentSmallBlind, moneyInPot, currentBets, uncoveredCards, playersCards);

        // Deal fifth uncovered card

        makeBets(currentSmallBlind, moneyInPot, currentBets, uncoveredCards, playersCards);

        // Cleanup
    }

    public void startGame() {
        for (int i = 0; i < 10; i++) { // Play 10 rounds
            int currentSmallBlind = i % players.length;
            playRound(currentSmallBlind);
        }

        // Determine winner
    }
}
