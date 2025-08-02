import java.util.SortedMap;

import actions.Action;
import actions.Fold;
import deck.Card;

public class Player {
    private static final int NUMBER_OF_CARDS_PER_PLAYER = 2;

    private AbstractBot bot;
    private String name;
    private int bankroll;

    private Card[] hand;

    public Player(AbstractBot bot, int initialBankroll) {
        this.bot = bot;
        this.name = bot.getName();
        this.bankroll = initialBankroll;
        this.hand = new Card[NUMBER_OF_CARDS_PER_PLAYER];
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

    public Card[] getHand() {
        return hand;
    }

    public void receiveCards(Card... cards) {
        if (cards.length != NUMBER_OF_CARDS_PER_PLAYER) {
            throw new IllegalArgumentException("Player must receive exactly " + NUMBER_OF_CARDS_PER_PLAYER + " cards");
        }
        this.hand = cards;
    }

    public void clearHand() {
        this.hand = new Card[NUMBER_OF_CARDS_PER_PLAYER];
    }

    public void updateBankroll(int amount) throws IllegalStateException {
        this.bankroll += amount;

        if (this.bankroll < 0) {
            throw new IllegalStateException("Bankroll cannot be negative");
        }
    }

    public Action play(
        Card[] uncoveredCards,
        int moneyInPot,
        SortedMap<Integer, Integer> currentBets
    ) throws IllegalStateException {
        try {
            Action action = this.bot.selectAction(
                bankroll,
                hand,
                uncoveredCards,
                moneyInPot,
                currentBets
            );

            if (action == null) {
                throw new IllegalStateException("Bot did not return a valid action");
            }

            return action;
        } catch (Exception e) {
            System.err.println(this.bot.getName() + ": Error during bot action selection: " + e.getMessage());
            return new Fold();
        }
    }
}
