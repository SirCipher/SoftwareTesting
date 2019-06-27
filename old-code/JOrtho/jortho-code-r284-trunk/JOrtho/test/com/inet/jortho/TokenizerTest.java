package com.inet.jortho;

import junit.framework.TestCase;

import javax.swing.*;

public class TokenizerTest extends TestCase {

    static {
        AllTests.init();
    }

    public void testWebAddress(){
        JEditorPane jText = TestUtils.initDefaultSpellchecker("www.google.com");
        Tokenizer tokenizer = TestUtils.getDefaultTokenizer(jText);

        assertNull("Word should not have been detected", tokenizer.nextInvalidWord());
    }

}
