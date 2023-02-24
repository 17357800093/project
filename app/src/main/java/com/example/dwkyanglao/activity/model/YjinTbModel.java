package com.example.dwkyanglao.activity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YjinTbModel {

    /**
     * data : {"1":["2023-02-20 09:22:52.746+08","2023-02-20 09:23:53.376+08"],"2":[],"3":[],"4":[],"5":[]}
     * code : 0
     * errorMessage : null
     */

    private DataBean data;
    private int code;
    private Object errorMessage;

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

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class DataBean {
        @SerializedName("1")
        private List<String> _$1;
        @SerializedName("2")
        private List<?> _$2;
        @SerializedName("3")
        private List<?> _$3;
        @SerializedName("4")
        private List<?> _$4;
        @SerializedName("5")
        private List<?> _$5;

        public List<String> get_$1() {
            return _$1;
        }

        public void set_$1(List<String> _$1) {
            this._$1 = _$1;
        }

        public List<?> get_$2() {
            return _$2;
        }

        public void set_$2(List<?> _$2) {
            this._$2 = _$2;
        }

        public List<?> get_$3() {
            return _$3;
        }

        public void set_$3(List<?> _$3) {
            this._$3 = _$3;
        }

        public List<?> get_$4() {
            return _$4;
        }

        public void set_$4(List<?> _$4) {
            this._$4 = _$4;
        }

        public List<?> get_$5() {
            return _$5;
        }

        public void set_$5(List<?> _$5) {
            this._$5 = _$5;
        }
    }
}
