package com.example.dwkyanglao.activity.model;

import com.google.gson.annotations.SerializedName;

public class GjsjModel {

    /**
     * data : {"10004":{"version":"1.0.15","moduleVersions":{"wifi":{"version":"1.0.15","path":"wifi/1.0.15","checksum":"f2d3b0b517d59f96bde7e0c08eebc5d6270a32a6fab393df735c4460a80c396e"},"mattress":{"version":"1.0.15","path":"mattress/1.0.15","checksum":"a447443ad7fe31d94be2df95e8e633194aa7a66a6ec92ff55604e65b43a1c2d6"}}}}
     * code : 0
     * errorMessage : null
     */

    private DataBean data;
    private int code;
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class DataBean {
        /**
         * 10004 : {"version":"1.0.15","moduleVersions":{"wifi":{"version":"1.0.15","path":"wifi/1.0.15","checksum":"f2d3b0b517d59f96bde7e0c08eebc5d6270a32a6fab393df735c4460a80c396e"},"mattress":{"version":"1.0.15","path":"mattress/1.0.15","checksum":"a447443ad7fe31d94be2df95e8e633194aa7a66a6ec92ff55604e65b43a1c2d6"}}}
         */

        @SerializedName("10004")
        private _$10004Bean _$10004;

        public _$10004Bean get_$10004() {
            return _$10004;
        }

        public void set_$10004(_$10004Bean _$10004) {
            this._$10004 = _$10004;
        }

        public static class _$10004Bean {
            /**
             * version : 1.0.15
             * moduleVersions : {"wifi":{"version":"1.0.15","path":"wifi/1.0.15","checksum":"f2d3b0b517d59f96bde7e0c08eebc5d6270a32a6fab393df735c4460a80c396e"},"mattress":{"version":"1.0.15","path":"mattress/1.0.15","checksum":"a447443ad7fe31d94be2df95e8e633194aa7a66a6ec92ff55604e65b43a1c2d6"}}
             */

            private String version;
            private ModuleVersionsBean moduleVersions;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public ModuleVersionsBean getModuleVersions() {
                return moduleVersions;
            }

            public void setModuleVersions(ModuleVersionsBean moduleVersions) {
                this.moduleVersions = moduleVersions;
            }

            public static class ModuleVersionsBean {
                /**
                 * wifi : {"version":"1.0.15","path":"wifi/1.0.15","checksum":"f2d3b0b517d59f96bde7e0c08eebc5d6270a32a6fab393df735c4460a80c396e"}
                 * mattress : {"version":"1.0.15","path":"mattress/1.0.15","checksum":"a447443ad7fe31d94be2df95e8e633194aa7a66a6ec92ff55604e65b43a1c2d6"}
                 */

                private WifiBean wifi;
                private MattressBean mattress;

                public WifiBean getWifi() {
                    return wifi;
                }

                public void setWifi(WifiBean wifi) {
                    this.wifi = wifi;
                }

                public MattressBean getMattress() {
                    return mattress;
                }

                public void setMattress(MattressBean mattress) {
                    this.mattress = mattress;
                }

                public static class WifiBean {
                    /**
                     * version : 1.0.15
                     * path : wifi/1.0.15
                     * checksum : f2d3b0b517d59f96bde7e0c08eebc5d6270a32a6fab393df735c4460a80c396e
                     */

                    private String version;
                    private String path;
                    private String checksum;

                    public String getVersion() {
                        return version;
                    }

                    public void setVersion(String version) {
                        this.version = version;
                    }

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getChecksum() {
                        return checksum;
                    }

                    public void setChecksum(String checksum) {
                        this.checksum = checksum;
                    }
                }

                public static class MattressBean {
                    /**
                     * version : 1.0.15
                     * path : mattress/1.0.15
                     * checksum : a447443ad7fe31d94be2df95e8e633194aa7a66a6ec92ff55604e65b43a1c2d6
                     */

                    private String version;
                    private String path;
                    private String checksum;

                    public String getVersion() {
                        return version;
                    }

                    public void setVersion(String version) {
                        this.version = version;
                    }

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getChecksum() {
                        return checksum;
                    }

                    public void setChecksum(String checksum) {
                        this.checksum = checksum;
                    }
                }
            }
        }
    }
}
