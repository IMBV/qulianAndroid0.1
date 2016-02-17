package com.quliantrip.qulian.domain.choice.playMethod;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 玩法的bean对象
 */
public class PlayMethodBean extends BaseJson {

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
        private List<ScreenEntity> screen;
        private List<PlayEntity> play;

        public void setScreen(List<ScreenEntity> screen) {
            this.screen = screen;
        }

        public void setPlay(List<PlayEntity> play) {
            this.play = play;
        }

        public List<ScreenEntity> getScreen() {
            return screen;
        }

        public List<PlayEntity> getPlay() {
            return play;
        }

        public static class ScreenEntity {
            private String name;
            private List<ChildEntity> child;

            public void setName(String name) {
                this.name = name;
            }

            public void setChild(List<ChildEntity> child) {
                this.child = child;
            }

            public String getName() {
                return name;
            }

            public List<ChildEntity> getChild() {
                return child;
            }

            public static class ChildEntity {
                private String id;
                private String tag_name;
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public String getId() {
                    return id;
                }

                public String getTag_name() {
                    return tag_name;
                }
            }
        }

        public static class PlayEntity {
            private String id;
            private String title;
            private String summary;
            private String userid;
            private String buynum;
            private String username;
            private String head_img;
            private String region;
            private String sale;
            private String proce;
            private String imgs;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setBuynum(String buynum) {
                this.buynum = buynum;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setProce(String proce) {
                this.proce = proce;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getSummary() {
                return summary;
            }

            public String getUserid() {
                return userid;
            }

            public String getBuynum() {
                return buynum;
            }

            public String getUsername() {
                return username;
            }

            public String getHead_img() {
                return head_img;
            }

            public String getRegion() {
                return region;
            }

            public String getSale() {
                return sale;
            }

            public String getProce() {
                return proce;
            }

            public String getImgs() {
                return imgs;
            }
        }
    }
}
