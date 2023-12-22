package com.hciproject.onehandedoldkeyboardwithfeature;

public class Time {

    // For measuring time in Runnable
    private long startMeasure = 0;
    private long stopMeasure = 0;
    private long prevTimeDifference = -1;
    private long timeDifference = 0;

    // For measuring time in 
    private long currentTimeABC = 0;
    private long currentTimeDEF = 0;
    private long lastKeyPressTimeABC = 0;
    private long lastKeyPressTimeDEF = 0;
    private long elapsedTimeABC = 0;
    private long elapsedTimeDEF = 0;

    public long getPrevTimeDifference() {
        return prevTimeDifference;
    }

    public void setPrevTimeDifference(long prevTimeDifference) {
        this.prevTimeDifference = prevTimeDifference;
    }




    public long getCurrentTimeABC() {
        return currentTimeABC;
    }

    public void setCurrentTimeABC(long currentTime) {
        this.currentTimeABC = currentTime;
    }

    public long getElapsedTimeABC() {
        return currentTimeABC - lastKeyPressTimeABC;
    }

    public void setElapsedTimeABC(long elapsedTime) {
        this.elapsedTimeABC = elapsedTime;
    }

    public long getLastKeyPressTimeABC() {
        return lastKeyPressTimeABC;
    }

    public void setLastKeyPressTimeABC(long lastKeyPressTime) {
        this.lastKeyPressTimeABC = lastKeyPressTime;
    }

    public long getCurrentTimeDEF() {
        return currentTimeDEF;
    }

    public void setCurrentTimeDEF(long currentTimeDEF) {
        this.currentTimeDEF = currentTimeDEF;
    }

    public long getLastKeyPressTimeDEF() {
        return lastKeyPressTimeDEF;
    }

    public void setLastKeyPressTimeDEF(long lastKeyPressTimeDEF) {
        this.lastKeyPressTimeDEF = lastKeyPressTimeDEF;
    }

    public long getElapsedTimeDEF() {
        return elapsedTimeDEF;
    }

    public void setElapsedTimeDEF(long elapsedTimeDEF) {
        this.elapsedTimeDEF = elapsedTimeDEF;
    }






    public long getStartMeasure() {
        return startMeasure;
    }

    public void setStartMeasure(long startMeasure) {
        this.startMeasure = startMeasure;
    }

    public long getStopMeasure() {
        return stopMeasure;
    }

    public void setStopMeasure(long stopMeasure) {
        this.stopMeasure = stopMeasure;
    }

    public long getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(long timeDifference) {
        this.timeDifference = timeDifference;
    }
}
