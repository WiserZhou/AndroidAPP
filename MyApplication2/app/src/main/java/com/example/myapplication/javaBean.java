package com.example.myapplication;


/**
 * @author 86186
 */

public class javaBean {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    private String password;

    private String new_password;

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getNew_password() {
        return new_password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
