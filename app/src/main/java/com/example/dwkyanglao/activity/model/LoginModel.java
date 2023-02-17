package com.example.dwkyanglao.activity.model;

public class LoginModel {

    /**
     * data : {"id":"7e8e7228-d69c-4064-8d71-c5e224a81d3f","username":"17357800093","displayName":null,"birthDay":"1995-06-19","sex":1,"height":178,"weight":61,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImV4cCI6MTcwODA1Mjk2MSwiaXNzIjoiYmV3YXRlYyIsImF1ZCI6ImJld2F0ZWMifQ.DBFC49CPyRs-hKpV6aQaEhlMquGeLAKmeaLTCX2BA1E","mqttToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImFjbCI6e30sImV4cCI6MTcwODA1Mjk2MX0.uVe_AzEO2561BYUi4vz_bPqtxnoX-3MOHuaIPLBWb2s","exp":"2024-02-16T03:09:21.9618575+00:00"}
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
         * birthDay : 1995-06-19
         * sex : 1
         * height : 178
         * weight : 61
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImV4cCI6MTcwODA1Mjk2MSwiaXNzIjoiYmV3YXRlYyIsImF1ZCI6ImJld2F0ZWMifQ.DBFC49CPyRs-hKpV6aQaEhlMquGeLAKmeaLTCX2BA1E
         * mqttToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjdlOGU3MjI4LWQ2OWMtNDA2NC04ZDcxLWM1ZTIyNGE4MWQzZiIsImFjbCI6e30sImV4cCI6MTcwODA1Mjk2MX0.uVe_AzEO2561BYUi4vz_bPqtxnoX-3MOHuaIPLBWb2s
         * exp : 2024-02-16T03:09:21.9618575+00:00
         */

        private String id;
        private String username;
        private String displayName;
        private String birthDay;
        private int sex;
        private int height;
        private int weight;
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

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
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
