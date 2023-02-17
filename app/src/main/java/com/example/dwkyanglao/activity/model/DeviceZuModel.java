package com.example.dwkyanglao.activity.model;

import java.util.List;

public class DeviceZuModel {

    /**
     * data : [{"id":"80a20c18-6809-4483-bd97-9d094bb08381","name":"我的健康","type":1}]
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
         * id : 80a20c18-6809-4483-bd97-9d094bb08381
         * name : 我的健康
         * type : 1
         */

        private String id;
        private String name;
        private int type;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
