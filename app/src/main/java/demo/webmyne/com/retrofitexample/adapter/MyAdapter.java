package demo.webmyne.com.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demo.webmyne.com.retrofitexample.R;
import demo.webmyne.com.retrofitexample.api.model.movie.Movie;

/**
 * Created by vaibhavirana on 05-04-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    private List<Movie> movieList;


    public MyAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setValues(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setDataList(List<Movie> data) {
        movieList= new ArrayList<>();
        movieList = data;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPic;
        private LinearLayout moviesLayout;
        private TextView txtTitle;
        private TextView txtSubtittle;
        private TextView txtDescr;
        private TextView txtrating;

        public MyViewHolder(View itemView) {
            super(itemView);
            moviesLayout = (LinearLayout) itemView.findViewById(R.id.movies_layout);
            imgPic = (ImageView) itemView.findViewById(R.id.imgPic);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtSubtittle = (TextView) itemView.findViewById(R.id.txtSubtitle);
            txtDescr = (TextView) itemView.findViewById(R.id.txtDescr);
            txtrating = (TextView) itemView.findViewById(R.id.rating);
        }

        public void setValues(Movie movie) {
            Log.e("data",movie.getTitle());
            txtTitle.setText(movie.getTitle());
            txtSubtittle.setText(movie.getRelease_date());
            txtDescr.setText(movie.getOverview());
            txtrating.setText(movie.getVote_average().toString());
            Glide.with(context).load(movie.getPoster_path()).placeholder(R.mipmap.ic_launcher).into(imgPic);
            /*Glide.with(context).load(movie.getPoster_path()).placeholder(R.mipmap.ic_launcher)
                    .into("http://image.tmdb.org/t/p/ + 9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg");
*/
        }
    }
}
