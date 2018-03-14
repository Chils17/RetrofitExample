package demo.webmyne.com.retrofitexample.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import demo.webmyne.com.retrofitexample.R;
import demo.webmyne.com.retrofitexample.adapter.MyAdapter;
import demo.webmyne.com.retrofitexample.api.ApiCalling;
import demo.webmyne.com.retrofitexample.api.call.GetData;
import demo.webmyne.com.retrofitexample.api.model.TenderResponse;
import demo.webmyne.com.retrofitexample.api.model.movie.Movie;
import demo.webmyne.com.retrofitexample.helper.Function;
import demo.webmyne.com.retrofitexample.helper.MyApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private Context context;
    private MyAdapter adapter;
    private List<Movie> movieList;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        context = this;

        movieList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyAdapter(context, movieList);
        recyclerView.setAdapter(adapter);

        callApi();
    }


    private void callApi() {
        ApiCalling api = MyApplication.getRetrofit().create(ApiCalling.class);
        api.getTender(1,"2017-12-08","2017-12-08").enqueue(new Callback<TenderResponse>() {
            @Override
            public void onResponse(Call<TenderResponse> call, Response<TenderResponse> response) {
                if (response.body() != null) {
                    Log.e("res",Function.jsonString(response.body().getData()));
                        response.body().getData();
                }


            }

            @Override
            public void onFailure(Call<TenderResponse> call, Throwable t) {

            }
        });
        new GetData(context, new GetData.OnGetData() {
            @Override
            public void onSuccess(List<Movie> data) {
                if (data != null && data.size() > 0) {
                    adapter.setDataList(data);
                }
            }

            @Override
            public void onFail() {
                Function.showToast(context, getString(R.string.try_again));
            }
        });

    }
}
