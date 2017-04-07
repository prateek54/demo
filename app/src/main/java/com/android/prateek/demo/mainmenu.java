package com.android.prateek.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;

public class mainmenu extends AppCompatActivity {

    private static final String GRAPH_PATH = "me/permissions";
    private static final String SUCCESS = "success";

    private static final int PICK_PERMS_REQUEST = 0;

    private CallbackManager callbackManager;

    private ProfilePictureView profilePictureView;
    private TextView userNameView;
    private Button fb_popup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());

        profilePictureView = (ProfilePictureView) findViewById(R.id.profile_fb_nav);
        profilePictureView.setCropped(true);
        fb_popup= (Button) findViewById(R.id.fb_final_pop);
        userNameView = (TextView) findViewById(R.id.name_fb_nav);
       fb_popup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(mainmenu.this,popup_verify.class);
               startActivity(intent);

           }
       });
        final Button deAuthButton = (Button) findViewById(R.id.deauth);
        deAuthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isLoggedIn()) {
                    Toast.makeText(
                            mainmenu.this,
                            R.string.app_not_logged_in,
                            Toast.LENGTH_LONG).show();
                    return;
                }
                GraphRequest.Callback callback = new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        try {
                            if(response.getError() != null) {
                                Toast.makeText(
                                        mainmenu.this,
                                        getResources().getString(
                                                R.string.failed_to_deauth,
                                                response.toString()),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                            else if (response.getJSONObject().getBoolean(SUCCESS)) {
                                LoginManager.getInstance().logOut();
                                Intent intent = new Intent(mainmenu.this,
                                        MainActivity.class);

                                Toast.makeText(
                                        mainmenu.this,
                                        "YOU HAVE BEEN LOGGED OUT",
                                        Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }
                        } catch (JSONException ex) { /* no op */ }
                    }
                };
                GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),
                        GRAPH_PATH, new Bundle(), HttpMethod.DELETE, callback);
                request.executeAsync();
            }
        });


        new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    final Profile oldProfile,
                    final Profile currentProfile) {
                updateUI();
            }
        };
    }

    private boolean isLoggedIn() {
        AccessToken accesstoken = AccessToken.getCurrentAccessToken();
        return !(accesstoken == null || accesstoken.getPermissions().isEmpty());
    }

    private void updateUI() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            profilePictureView.setProfileId(profile.getId());
            userNameView
                    .setText(String.format("%s %s",profile.getFirstName(), profile.getLastName()));
        } else {
            profilePictureView.setProfileId(null);
            userNameView.setText("POTATO SINGH");
        }
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_PERMS_REQUEST) {
            if(resultCode == RESULT_OK) {
                String[] readPermsArr = data
                        .getStringArrayExtra(PermissionSelectActivity.EXTRA_SELECTED_READ_PARAMS);
                String writePrivacy = data
                        .getStringExtra(PermissionSelectActivity.EXTRA_SELECTED_WRITE_PRIVACY);
                String[] publishPermsArr = data
                        .getStringArrayExtra(
                                PermissionSelectActivity.EXTRA_SELECTED_PUBLISH_PARAMS);
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}