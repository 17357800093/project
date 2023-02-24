package com.example.dwkyanglao.activity.model;

import java.util.List;

public class GonxiangModel {

    /**
     * data : [{"id":"f78ee928-991c-4f2f-8c73-5a0a48a678d0","userName":"15967317198"}]
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
         * id : f78ee928-991c-4f2f-8c73-5a0a48a678d0
         * userName : 15967317198
         */

        private String id;
        private String userName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
