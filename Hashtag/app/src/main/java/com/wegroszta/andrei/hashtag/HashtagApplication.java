package com.wegroszta.andrei.hashtag;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

public class HashtagApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Twitter.initialize(this);
    }
}
