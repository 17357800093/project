package com.example.dwkyanglao.activity.model;

public class MqttMsgModel {

    /**
     * time : 1676867384260
     * heart : 0
     * breath : 0
     * move : 0
     * state : 3
     */

    private long time;
    private int heart;
    private int breath;
    private int move;
    private int state;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getBreath() {
        return breath;
    }

    public void setBreath(int breath) {
        this.breath = breath;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
