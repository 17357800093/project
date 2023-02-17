package com.example.dwkyanglao.activity.model;

public class CodeMsgModel {

    /**
     * code : -1
     * errorMessage : 短信发送间隔1分钟，请勿重复发送
     */

    private int code;
    private String errorMessage;

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
}
