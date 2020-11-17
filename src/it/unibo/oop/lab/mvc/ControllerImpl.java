package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> stringHistory = new ArrayList<>();
    private String nextString;

    /**
     * {@inheritDoc}
     * @exception IllegalArgumentException if the string is null
     */
    public void setNextString(final String string) {
        if (string != null) {
            this.nextString = string;
        } else {
            throw new IllegalArgumentException("Null String");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getNextString() {
        return this.nextString;
    }

    /**
     * @implNote return a copy of the list
     * 
     * @return {@inheritDoc}
     */
    public List<String> printedStrings() {
        return new ArrayList<>(stringHistory);
    }

    /**
     * {@inheritDoc}
     * @exception IllegalStateException if the next string is unset
     */
    public void printString() {
        if (this.nextString != null) {
            this.stringHistory.add(this.nextString);
            System.out.println(this.nextString);
        } else {
            throw new IllegalStateException("Next String is unset");
        }
    }

}
