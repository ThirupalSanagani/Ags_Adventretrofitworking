package com.example.ags_advent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Dataapi {
    @SerializedName("loginName")
    @Expose
    private String loginName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("imei")
    @Expose
    private String imei;

   /* public Dataapi(String loginName, String password, String imei) {
        this.loginName = loginName;
        this.password = password;
        this.imei = imei;
    }*/



    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImei() {

        return imei;
    }

    public void setImei(String imei) {

        this.imei = imei;
    }


}
