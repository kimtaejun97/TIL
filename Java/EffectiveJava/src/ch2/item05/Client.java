package ch2.item05;

import java.util.function.Supplier;

public class Client {
    public static void main(String[] args) {
        Lexicon lexicon = new KoreanDictionary();
        SpellChecker4 spellChecker4 = new SpellChecker4(new Supplier<Lexicon>() {
            @Override
            public Lexicon get() {
                return lexicon;
            }
        });
    }
}
