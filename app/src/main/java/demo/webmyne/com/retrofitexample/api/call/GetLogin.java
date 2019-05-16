package demo.webmyne.com.retrofitexample.api.call;

import android.content.Context;
import android.util.Log;

import demo.webmyne.com.retrofitexample.api.ApiCalling;
import demo.webmyne.com.retrofitexample.api.model.login.LoginRequest;
import demo.webmyne.com.retrofitexample.api.model.login.LoginResponse;
import demo.webmyne.com.retrofitexample.api.model.login.User;
import demo.webmyne.com.retrofitexample.helper.Function;
import demo.webmyne.com.retrofitexample.helper.MyApplication;
import demo.webmyne.com.retrofitexample.helper.ProgressBarHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class GetLogin {
    private Context context;
    private ProgressBarHelper progressBarHelper;
    private OnGetLogin onGetLogin;
    private LoginRequest loginRequest;

    public GetLogin(Context context, OnGetLogin onGetLogin, LoginRequest loginRequest) {
        progressBarHelper = new ProgressBarHelper(context, false);
        this.onGetLogin = onGetLogin;
        this.loginRequest = loginRequest;
        this.context = context;
        call();
    }

    private void call() {
        progressBarHelper.showProgressDialog();

        ApiCalling api = MyApplication.getRetrofit().create(ApiCalling.class);
        api.getLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBarHelper.hideProgressDialog();
                if (response.body() != null) {
                    Log.e("res", Function.jsonString(response.body()));
                    if (response.body().getResponseCode() == 1) {
                        onGetLogin.onSuccess(response.body().getData());
                    } else {
                        onGetLogin.onServerError(response.body().getResponseMessage());
                    }

                } else {
                    onGetLogin.onFail();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBarHelper.hideProgressDialog();
                onGetLogin.onFail();
            }
        });


    }

    public interface OnGetLogin {
        void onSuccess(User data);

        void onFail();

        void onServerError(String responseMessage);
    }
}
