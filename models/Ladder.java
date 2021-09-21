package com.snakeandladder;

public class Ladder {
    //start point should be smaller then end point

    private int start;
    private int end;

    public Ladder(){

    }
    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
