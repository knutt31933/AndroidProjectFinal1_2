package com.example.knutt.androidprojectfinal1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class AccountActivity extends AppCompatActivity  {


    private Button btnlogout, statusUpdate;
    private ProfilePictureView profilePictureView2;
    private TextView textname, textemail;
    private AccessToken accessToken;
    private JSONObject reponse;
    private ShareDialog shareDialog;
    private FacebookSdk facebookSdk;
    private String TAG = "MainActivity";
    private EditText editTextPost,editTextTest;
    ArrayList<String> strword = new ArrayList<>() ;
    //HashMap<Integer,String> emo = new HashMap<>();
    HashSet<String> emo = new HashSet<>();



    private CallbackManager callbackManager;
   private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_account);

        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);

        db = new Database(getApplicationContext());
        db.getReadableDatabase();

        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
        String logout = intent.getStringExtra("Logout");


        Log.w("Jsondata", jsondata);
        shareDialog = new ShareDialog(this);


        profilePictureView2 = (ProfilePictureView) findViewById(R.id.main_profile2);
        textname = (TextView) findViewById(R.id.name);
        textemail = (TextView) findViewById(R.id.email);
        statusUpdate = (Button) findViewById(R.id.updatestatus);
        editTextPost =(EditText)findViewById(R.id.main_edittextpost);
        editTextTest =(EditText)findViewById(R.id.main_testedit);







        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        try {

            reponse = new JSONObject(jsondata);
            String fname = reponse.get("first_name").toString();
            String lname = reponse.get("last_name").toString();
            String fullname = fname + " " +lname;
                    textname.setText(fullname);
            textemail.setText(reponse.get("email").toString());
            profilePictureView2.setProfileId(reponse.get("id").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        editTextTest.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Toast.makeText(AccountActivity.this,i +" get Key edit",Toast.LENGTH_LONG).show();

                return false;
            }
        });


        statusUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // LoginManager.getInstance().logInWithReadPermissions(AccountActivity.this,Arrays.asList("publish_actions"));
            getPost();

