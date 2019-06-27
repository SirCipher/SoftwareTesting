package com.inet.jortho;

import javax.swing.*;
import java.util.Locale;

class TestUtils {

    static JEditorPane initDefaultSpellchecker(final String str) {
        JEditorPane jText = new JTextPane();
        SpellChecker.register(jText);
        SpellChecker.enableAutoSpell(jText, true);
        jText.setText(str);

        return jText;
    }

    static Tokenizer getDefaultTokenizer(JEditorPane jTextPane) {
        return new Tokenizer(jTextPane, SpellChecker.getCurrentDictionary(), Locale.UK, SpellChecker.getOptions());
    }

}
