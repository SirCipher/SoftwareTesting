package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

public class HighlighterTest extends TestCase {

    static {
        AllTests.init();
    }

    /**
     * Tests that the highlighter updates correctly after inserting new paragraphs. Given five paragraphs, insert
     * a new one and check that all the hightlights are still displaying correctly.
     */
    public void testParagraphs() {
        String testString = "aa\naaa\naaaa\naaaaa\n\naaaaaa";
        JEditorPane jText = TestUtils.initDefaultSpellchecker(testString);

        try {
            // Enough time for the UI to have initialised and the hightlights to have appeared
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Document doc = jText.getDocument();
        assertNotNull("Couldn't get document from JEditorPane", doc);

        Highlighter highlighter = jText.getHighlighter();
        assertEquals("Expected four highlights", 4, highlighter.getHighlights().length);

        try {
            doc.insertString(testString.indexOf("\n"), "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        highlighter = jText.getHighlighter();

        assertNotNull("No highlights returned", highlighter.getHighlights());
        assertEquals("Expected four highlights", 4, highlighter.getHighlights().length);
    }

}
