package com.example.dwkyanglao.activity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WarncharModel {

    /**
     * data : {"1":["2023-02-21 09:27:16.344+08","2023-02-21 09:34:15.428+08","2023-02-21 09:35:16.11+08","2023-02-21 10:28:02.78+08","2023-02-21 10:29:03.422+08","2023-02-21 12:58:53.206+08","2023-02-21 13:18:09.246+08","2023-02-21 13:23:15.389+08","2023-02-21 13:38:29.887+08","2023-02-21 13:39:30.591+08","2023-02-21 13:42:29.573+08","2023-02-21 13:43:30.257+08","2023-02-21 13:52:00.037+08","2023-02-21 14:08:03.323+08","2023-02-21 14:09:04.013+08","2023-02-21 14:10:41.875+08","2023-02-21 14:11:42.557+08","2023-02-21 14:21:02.11+08","2023-02-21 14:22:02.82+08"],"2":["2023-02-21 13:18:09.252+08","2023-02-21 13:23:15.395+08","2023-02-21 13:39:07.129+08","2023-02-21 13:42:54.079+08","2023-02-21 13:52:00.043+08"],"3":[],"4":[],"5":[]}
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
        private List<String> _$2;
        @SerializedName("3")
        private List<String> _$3;
        @SerializedName("4")
        private List<String> _$4;
        @SerializedName("5")
        private List<String> _$5;

        public List<String> get_$1() {
            return _$1;
        }

        public void set_$1(List<String> _$1) {
            this._$1 = _$1;
        }

        public List<String> get_$2() {
            return _$2;
        }

        public void set_$2(List<String> _$2) {
            this._$2 = _$2;
        }

        public List<String> get_$3() {
            return _$3;
        }

        public void set_$3(List<String> _$3) {
            this._$3 = _$3;
        }

        public List<String> get_$4() {
            return _$4;
        }

        public void set_$4(List<String> _$4) {
            this._$4 = _$4;
        }

        public List<String> get_$5() {
            return _$5;
        }

        public void set_$5(List<String> _$5) {
            this._$5 = _$5;
        }
    }
}
