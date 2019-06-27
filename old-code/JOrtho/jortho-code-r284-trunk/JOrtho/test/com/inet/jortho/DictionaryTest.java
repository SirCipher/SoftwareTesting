package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class DictionaryTest extends TestCase {

    static {
        AllTests.init();
    }

    /**
     * Tests that the dictionary adds long words correctly. This is also used to investigate the maximum length,
     * if any, of a {@code String} that the dictionary will accept.
     */
    public void testLongAdd() {
        StringBuilder sb = new StringBuilder("aLongStringToTest");

        for (int i = 0; i < 10; i++) {
            sb.append("aLongStringToTest");
        }

        String str = sb.toString();
        JEditorPane jText = TestUtils.initDefaultSpellchecker(str);

        Tokenizer tokenizer = TestUtils.getDefaultTokenizer(jText);

        assertNotNull("Invalid word not detected", tokenizer.nextInvalidWord());

        Dictionary dictionary = SpellChecker.getCurrentDictionary();
        dictionary.add(str);
        dictionary.trimToSize();
        AutoSpellChecker.refresh(jText);

        assertTrue("Word not added to dictionary", dictionary.exist(str));

        tokenizer = TestUtils.getDefaultTokenizer(jText);
        assertNull("Incorrect spelling detected by tokenizer", tokenizer.nextInvalidWord());
    }

}
