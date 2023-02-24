package com.example.dwkyanglao.activity.model;

import java.util.List;

public class YijnXxModel {

    /**
     * data : [{"name":null,"deviceType":10004,"uri":"105230201001","content":"心率低于80","time":"2023-02-20T01:12:22.401348Z","username":"17357800093"},{"name":null,"deviceType":10004,"uri":"105230201001","content":"心率低于80","time":"2023-02-20T01:12:22.401348Z","username":"17357800093"}]
     * code : 0
     * errorMessage : null
     */

    private int code;
    private Object errorMessage;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : null
         * deviceType : 10004
         * uri : 105230201001
         * content : 心率低于80
         * time : 2023-02-20T01:12:22.401348Z
         * username : 17357800093
         */

        private Object name;
        private int deviceType;
        private String uri;
        private String content;
        private String time;
        private String username;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