//                if (shareDialog.canShow(ShareLinkContent.class)) {
//
//
//                    ShareLinkContent linkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse("www.google.co.th")).build();
//                    shareDialog.show(linkContent);
//                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.info_id){
            Toast.makeText(getApplicationContext(), "info_id selected", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.setting_id){
            LoginManager.getInstance().logOut();
               Intent intentlog = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intentlog);
            Toast.makeText(getApplicationContext(), "setting_id selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
    public void getPost() {

        final ArrayList<HashMap<String,String>> attitude = db.getAttitudeList();
        final ArrayList<HashMap<String,String>> emotion = db.getEmotionList();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id"); // for post user id


//        new GraphRequest(
//
//                AccessToken.getCurrentAccessToken(), "/me/posts", null, HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    @Override
//                    public void onCompleted(GraphResponse response) {
//                        Toast.makeText(AccountActivity.this,response.toString(),Toast.LENGTH_LONG).show();
//
//                    }
//                }
//        ).executeAsync();
        final String message = editTextPost.getText().toString().trim();
        if(message.length() == 0){
            Toast.makeText(AccountActivity.this,"กรุณาใส่ข้อความ",Toast.LENGTH_LONG).show();
        }else{

            Locale thaiLocale = new Locale("th");



            BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);

//BreakIterator boundary = DictionaryBasedBreakIterator.getWordInstance(thaiLocale);

            boundary.setText(message);

            StringBuffer strout = new StringBuffer();
            int start = boundary.first();
            for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {

                strword.add(message.substring(start,end));
                //Toast.makeText(MainActivity.this,input.substring(start, end) + " ",Toast.LENGTH_SHORT).show();

            }

            int count = 0;

            for(int i = 0;i<strword.size();i++){
                String str = strword.get(i);

                // Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
                for(int j = 0;j<attitude.size();j++){
                    if(str.equals(attitude.get(j).get("AttitudeWord"))){
                        String rank = attitude.get(j).get("AttitudeRank");
                        int attemo = Integer.parseInt(attitude.get(j).get("Emotion"));

                        String emotionword = emotion.get(attemo).get("EmotionWord");
                        int countrank = Integer.parseInt(rank);
                        emo.add(emotionword);
                        count = count+countrank;


                    }
                }

            }

            Iterator<String> itremo = emo.iterator();


       /* make the API call */
            StringBuffer emoneutral = new StringBuffer();
            StringBuffer emopositive = new StringBuffer();
            StringBuffer emonegetive = new StringBuffer();

       if(count == 0 && emo.size()!= 0){

           int countnum = 0;
           Bundle params = new Bundle();
           params.putString("message", message);

           while (itremo.hasNext()){


                   emoneutral.append(itremo.next() + " ");
                   countnum++;

           }
           if(countnum == 0 ){

               emoneutral.append("-");
           }
           AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
           builder.setMessage("ทัศนคติ : เป็นกลาง\nอารมณ์ : "+emoneutral).setTitle("AlertDialog").setCancelable(false)
                   .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Bundle params = new Bundle();
                           params.putString("message", message);
                           new GraphRequest(
                                   AccessToken.getCurrentAccessToken(),
                                   "/me/feed",
                                   params,
                                   HttpMethod.POST,
                                   new GraphRequest.Callback() {
                                       public void onCompleted(GraphResponse response) {
                                           Toast.makeText(AccountActivity.this,"Post Status Success",Toast.LENGTH_LONG).show();
                                       }
                                   }
                           ).executeAsync();
                       }
                   })
                   .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           dialogInterface.cancel();
                       }
                   });
           AlertDialog dialog = builder.create();
           dialog.show();

       }else if(count<0 && emo.size()!= 0){
           int countnum2 =0;
           Bundle params = new Bundle();
           params.putString("message", message);


           while (itremo.hasNext()){


                   emonegetive.append(itremo.next() + " ");
                   countnum2++;

           }

           if(countnum2 == 0){
               emonegetive.append("-");
           }


           AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
           builder.setMessage("ทัศนคติ : ทางลบ\nอารมณ์ : "+emonegetive).setTitle("AlertDialog").setCancelable(false)
                   .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Bundle params = new Bundle();
                           params.putString("message", message);
                           new GraphRequest(
                                   AccessToken.getCurrentAccessToken(),
                                   "/me/feed",
                                   params,
                                   HttpMethod.POST,
                                   new GraphRequest.Callback() {
                                       public void onCompleted(GraphResponse response) {
                                           Toast.makeText(AccountActivity.this,"Post Status Success",Toast.LENGTH_LONG).show();
                                       }
                                   }
                           ).executeAsync();
                       }
                   })
                   .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           dialogInterface.cancel();
                       }
                   });
           AlertDialog dialog = builder.create();
           dialog.show();


       }else if(count > 0 && emo.size()!= 0){
           int countnum3 = 0;
           Bundle params = new Bundle();
           params.putString("message", message);

           while (itremo.hasNext()){


                   emopositive.append(itremo.next() + " ");
                   countnum3++;


           }
           if(countnum3==0){
               emopositive.append("-");

           }


           AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
           builder.setMessage("ทัศนคติ : ทางบวก\nอารมณ์ : "+emopositive).setTitle("AlertDialog").setCancelable(false)
                   .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Bundle params = new Bundle();
                           params.putString("message", message);
                           new GraphRequest(
                                   AccessToken.getCurrentAccessToken(),
                                   "/me/feed",
                                   params,
                                   HttpMethod.POST,
                                   new GraphRequest.Callback() {
                                       public void onCompleted(GraphResponse response) {
                                           Toast.makeText(AccountActivity.this,"Post Status Success",Toast.LENGTH_LONG).show();
                                       }
                                   }
                           ).executeAsync();

                       }
                   })
                   .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           dialogInterface.cancel();
                       }
                   });
           AlertDialog dialog = builder.create();
           dialog.show();

       }else{
           AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
           builder.setMessage("ทัศนคติ : -\nอารมณ์ : -").setTitle("AlertDialog").setCancelable(false)
                   .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Bundle params = new Bundle();
                           params.putString("message", message);
                           new GraphRequest(
                                   AccessToken.getCurrentAccessToken(),
                                   "/me/feed",
                                   params,
                                   HttpMethod.POST,
                                   new GraphRequest.Callback() {
                                       public void onCompleted(GraphResponse response) {
                                           Toast.makeText(AccountActivity.this,"Post Status Success",Toast.LENGTH_LONG).show();
                                       }
                                   }
                           ).executeAsync();

                       }
                   })
                   .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           dialogInterface.cancel();
                       }
                   });
           AlertDialog dialog = builder.create();
           dialog.show();



       }



//            editTextPost.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View view, int i, KeyEvent keyEvent) {
//
//
//                    return false;
//                }
//            });


       /* make the API call */

        }


    }


}

