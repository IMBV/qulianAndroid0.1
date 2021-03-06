package com.quliantrip.qulian.domain.choice.good;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 提交订单
 */
public class OrderSubmitBean extends BaseJson {

    private int code;
    private DataEntity data;
    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity {
        private OnlineEntity online;
        private List<AttributeEntity> attribute;
        private List<BranchnameEntity> branchname;
        private List<AttrssEntity> attrss;

        public List<AttrssEntity> getAttrss() {
            return attrss;
        }

        public void setAttrss(List<AttrssEntity> attrss) {
            this.attrss = attrss;
        }

        public void setOnline(OnlineEntity online) {
            this.online = online;
        }

        public void setAttribute(List<AttributeEntity> attribute) {
            this.attribute = attribute;
        }

        public void setBranchname(List<BranchnameEntity> branchname) {
            this.branchname = branchname;
        }

        public OnlineEntity getOnline() {
            return online;
        }

        public List<AttributeEntity> getAttribute() {
            return attribute;
        }

        public List<BranchnameEntity> getBranchname() {
            return branchname;
        }

        public static class OnlineEntity {
            private String id;
            private String img;
            private String name;

            public void setId(String id) {
                this.id = id;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public String getImg() {
                return img;
            }

            public String getName() {
                return name;
            }
        }

        public static class AttributeEntity {

            private String id;
            @SerializedName("package")
            private String packageX;

            public void setId(String id) {
                this.id = id;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getId() {
                return id;
            }

            public String getPackageX() {
                return packageX;
            }
        }

        public static class BranchnameEntity {
            private String id;
            private String name;
            private String images;
            private String address;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImages() {
                return images;
            }

            public String getAddress() {
                return address;
            }
        }

        public static class AttrssEntity {

            private String sale;
            private String proce;
            private String num;
            private String date;
            private String de;

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setProce(String proce) {
                this.proce = proce;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setDe(String de) {
                this.de = de;
            }

            public String getSale() {
                return sale;
            }

            public String getProce() {
                return proce;
            }

            public String getNum() {
                return num;
            }

            public String getDate() {
                return date;
            }

            public String getDe() {
                return de;
            }
        }
    }
}
