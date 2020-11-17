package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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
    private static final String OUTPUT_FILE_NAME = "output.txt";
    private final File file = new File(System.getProperty("user.home") 
            + File.separator + OUTPUT_FILE_NAME);
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;


    private final DrawNumber model;
    private final List<DrawNumberView> views;

    private int min;
    private int max;
    private int attempts;


    /**
     * 
     */
    public DrawNumberApp() {
        this.views = new ArrayList<>();
        this.views.add(new DrawNumberViewImpl());
        this.views.add(new DrawNumberViewImpl());
        this.views.add(new DrawNumberViewPrinter(System.out));
        try {
            this.views.add(new DrawNumberViewPrinter(new PrintStream(this.file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (final DrawNumberView view : this.views) {
            view.setObserver(this);
            view.start();
        }
        this.setConfigFromFile(CONFIG_FILE_PATH);
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
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
            for (final DrawNumberView view : this.views) {
                view.displayError(FILE_ERROR_STRING);
            }
            this.min = MIN;
            this.max = MAX;
            this.attempts = ATTEMPTS;
        } catch (IOException e) {
            for (final DrawNumberView view : this.views) {
                view.displayError(IOERROR);
            }
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : this.views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : this.views) {
                view.limitsReached();
            }
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
