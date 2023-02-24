package com.example.dwkyanglao.activity.model;

public class SmrbModel {

    /**
     * data : {"score":99,"efficiency":99,"rollOver":0,"maxHeart":90,"minHeart":65,"maxBreath":27,"minBreath":17,"sleepTime":"2023-02-21T13:51:15.474Z","wakeUpTime":"2023-02-22T01:48:15.474Z","heartAvg":77,"breathAvg":21,"sleepSpan":717}
     * code : 0
     * errorMessage : null
     */

    private DataBean data;
    private int code;
    private Object errorMessage;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class DataBean {
        /**
         * score : 99
         * efficiency : 99
         * rollOver : 0
         * maxHeart : 90
         * minHeart : 65
         * maxBreath : 27
         * minBreath : 17
         * sleepTime : 2023-02-21T13:51:15.474Z
         * wakeUpTime : 2023-02-22T01:48:15.474Z
         * heartAvg : 77
         * breathAvg : 21
         * sleepSpan : 717
         */

        private int score;
        private int efficiency;
        private int rollOver;
        private int maxHeart;
        private int minHeart;
        private int maxBreath;
        private int minBreath;
        private String sleepTime;
        private String wakeUpTime;
        private int heartAvg;
        private int breathAvg;
        private int sleepSpan;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getEfficiency() {
            return efficiency;
        }

        public void setEfficiency(int efficiency) {
            this.efficiency = efficiency;
        }

        public int getRollOver() {
            return rollOver;
        }

        public void setRollOver(int rollOver) {
            this.rollOver = rollOver;
        }

        public int getMaxHeart() {
            return maxHeart;
        }

        public void setMaxHeart(int maxHeart) {
            this.maxHeart = maxHeart;
        }

        public int getMinHeart() {
            return minHeart;
        }

        public void setMinHeart(int minHeart) {
            this.minHeart = minHeart;
        }

        public int getMaxBreath() {
            return maxBreath;
        }

        public void setMaxBreath(int maxBreath) {
            this.maxBreath = maxBreath;
        }

        public int getMinBreath() {
            return minBreath;
        }

        public void setMinBreath(int minBreath) {
            this.minBreath = minBreath;
        }

        public String getSleepTime() {
            return sleepTime;
        }

        public void setSleepTime(String sleepTime) {
            this.sleepTime = sleepTime;
        }

        public String getWakeUpTime() {
            return wakeUpTime;
        }

        public void setWakeUpTime(String wakeUpTime) {
            this.wakeUpTime = wakeUpTime;
        }

        public int getHeartAvg() {
            return heartAvg;
        }

        public void setHeartAvg(int heartAvg) {
            this.heartAvg = heartAvg;
        }

        public int getBreathAvg() {
            return breathAvg;
        }

        public void setBreathAvg(int breathAvg) {
            this.breathAvg = breathAvg;
        }

        public int getSleepSpan() {
            return sleepSpan;
        }

        public void setSleepSpan(int sleepSpan) {
            this.sleepSpan = sleepSpan;
        }
    }
}
