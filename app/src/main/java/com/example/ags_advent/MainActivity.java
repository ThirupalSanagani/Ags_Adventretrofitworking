package com.example.ags_advent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tu, tp, ti;
    Button b1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tu = findViewById(R.id.edituserid);
        tp = findViewById(R.id.editpassword);
        b1 = findViewById(R.id.button);
        ti = findViewById(R.id.textimei);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString("userName", "");
        String savedPassword = sharedPreferences.getString("Password", "");

        tu.setText(savedUsername);
        tp.setText(savedPassword);
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://vserveq.voltasworld.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrointerface retroApi = retrofit.create(Retrointerface.class);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =tu.getText().toString();
                String password = tp.getText().toString();
                Dataapi dataapi = new Dataapi();
                dataapi.setLoginName(username);
                dataapi.setPassword(password);
                dataapi.setImei("");

                Call<LoginRespnse> call= retroApi.SignResponse(dataapi);

                call.enqueue(new Callback<LoginRespnse>() {
                    @Override
                    public void onResponse(Call<LoginRespnse> call, Response<LoginRespnse> response) {
                        if (response.isSuccessful()){

                            LoginRespnse loginRespnse = response.body();

                            if (loginRespnse != null && loginRespnse.isSuccess()) {
                                LoginResult result = loginRespnse.getResult();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userId",result.getUserId());
                                editor.putString("userName",username);
                                editor.putString("Password",password);
                                editor.putString("token", result.getToken());
                                editor.commit();
                                Intent i = new Intent (MainActivity.this,Dashboard.class);
                                startActivity(i);
                                finish();
                            } else {
                                String errorMessage =  loginRespnse.getErrors()[0];
                                showErrorDialog("Login Failed", errorMessage);
                            }
                        } else {
                            showErrorDialog("API Error", "API call failed.");
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginRespnse> call, Throwable t) {
                        showErrorDialog("Network Error", "Network error. Please try again.");
                    }
                });
            }
        });
    }
    /* private void showDialog(String title, String message) {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle(title)
                 .setMessage(message)
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {

                     }
                 }).show();
     }*/
    private void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}