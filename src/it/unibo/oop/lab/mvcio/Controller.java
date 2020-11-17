package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    private static final String FILE_NAME_STRING = "output.txt";
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     */

    //I could use 3 separate const
    private File file = new File(System.getProperty("user.home") 
            + File.separator + FILE_NAME_STRING);

     /* 1) A method for setting a File as current file */

    /**
     * 
     * @param file to use in Controller
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /* 2) A method for getting the current File */

    /**
     * 
     * @return file used in Controller
     */
    public File getFile() {
        return this.file;
    }

    /* 3) A method for getting the path (in form of String) of the current File */

    /**
     * 
     * @return the file path
     */
    public String getPathFile() {
        return this.file.getPath();
    }

    /* 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     */ 

    /**
     * 
     * @param string to be saved
     * @throws IOException
     */
    public void saveString(final String string) throws IOException {
        try (PrintStream ps = new PrintStream(this.file)) {
            ps.print(string);
        }
    }

    /* 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */



}
