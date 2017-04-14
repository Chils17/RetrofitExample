package demo.webmyne.com.retrofitexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import demo.webmyne.com.retrofitexample.R;
import demo.webmyne.com.retrofitexample.adapter.JobAdapter;
import demo.webmyne.com.retrofitexample.api.call.GetJob;
import demo.webmyne.com.retrofitexample.api.model.job.JobRequest;
import demo.webmyne.com.retrofitexample.api.model.job.JobResponse;
import demo.webmyne.com.retrofitexample.helper.DatabaseHelper;
import demo.webmyne.com.retrofitexample.helper.Function;

public class JobActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Context context;
    private JobAdapter adapter;
    private JobRequest jobRequest;
    private List<JobResponse.ResponseDataBean.DataBean> dataBeen ;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        init();
    }

    private void init() {
        context = this;

        db = new DatabaseHelper(context);

        dataBeen = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new JobAdapter(context, dataBeen);
        recyclerView.setAdapter(adapter);

        callApi();
    }

    private void callApi() {
        jobRequest = new JobRequest();
        jobRequest.setLanguageType("E");
        jobRequest.setLastJobID(0);
        jobRequest.setUserID(10023);

        new GetJob(context, new GetJob.OnGetJob(){

            @Override
            public void onSuccess(List<JobResponse.ResponseDataBean.DataBean> data) {
                if (data != null && data.size() > 0) {
                  //  adapter.setDataList(data);
                    db.addEmployee(data);
                    //db.getEmployee(data);
                    adapter.setDataList(db.getEmployee());
                }
            }

            @Override
            public void onFail() {
                Function.showToast(context, getString(R.string.try_again));
            }

            @Override
            public void onServerError(String responseMessage) {
                Function.showToast(context, responseMessage);
            }
        },jobRequest);
    }
}
