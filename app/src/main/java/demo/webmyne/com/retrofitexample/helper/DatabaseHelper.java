package demo.webmyne.com.retrofitexample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import demo.webmyne.com.retrofitexample.api.model.job.JobResponse;

/**
 * Created by vaibhavirana on 07-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public DatabaseHelper(Context context) {
        super(context, AppConstant.DB_NAME,null,AppConstant.DB_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " +AppConstant.TABLE_NAME
                +"(" +AppConstant.JOB_TYPE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +AppConstant.JOB_NAME+ " VARCHAR," +AppConstant.JOB_TYPE+ " VARCHAR, "
                +AppConstant.JOB_IMAGE+ " VARCHAR," +AppConstant.JOB_DESC+ " VARCHAR);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Employee";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    //insert employee details into the database
    public boolean addEmployee(List<JobResponse.ResponseDataBean.DataBean> data){
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i=0;i<data.size();i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(AppConstant.JOB_NAME, data.get(i).getJobTitle());
            contentValues.put(AppConstant.JOB_TYPE, data.get(i).getJobType());
            contentValues.put(AppConstant.JOB_DESC, data.get(i).getDescription());
            contentValues.put(AppConstant.JOB_IMAGE,data.get(i).getImageName());
            db.insert(AppConstant.TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    //Show employee details into the recyclerview
    public List<JobResponse.ResponseDataBean.DataBean> getEmployee(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Employee";
        Cursor cursor = db.rawQuery(sql, null);

        List<JobResponse.ResponseDataBean.DataBean> data = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                JobResponse.ResponseDataBean.DataBean bean = new JobResponse.ResponseDataBean.DataBean();
                bean.setJobTitle(cursor.getString(cursor.getColumnIndex(AppConstant.JOB_NAME)));
                bean.setJobType(cursor.getString(cursor.getColumnIndex(AppConstant.JOB_TYPE)));
                bean.setDescription(cursor.getString(cursor.getColumnIndex(AppConstant.JOB_DESC)));
                bean.setImageName(cursor.getString(cursor.getColumnIndex(AppConstant.JOB_IMAGE)));

                data.add(bean);
            } while (cursor.moveToNext());
        }
        return data;
    }
}
