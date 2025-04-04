package seedu.medinote.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void printHelpTable_printsCorrectFormat() {
        Ui.printHelpTable();
        String output = outContent.toString();
        assertTrue(output.contains("Keyword"));
        assertTrue(output.contains("Action"));
        assertTrue(output.contains("help"));
        assertTrue(output.contains("register"));
        assertTrue(output.contains("exit"));
        assertTrue(output.contains(Ui.UI_LINE_BREAK));
    }

}
