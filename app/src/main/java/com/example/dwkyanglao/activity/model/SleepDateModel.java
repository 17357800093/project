package com.example.dwkyanglao.activity.model;

import java.util.List;

public class SleepDateModel {

    /**
     * data : ["02/22/2023"]
     * code : 0
     * errorMessage : null
     */

    private int code;
    private Object errorMessage;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
