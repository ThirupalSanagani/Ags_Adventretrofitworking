package com.example.ags_advent;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Retrointerface {
    //https://vserveq.voltasworld.com/safetyapi/api/User/SignIn
    @POST("safetyapi/api/User/SignIn")
    //Call<Dataapi> SignResponse (@Body Dataapi dataapi);
    Call<LoginRespnse> SignResponse(@Body Dataapi dataapi);


}
