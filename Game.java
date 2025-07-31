public class Game {
    private int smallBlind = 10;
    private int bigBlind = 20;

    public Game(int initialMoney) {
    }

    private void playRound(int currentSmallBlindPlayer) {
        // Deal two cards to each player

        // Collect blinds

        // make Bets starting with the big blind player and ending with the small blind player

       // Deal first three uncovered cards

        // make Bets starting with the small blind player

        // Deal fourth uncovered card

        // make Bets starting with the small blind player

        // Deal fifth uncovered card

        // make Bets starting with the small blind player

        // Cleanup
    }

    public void startGame() {
        for (int i = 0; i < 10; i++) { // Play 10 rounds
            int currentSmallBlind = i % playerManager.getNumberOfPlayers();
            playRound(currentSmallBlind);
        }

        // Determine winner
    }
}
