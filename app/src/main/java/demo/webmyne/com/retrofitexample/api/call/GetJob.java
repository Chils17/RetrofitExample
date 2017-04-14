package demo.webmyne.com.retrofitexample.api.call;

import android.content.Context;
import android.util.Log;

import java.util.List;

import demo.webmyne.com.retrofitexample.api.ApiCalling;
import demo.webmyne.com.retrofitexample.api.model.job.JobRequest;
import demo.webmyne.com.retrofitexample.api.model.job.JobResponse;
import demo.webmyne.com.retrofitexample.helper.Function;
import demo.webmyne.com.retrofitexample.helper.MyApplication;
import demo.webmyne.com.retrofitexample.helper.ProgressBarHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vaibhavirana on 06-04-2017.
 */

public class GetJob {
    private Context context;
    private ProgressBarHelper progressBarHelper;
    private OnGetJob onGetJob;
    private JobRequest jobRequest;

    public GetJob(Context context, OnGetJob onGetJob, JobRequest jobRequest) {
        progressBarHelper = new ProgressBarHelper(context, false);
        this.onGetJob = onGetJob;
        this.jobRequest = jobRequest;
        this.context = context;
        call();
    }

    private void call() {
        progressBarHelper.showProgressDialog();
        ApiCalling api = MyApplication.getRetrofit().create(ApiCalling.class);
        api.getJob(jobRequest).enqueue(new Callback<JobResponse>() {
            @Override
            public void onResponse(Call<JobResponse> call, Response<JobResponse> response) {
                progressBarHelper.hideProgressDialog();
                if (response.body() != null) {
                    Log.e("res", Function.jsonString(response.body()));
                    if (response.body().getResponseCode() == 1) {
                        onGetJob.onSuccess(response.body().getResponseData().getData());

                    } else {
                        onGetJob.onServerError(response.body().getResponseMsg());
                    }

                } else {
                    onGetJob.onFail();
                }
            }

            @Override
            public void onFailure(Call<JobResponse> call, Throwable t) {

            }
        });

    }

    public interface OnGetJob {
        void onSuccess(List<JobResponse.ResponseDataBean.DataBean> data);


        void onFail();

        void onServerError(String responseMessage);

    }
}
