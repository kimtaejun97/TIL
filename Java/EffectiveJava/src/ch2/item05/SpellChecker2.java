package ch2.item05;

public class SpellChecker2 {
    private final Lexicon dictionary =new KoreanDictionary();

    private SpellChecker2(){}

    public static final SpellChecker2 INSTANCE = new SpellChecker2();

    public boolean isValid(String word){
        // check
        return true;
    }

}
