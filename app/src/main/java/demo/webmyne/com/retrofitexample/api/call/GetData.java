package demo.webmyne.com.retrofitexample.api.call;

import android.content.Context;

import java.util.List;

import demo.webmyne.com.retrofitexample.api.ApiCalling;
import demo.webmyne.com.retrofitexample.api.model.movie.Movie;
import demo.webmyne.com.retrofitexample.api.model.movie.MyResp;
import demo.webmyne.com.retrofitexample.helper.MyApplication;
import demo.webmyne.com.retrofitexample.helper.ProgressBarHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class GetData {
    private final static String API_KEY = "56ab5b85b5e275d133eb631fea6b04b6";
    private ProgressBarHelper progressBarHelper;
    private OnGetData onGetData;

    public GetData(Context context, OnGetData onGetData) {
        progressBarHelper = new ProgressBarHelper(context, false);
        this.onGetData = onGetData;
        call();
    }

    private void call() {
        progressBarHelper.showProgressDialog();
        ApiCalling api = MyApplication.getRetrofit().create(ApiCalling.class);
        Call<MyResp> call = api.getTopMovies(API_KEY);
        call.enqueue(new Callback<MyResp>() {
            @Override
            public void onResponse(Call<MyResp> call, Response<MyResp> response) {
                progressBarHelper.hideProgressDialog();
                if (response.body() != null) {
                    onGetData.onSuccess(response.body().getResults());
                } else {
                    onGetData.onFail();
                }
            }

            @Override
            public void onFailure(Call<MyResp> call, Throwable t) {
                progressBarHelper.hideProgressDialog();
                onGetData.onFail();
            }
        });

    }

    public interface OnGetData {
        void onSuccess(List<Movie> data);
        void onFail();

    }
}
