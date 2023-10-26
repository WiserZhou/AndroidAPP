package com.example.app3;


import com.google.gson.annotations.SerializedName;

/**
 * @author 86186
 */
public class javaBean {
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
