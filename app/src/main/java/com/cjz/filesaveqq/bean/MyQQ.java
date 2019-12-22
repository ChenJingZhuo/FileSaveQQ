package com.cjz.filesaveqq.bean;

/**
 *  author: ChenJingZhuo
 *  date: 2019/11/22
 */

public class MyQQ {
    private int _id;
    private String account;
    private String password;

    public MyQQ(){

    }

    public MyQQ(int _id,String account,String password){
        this._id=_id;
        this.account=account;
        this.password=password;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
