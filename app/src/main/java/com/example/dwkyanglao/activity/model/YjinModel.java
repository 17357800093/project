package com.example.dwkyanglao.activity.model;

import java.util.List;

public class YjinModel {

    /**
     * data : [{"tag":"heart","keep":60,"state":{"max":100,"min":60}},{"tag":"breath","keep":60,"state":{"max":20,"min":12}},{"tag":"move","keep":900}]
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
         * tag : heart
         * keep : 60
         * state : {"max":100,"min":60}
         */

        private String tag;
        private int keep;
        private StateBean state;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getKeep() {
            return keep;
        }

        public void setKeep(int keep) {
            this.keep = keep;
        }

        public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }

        public static class StateBean {
            /**
             * max : 100
             * min : 60
             */

            private int max;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }
    }
}
