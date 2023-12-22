package com.hciproject.onehandedoldkeyboardwithfeature;

public class Letter {

    private boolean click = false;
    private int letterIndex = -1;
    private String letterForPrint = "";
    private String prevLetterForPrint = "";
    private int currentClick = -1;
    private int previousClick = -1;
    private boolean otherClick = false;

    public boolean isOtherClick() {
        return otherClick;
    }

    public void setOtherClick(boolean otherClick) {
        this.otherClick = otherClick;
    }

    public int getPreviousClick() {
        return previousClick;
    }

    public void setPreviousClick(int previousClick) {
        this.previousClick = previousClick;
    }

    public String getLetterForPrint() {
        return letterForPrint;
    }

    public void setLetterForPrint(String letterForPrint) {
        this.letterForPrint = letterForPrint;
    }

    public int getLetterIndex() {
        return letterIndex;
    }

    public void setLetterIndex(int letterIndex) {
        this.letterIndex = letterIndex;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public int getCurrentClick() {
        return currentClick;
    }

    public void setCurrentClick(int currentClick) {
        this.currentClick = currentClick;
    }

    public String getPrevLetterForPrint() {
        return prevLetterForPrint;
    }

    public void setPrevLetterForPrint(String prevLetterForPrint) {
        this.prevLetterForPrint = prevLetterForPrint;
    }
}
