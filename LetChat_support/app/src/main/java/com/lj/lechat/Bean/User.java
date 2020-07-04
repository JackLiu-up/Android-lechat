package com.lj.lechat.Bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

    private String nickname;
    private String hobbit;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHobbit() {
        return hobbit;
    }

    public void setHobbit(String hobbit) {
        this.hobbit = hobbit;
    }
}
