package demo.webmyne.com.retrofitexample.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import demo.webmyne.com.retrofitexample.R;
import demo.webmyne.com.retrofitexample.api.model.login.User;
import demo.webmyne.com.retrofitexample.helper.AppConstant;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imgPic;
    private TextView editName;
    private TextView editEmail;
    private EditText editDb;
    private EditText editMob;
    private EditText editOs;
    private EditText editPolicy;
    private EditText editPass;
    private User userObj;
    private Context context;
    private SharedPreferences pref;
    private TextView textPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Fetching name from shared preferences
        pref = getSharedPreferences(AppConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = pref.getString(AppConstant.INTENT_UNAME,"Not Available");

        init();
        getSupportActionBar().setTitle(pref.getString(AppConstant.INTENT_UNAME, ""));

        textPro.setText(email);
    }

    private void init() {
        textPro= (TextView)findViewById(R.id.txtProfile);
        imgPic = (ImageView) findViewById(R.id.imgPic);
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editDb = (EditText) findViewById(R.id.editDob);
        editMob = (EditText) findViewById(R.id.editMob);
        editOs = (EditText) findViewById(R.id.editOs);
        editPolicy = (EditText) findViewById(R.id.editPolicy);
        editPass = (EditText) findViewById(R.id.editPass);

        setUser();

    }

    private void setUser() {
       // Intent i = getIntent();
      //  userObj = (User) i.getSerializableExtra("obj");

        editName.setText(pref.getString(AppConstant.INTENT_UNAME, ""));
        editEmail.setText(pref.getString(AppConstant.INTENT_EMAIL, ""));
        editDb.setText(pref.getString(AppConstant.INTENT_DOB, ""));
        editMob.setText(pref.getString(AppConstant.INTENT_MOBILE, ""));
        editOs.setText(pref.getString(AppConstant.INTENT_MOBILEOS, ""));
        editPolicy.setText(pref.getString(AppConstant.INTENT_POLICYNO, ""));
        editPass.setText(pref.getString(AppConstant.INTENT_PASS, ""));


       // searilazation obj
       /* editName.setText(userObj.getName());
        editEmail.setText(userObj.getEmail());
        editDb.setText(userObj.getDOB());
        editMob.setText(userObj.getMobile());
        editOs.setText(userObj.getMobileOS());
        editPolicy.setText(userObj.getPolicyNumber());
        editPass.setText(userObj.getPassword());*/

      /*  Glide.with(getApplicationContext())
                .load(userObj.getImagePath())
                .placeholder(R.mipmap.ic_launcher)
                .into(imgPic);
*/
        Glide.with(getApplicationContext())
                .load(pref.getString(AppConstant.INTENT_IMAGE_PATH,""))
                //.load(userObj.getImagePath())
                .asBitmap().centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(new BitmapImageViewTarget(imgPic) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imgPic.setImageDrawable(circularBitmapDrawable);
                    }
                });

        //intent putextra and getExtra method

       /* editName.setText(i.getStringExtra(AppConstant.INTENT_UNAME));
        editEmail.setText(i.getStringExtra(AppConstant.INTENT_EMAIL));
        editDb.setText(i.getStringExtra(AppConstant.INTENT_DOB));
        editMob.setText(i.getStringExtra(AppConstant.INTENT_MOBILE));
        editOs.setText(i.getStringExtra(AppConstant.INTENT_MOBILEOS));
        editPolicy.setText(i.getStringExtra(AppConstant.INTENT_POLICYNO));
        editPass.setText(i.getStringExtra(AppConstant.INTENT_PASS));*/

    }

    //Logout function
    private void logout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(AppConstant.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(AppConstant.IS_LOGGED_IN, false);

                        //Putting blank value to email
                        editor.putString(AppConstant.INTENT_UNAME, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
