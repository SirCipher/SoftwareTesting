package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;
import java.util.List;

public class DictionaryBaseTest extends TestCase {

    static {
        AllTests.init();
    }

    /**
     * Tests that the suggestions feature does return suggestions as well as the spelling mistake being replaced
     * by the suggestion
     */
    public void testSearchSuggestions() {
        @SuppressWarnings("SpellCheckingInspection") JEditorPane jText = TestUtils.initDefaultSpellchecker("testt");
        Tokenizer tokenizer = TestUtils.getDefaultTokenizer(jText);

        assertNotNull("No spelling mistake found by tokenizer", tokenizer.nextInvalidWord());

        List<Suggestion> suggestions = SpellChecker.getCurrentDictionary().searchSuggestions(jText.getText());
        assertTrue("No suggestions returned", suggestions.size() > 0);

        String replacement = suggestions.get(0).getWord();
        jText.setText(replacement);

        tokenizer = TestUtils.getDefaultTokenizer(jText);

        assertNull("Editor not updated with replacement value", tokenizer.nextInvalidWord());
    }

    /**
     * Checks that the search suggestions handles null input gracefully
     */
    public void testSearchSuggestionsNullInput() {
        try {
            List<Suggestion> suggestions = SpellChecker.getCurrentDictionary().searchSuggestions(null);
            assertEquals("No results should be returned with a null input", suggestions.size(), 0);
        } catch (NullPointerException e) {
            fail("Null input to searchSuggestions not handled gracefully. Ex: " + e);
        }
    }

}