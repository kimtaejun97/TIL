package ch2.item05;

import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker4 {
    private final Lexicon dictionary;

    public SpellChecker4(Supplier<Lexicon> dictionary) {
        this.dictionary =  Objects.requireNonNull(dictionary.get());
    }
    public boolean isValid(String word){
        //check
        return true;
    }
}
