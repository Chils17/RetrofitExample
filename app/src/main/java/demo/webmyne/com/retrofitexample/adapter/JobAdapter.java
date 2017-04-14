package demo.webmyne.com.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demo.webmyne.com.retrofitexample.R;
import demo.webmyne.com.retrofitexample.api.model.job.JobResponse;
import demo.webmyne.com.retrofitexample.helper.Function;

/**
 * Created by vaibhavirana on 06-04-2017.
 */

public class JobAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<JobResponse.ResponseDataBean.DataBean> dataBeen;
    public static final int IMAGE_DESC = 0;
    public static final int IMAGE = 1;
    public static final int DESC = 2;

    public JobAdapter(Context context, List<JobResponse.ResponseDataBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case IMAGE_DESC:
                View itemView0 = inflater.inflate(R.layout.item_row, parent, false);
                return new RowViewHolder(itemView0);

            case IMAGE:
                View itemView1 = inflater.inflate(R.layout.item_column, parent, false);
                return new ColumnViewHolder(itemView1);

            case DESC:
                View itemView2 = inflater.inflate(R.layout.item_norm, parent, false);
                return new NormViewHolder(itemView2);

            default:
                View itemView11 = inflater.inflate(R.layout.item_row, parent, false);
                return new RowViewHolder(itemView11);

        }
    }

    @Override
    public int getItemViewType(int position) {

        JobResponse.ResponseDataBean.DataBean obj = dataBeen.get(position);
        if (Function.checkString(obj.getImageName()) && Function.checkString(obj.getDescription())) {
            return IMAGE_DESC;
        } else if (!Function.checkString(obj.getImageName()) && Function.checkString(obj.getDescription())) {
            return DESC;
        } else {
            return IMAGE;
        }
       /* switch (position) {
            case 0:
                return Row;
            case 1:
                return Col;
            case 2:
                return NORM;
            default:
                return Row;
        }*/
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JobResponse.ResponseDataBean.DataBean bean = dataBeen.get(position);
        switch (getItemViewType(position)) {
            case IMAGE_DESC:
                RowViewHolder rowViewHolder = (RowViewHolder) holder;
                //rowViewHolder.imageView.setImageResource(bean.getImageName());
                rowViewHolder.txtName.setText(bean.getJobTitle());
                //rowViewHolder.txtDesc.setText(bean.getDescription());
                rowViewHolder.txtDesc.setText(Html.fromHtml(bean.getDescription()).toString());

                Glide.with(context)
                        .load(bean.getImageName())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(rowViewHolder.imageView);
                break;

            case IMAGE:
                ColumnViewHolder columnViewHolder = (ColumnViewHolder) holder;
                //columnViewHolder.imgView.setImageResource(response.getImage());
                columnViewHolder.txt_name.setText(bean.getJobTitle());
                //columnViewHolder.txt_desc.setText(bean.getDescription());
//                columnViewHolder.txt_desc.setText(Html.fromHtml(bean.getDescription()).toString());

                Glide.with(context)
                        .load(bean.getImageName())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(columnViewHolder.imgPic);
                break;

            case DESC:
                NormViewHolder normViewHolder = (NormViewHolder) holder;
                //normViewHolder.imgView.setImageResource(response.getImage());
                normViewHolder.textName.setText(bean.getJobTitle());
                //normViewHolder.textDesc.setText(bean.getDescription());
                normViewHolder.textDesc.setText(Html.fromHtml(bean.getDescription()).toString());
        }

    }

    @Override
    public int getItemCount() {
        return dataBeen.size();
    }

    public void setDataList(List<JobResponse.ResponseDataBean.DataBean> data) {
        dataBeen = new ArrayList<>();
        dataBeen = data;
        notifyDataSetChanged();
    }

    private class RowViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtName;
        private TextView txtDesc;

        public RowViewHolder(View itemView0) {
            super(itemView0);
            imageView = (ImageView) itemView.findViewById(R.id.imagePic);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
        }
    }

    private class ColumnViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPic;
        private TextView txt_name;

        public ColumnViewHolder(View itemView1) {
            super(itemView1);
            imgPic = (ImageView) itemView.findViewById(R.id.img_pic);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }

    private class NormViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textDesc;

        public NormViewHolder(View itemView2) {
            super(itemView2);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textDesc = (TextView) itemView.findViewById(R.id.textDesc);
        }
    }

}
