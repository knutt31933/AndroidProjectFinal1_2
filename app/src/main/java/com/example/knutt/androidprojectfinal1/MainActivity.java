package com.example.knutt.androidprojectfinal1;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private Button buttonLoginfb;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);









        buttonLoginfb = (Button)findViewById(R.id.btnloginfb);



        //loginButton = (LoginButton)findViewById(R.id.buttonFB);
//        loginButton.setReadPermissions(Arrays.asList(
//                "public_profile", "email","publish_action"));




        buttonLoginfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "email","user_posts","user_friends"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        getUserDetail(loginResult);

                    }

                    @Override
                    public void onCancel() {


                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getUserDetail(final LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject json_object, GraphResponse response) {

                Intent intent = new Intent(MainActivity.this,
                        AccountActivity.class);
                intent.putExtra("userProfile", json_object.toString());
                intent.putExtra("Logout",loginResult.getAccessToken());
                intent.putExtra("id",loginResult.getAccessToken().getUserId());
                startActivity(intent);
            }
        });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,first_name,last_name,email,gender,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();


    }





    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }





}
