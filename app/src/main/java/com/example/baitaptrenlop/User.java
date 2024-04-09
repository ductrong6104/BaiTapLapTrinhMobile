package com.example.baitaptrenlop;

public class User {
    String login;
    String avatar;
    String url;

    public User() {
    }

    public User(String login, String avatar, String url) {
        this.login = login;
        this.avatar = avatar;
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUrl() {
        return url;
    }
}
