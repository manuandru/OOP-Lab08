package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final String CONFIG_FILE_PATH = "res/config.yml";
    private static final String FILE_ERROR_STRING = "File not found, starting with default config...";
    private static final String IOERROR = "I/O Error";
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;


    private final DrawNumber model;
    private final DrawNumberView view;

    private int min;
    private int max;
    private int attempts;


    /**
     * 
     */
    public DrawNumberApp() {
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.setConfigFromFile(CONFIG_FILE_PATH);
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
        this.view.start();
    }

    /**
     * Set the min, max, attempts value from file.
     * @param file where read the config
     */
    private void setConfigFromFile(final String file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            this.min = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            this.max = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            this.attempts = Integer.parseInt(st.nextToken());
        } catch (FileNotFoundException e) {
            this.view.displayError(FILE_ERROR_STRING);
            this.min = MIN;
            this.max = MAX;
            this.attempts = ATTEMPTS;
        } catch (IOException e) {
            this.view.displayError(IOERROR);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
