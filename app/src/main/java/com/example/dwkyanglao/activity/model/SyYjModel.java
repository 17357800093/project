package com.example.dwkyanglao.activity.model;

import java.util.List;

public class SyYjModel {

    /**
     * data : [{"tagType":1,"count":0},{"tagType":2,"count":0},{"tagType":3,"count":0},{"tagType":4,"count":0},{"tagType":5,"count":0}]
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
         * tagType : 1
         * count : 0
         */

        private int tagType;
        private int count;

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
