package ch2.item05;

import java.util.Objects;

public class SpellChecker3 {
    private final Lexicon dictionary;

    public SpellChecker3(Lexicon dictionary) {
        this.dictionary =  Objects.requireNonNull(dictionary);
    }
    public boolean isValid(String word){
        //check
        return true;
    }
}
