package com.example.dwkyanglao.activity.model;

import java.util.List;

public class DevicesModel {

    /**
     * data : [{"id":"08561dc4-d79a-4c57-bdbd-34ad605274d8","name":null,"uri":"111","userId":"7e8e7228-d69c-4064-8d71-c5e224a81d3f","deviceType":10004,"mac":null,"version":null,"connected":0},{"id":"7f2270f5-7676-4ade-8d6e-8faf30d8d9dd","name":null,"uri":"112","userId":"7e8e7228-d69c-4064-8d71-c5e224a81d3f","deviceType":10004,"mac":null,"version":null,"connected":0}]
     * code : 0
     * errorMessage : null
     */

    private int code;
    private String errorMessage;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 08561dc4-d79a-4c57-bdbd-34ad605274d8
         * name : null
         * uri : 111
         * userId : 7e8e7228-d69c-4064-8d71-c5e224a81d3f
         * deviceType : 10004
         * mac : null
         * version : null
         * connected : 0
         */

        private String id;
        private String name;
        private String uri;
        private String userId;
        private int deviceType;
        private String mac;
        private String version;
        private int connected;
        private boolean ischeck;

        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getConnected() {
            return connected;
        }

        public void setConnected(int connected) {
            this.connected = connected;
        }
    }
}
