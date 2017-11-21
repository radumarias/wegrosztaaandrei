package com.wegroszta.andrei.hashtag.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.wegroszta.andrei.hashtag.R;

public class LoginActivity extends AppCompatActivity {
    private TwitterLoginButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (TwitterLoginButton) findViewById(R.id.btn_twitter_login);

        btnLogin.setCallback(twitterSessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        btnLogin.onActivityResult(requestCode, resultCode, data);
    }

    private final Callback<TwitterSession> twitterSessionCallback = new Callback<TwitterSession>() {
        @Override
        public void success(Result<TwitterSession> result) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        @Override
        public void failure(TwitterException exception) {
            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT)
                    .show();
        }
    };
}
