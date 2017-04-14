package demo.webmyne.com.retrofitexample.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vaibhavirana on 04-04-2017.
 */

public class Function {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }


    public static String jsonString(Object obj) {
        return "" + MyApplication.getGson().toJson(obj);
    }
    public static boolean checkString(String input){
        if(input!=null && input.trim().length()>0){
            return true;
        }else{
            return false;
        }
    }
}
