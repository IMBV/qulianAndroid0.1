package com.quliantrip.qulian.domain.choice.good;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * Created by Qulian5 on 2016/1/27.
 */
public class GoodOrderSubmitBean extends BaseJson {

    private int code;
    private String msg;

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private String order_sn;

        public void setId(int id) {
            this.id = id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getId() {
            return id;
        }

        public String getOrder_sn() {
            return order_sn;
        }
    }
}
