package com.example.dwkyanglao.activity.model;

public class DeviceXQModel {

    /**
     * code : 0
     * errorMessage : string
     * data : {"id":"3fa85f64-5717-4562-b3fc-2c963f66afa6","name":"string","uri":"string","userId":"3fa85f64-5717-4562-b3fc-2c963f66afa6","deviceType":0,"mac":"string","version":"string","connected":0}
     */

    private int code;
    private String errorMessage;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3fa85f64-5717-4562-b3fc-2c963f66afa6
         * name : string
         * uri : string
         * userId : 3fa85f64-5717-4562-b3fc-2c963f66afa6
         * deviceType : 0
         * mac : string
         * version : string
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
