package demo.webmyne.com.retrofitexample.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import demo.webmyne.com.retrofitexample.R;
import demo.webmyne.com.retrofitexample.api.call.GetLogin;
import demo.webmyne.com.retrofitexample.api.model.login.LoginRequest;
import demo.webmyne.com.retrofitexample.api.model.login.User;
import demo.webmyne.com.retrofitexample.helper.AppConstant;
import demo.webmyne.com.retrofitexample.helper.Function;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editMobile;
    private EditText editPass;
    private Context context;
    private LoginRequest loginRequest;
    private Button btn_login;
    public String mobile;
    public String pass;
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(AppConstant.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(AppConstant.IS_LOGGED_IN, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void init() {
        context = this;
        editMobile = (EditText) findViewById(R.id.editMobile);
        editPass = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    private void callApi() {
        loginRequest = new LoginRequest();
        loginRequest.setMobile(mobile);
        loginRequest.setPassword(pass);
        loginRequest.setIsRegister(0);
        loginRequest.setGCMToken("");
        loginRequest.setDeviceId("");
        loginRequest.setMobileOS("A");

        Log.e("loginRequest", Function.jsonString(loginRequest));

        new GetLogin(context, new GetLogin.OnGetLogin() {
            @Override
            public void onSuccess(User data) {
                if (data != null) {
                    //Log.e("USER",Function.jsonString(data));
                    Function.showToast(context, AppConstant.IS_LOGGED_IN);

                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(AppConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(AppConstant.IS_LOGGED_IN, true);
                    editor.putString(AppConstant.INTENT_UNAME, data.getName());
                    editor.putString(AppConstant.INTENT_EMAIL, data.getEmail());
                    editor.putString(AppConstant.INTENT_DOB, data.getDOB());
                    editor.putString(AppConstant.INTENT_MOBILE, data.getMobile());
                    editor.putString(AppConstant.INTENT_MOBILEOS, data.getMobileOS());
                    editor.putString(AppConstant.INTENT_POLICYNO, data.getPolicyNumber());
                    editor.putString(AppConstant.INTENT_PASS, data.getPassword());
                    editor.putString(AppConstant.INTENT_IMAGE_PATH, data.getImagePath());

                    editor.commit();

                    //intent putextra and getExtra method
                  /*  intent.putExtra(AppConstant.INTENT_UNAME,data.getName());
                    intent.putExtra(AppConstant.INTENT_EMAIL,data.getEmail());
                    intent.putExtra(AppConstant.INTENT_DOB,data.getDOB());
                    intent.putExtra(AppConstant.INTENT_MOBILE,data.getMobile());
                    intent.putExtra(AppConstant.INTENT_MOBILEOS,data.getMobileOS());
                    intent.putExtra(AppConstant.INTENT_POLICYNO,data.getPolicyNumber());
                    intent.putExtra(AppConstant.INTENT_PASS,data.getPassword());*/
                    Intent intent = new Intent(context, ProfileActivity.class);
                   // intent.putExtra("obj", data);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(context,"Data was wrong !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail() {
                Toast.makeText(context,"Something went wrong !",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServerError(String responseMessage) {
                Toast.makeText(context,"Something went wrong !",Toast.LENGTH_SHORT).show();
                Function.showToast(context, responseMessage);
            }
        }, loginRequest);
    }

    @Override
    public void onClick(View view) {
        mobile = editMobile.getText().toString();
        pass = editPass.getText().toString();

        if (!mobile.isEmpty() && !pass.isEmpty()) {
            if (mobile.length()<= 10 && pass.length()<= 6)
            {
                callApi();
            }
            else
            {
                Toast.makeText(context,"Fields are too long  !",Toast.LENGTH_SHORT).show();
            }

        } else {
            Snackbar.make(view, "Fields are empty !", Snackbar.LENGTH_LONG).show();
            Function.showToast(context, getString(R.string.try_again));
        }
    }
}
