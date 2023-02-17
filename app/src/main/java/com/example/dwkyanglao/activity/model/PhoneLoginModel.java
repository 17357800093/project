package com.example.dwkyanglao.activity.model;

public class PhoneLoginModel {

    /**
     * data : {"id":"7e8e7228-d69c-4064-8d71-c5e224a81d3f","username":"17357800093","displayName":null,"birthDay":null,"sex":null,"height":null,"weight":null,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImV4cCI6MTcwNzk2MjQ2NiwiaXNzIjoiYmV3YXRlYyIsImF1ZCI6ImJld2F0ZWMifQ.QxacstGkTAIQ3bkCpbDLRZamDlA1cy6bfHeIpC9C4kw","mqttToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImFjbCI6e30sImV4cCI6MTcwNzk2MjQ2Nn0.YT-E4sBu8MocK7TpTDH8O6LY_B-92R-ax5zEz3aq9eM","exp":"2024-02-15T02:01:06.7367674+00:00"}
     * code : 0
     * errorMessage : null
     */

    private DataBean data;
    private int code;
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class DataBean {
        /**
         * id : 7e8e7228-d69c-4064-8d71-c5e224a81d3f
         * username : 17357800093
         * displayName : null
         * birthDay : null
         * sex : null
         * height : null
         * weight : null
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImV4cCI6MTcwNzk2MjQ2NiwiaXNzIjoiYmV3YXRlYyIsImF1ZCI6ImJld2F0ZWMifQ.QxacstGkTAIQ3bkCpbDLRZamDlA1cy6bfHeIpC9C4kw
         * mqttToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImFjbCI6e30sImV4cCI6MTcwNzk2MjQ2Nn0.YT-E4sBu8MocK7TpTDH8O6LY_B-92R-ax5zEz3aq9eM
         * exp : 2024-02-15T02:01:06.7367674+00:00
         */

        private String id;
        private String username;
        private Object displayName;
        private Object birthDay;
        private Object sex;
        private Object height;
        private Object weight;
        private String token;
        private String mqttToken;
        private String exp;

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

        public Object getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(Object birthDay) {
            this.birthDay = birthDay;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getHeight() {
            return height;
        }

        public void setHeight(Object height) {
            this.height = height;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMqttToken() {
            return mqttToken;
        }

        public void setMqttToken(String mqttToken) {
            this.mqttToken = mqttToken;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }
    }
}
