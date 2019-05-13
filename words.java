package com.maame.words;

public class words {


    private String correctWord;
    private String wrongWord;


    public words(String newcorrectWord, String newwrongWord) {
        setCorrectWord(newcorrectWord);
        setWrongWord(newwrongWord);

    }

    public static void add(words currentWord) {
    }

    public void setCorrectWord(String newcorrectWord) {
        correctWord = newcorrectWord;
    }

    public void setWrongWord(String newwrongWord) {
        wrongWord = newwrongWord;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public String getWrongWord() {
        return wrongWord;
    }

    public String toString()
    {
        return correctWord + "" + wrongWord;
    }


}
