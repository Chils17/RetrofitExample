package demo.webmyne.com.retrofitexample.helper;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import demo.webmyne.com.retrofitexample.api.ApiConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaibhavirana on 04-04-2017.
 */

public class MyApplication extends Application {
    private static Retrofit retrofit;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
        initGson();
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

    }

    private void initRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void initGson() {
        gson = new GsonBuilder()
                .setLenient()
                .create();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static Gson getGson() {
        return gson;
    }
}
