package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class InputTests extends TestCase {

    static {
        AllTests.init();
    }

    /**
     * Tests that the tokenizer splits on words that have a special character in between them. Expected result is
     * two results from the tokenizer for every string.
     */
    public void testSpecialCharacters() {
        String breaks = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        String leftWord = "llll", rightWord = "rrrr";
        JEditorPane jText = TestUtils.initDefaultSpellchecker("");

        for (char c : breaks.toCharArray()) {
            jText.setText(leftWord + c + rightWord);
            int j = 0;
            Tokenizer tokenizer = TestUtils.getDefaultTokenizer(jText);

            while (tokenizer.nextInvalidWord() != null) {
                j++;
            }

            assertEquals("2 words should be invalid. Character: " + c + " should break a word", 2, j);
        }
    }

    /**
     * Tests that the {@code Tokenizer} returns invalid words when a {@code String} contains numbers within it.
     * However, a {@code String} that starts with a number is acceptable.
     */
    public void testWithNumbers() {
        String testString = "aLongStringToTest";
        JEditorPane jText = TestUtils.initDefaultSpellchecker("");

        for (int i = 0; i < testString.length(); i++) {
            String s = testString.substring(0, i) + i + testString.substring(i + 1);
            jText.setText(s);
            Tokenizer tokenizer = TestUtils.getDefaultTokenizer(jText);

            if (i == 0) {
                assertNull("Spelling mistake detected when there should not have been. " +
                        "Strings starting with numbers are valid", tokenizer.nextInvalidWord());
            } else {
                assertNotNull("Expected invalid string", tokenizer.nextInvalidWord());
            }
        }
    }

}
