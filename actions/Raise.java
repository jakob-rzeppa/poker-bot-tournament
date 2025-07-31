package actions;

public class Raise extends Action {
    private int amount;

    public Raise(int by) {
        amount = by;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Raise by " + amount;
    }
}
