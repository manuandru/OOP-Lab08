package it.unibo.oop.lab.advanced;

import java.io.PrintStream;

public final class DrawNumberViewPrinter implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    private final PrintStream ps;

    public DrawNumberViewPrinter(final PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        //Empty
    }

    @Override
    public void start() {
        //Empty
    }

    @Override
    public void numberIncorrect() {
        ps.println("Incorrect Number.. try again");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            ps.println("Result: " + res.getDescription());
            return;
        case YOU_WON:
            ps.println("Result: " + res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
    }

    @Override
    public void limitsReached() {
        ps.println("You lost" + NEW_GAME);
    }

    @Override
    public void displayError(final String message) {
        ps.println("Error: " + message);
    }

}
