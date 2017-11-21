package com.wegroszta.andrei.hashtag.android.io;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import retrofit2.Call;
import retrofit2.http.GET;

public class CustomTwitterApiClient extends TwitterApiClient {
    public CustomTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public CustomService getCustomService() {
        return getService(CustomService.class);
    }

    interface CustomService {
        @GET("/1.1/friends/list.json")
        Call<FriendResult> getFriends();
    }

}
