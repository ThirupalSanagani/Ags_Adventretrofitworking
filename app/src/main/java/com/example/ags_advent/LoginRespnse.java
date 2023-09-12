package com.example.ags_advent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Result;


public class LoginRespnse {
    @SerializedName("result")
    @Expose
    private LoginResult result;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("errors")
    @Expose
    private String[] errors;

    public LoginResult getResult() {

        return result;
    }

    public void setResult(LoginResult result) {

        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(Boolean success) {

        this.success = success;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }


}
