package demo.webmyne.com.retrofitexample.api;

import demo.webmyne.com.retrofitexample.api.model.job.JobRequest;
import demo.webmyne.com.retrofitexample.api.model.job.JobResponse;
import demo.webmyne.com.retrofitexample.api.model.login.LoginRequest;
import demo.webmyne.com.retrofitexample.api.model.login.LoginResponse;
import demo.webmyne.com.retrofitexample.api.model.movie.MyResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vaibhavirana on 04-04-2017.
 */

public interface ApiCalling {
    @GET(ApiConstant.getTopMovie)
    Call<MyResp> getTopMovies(@Query("api_key") String apiKey);

    @GET(ApiConstant.getMovieDetail)
    Call<MyResp> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @POST(ApiConstant.getLogin)
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

    @POST(ApiConstant.getJob)
    Call<JobResponse> getJob(@Body JobRequest jobRequest);
}
