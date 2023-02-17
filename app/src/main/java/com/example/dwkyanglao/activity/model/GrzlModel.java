package com.example.dwkyanglao.activity.model;

public class GrzlModel {

    /**
     * data : {"id":"7e8e7228-d69c-4064-8d71-c5e224a81d3f","username":"17357800093","displayName":null,"birthDay":"1995-06-19","sex":0,"height":177,"weight":60}
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
         * id : 7e8e7228-d69c-4064-8d71-c5e224a81d3f
         * username : 17357800093
         * displayName : null
         * birthDay : 1995-06-19
         * sex : 0
         * height : 177
         * weight : 60
         */

        private String id;
        private String username;
        private Object displayName;
        private String birthDay;
        private int sex;
        private int height;
        private int weight;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getDisplayName() {
            return displayName;
        }

        public void setDisplayName(Object displayName) {
            this.displayName = displayName;
        }

        public String getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
