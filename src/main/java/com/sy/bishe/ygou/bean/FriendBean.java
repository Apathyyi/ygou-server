package com.sy.bishe.ygou.bean;

public class FriendBean {
    private int id;
    private int friend_id;
    private int user_id;
    private String friend_tag;
    private boolean friend_agree;

    private String friend_name;

    private String friend_img;

    public FriendBean() {
    }

    public FriendBean(int id, int friend_id, int user_id, String friend_tag, boolean friend_agree, String friend_name, String friend_img) {
        this.id = id;
        this.friend_id = friend_id;
        this.user_id = user_id;
        this.friend_tag = friend_tag;
        this.friend_agree = friend_agree;
        this.friend_name = friend_name;
        this.friend_img = friend_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFriend_tag() {
        return friend_tag;
    }

    public void setFriend_tag(String friend_tag) {
        this.friend_tag = friend_tag;
    }

    public boolean isFriend_agree() {
        return friend_agree;
    }

    public void setFriend_agree(boolean friend_agree) {
        this.friend_agree = friend_agree;
    }

    public String getFriend_name() {
        return friend_name;
    }

    public void setFriend_name(String friend_name) {
        this.friend_name = friend_name;
    }

    public String getFriend_img() {
        return friend_img;
    }

    public void setFriend_img(String friend_img) {
        this.friend_img = friend_img;
    }

    @Override
    public String toString() {
        return "FriendBean{" +
                "id=" + id +
                ", friend_id=" + friend_id +
                ", user_id=" + user_id +
                ", friend_tag='" + friend_tag + '\'' +
                ", friend_agree=" + friend_agree +
                '}';
    }
}
