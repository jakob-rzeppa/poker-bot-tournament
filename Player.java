import java.util.SortedMap;

import actions.Action;

public class Player {
    private AbstractBot bot;
    private String name;
    private int bankroll;

    private Card[] hand;

    public Player(AbstractBot bot, int initialBankroll) {
        this.bot = bot;
        this.name = bot.getName();
        this.bankroll = initialBankroll;
    }

    public AbstractBot getBot() {
        return bot;
    }

    public String getName() {
        return name;
    }

    public int getBankroll() {
        return bankroll;
    }

    public void play(
        Card[] uncoveredCards, 
        int moneyInPot, 
        SortedMap<Integer, Integer> currentBets
    ) throws IllegalStateException {
        Action action = this.bot.selectAction(
            bankroll,
            hand,
            uncoveredCards,
            moneyInPot,
            currentBets
        );

        // handle the action

        if (bankroll < 0) {
            throw new IllegalStateException("Bankroll cannot be negative");
        }
    }
}
