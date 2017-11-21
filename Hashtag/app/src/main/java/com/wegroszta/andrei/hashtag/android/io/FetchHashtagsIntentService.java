package com.wegroszta.andrei.hashtag.android.io;

import android.app.IntentService;
import android.content.Intent;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchHashtagsIntentService extends IntentService {
    private static final Timer timer = new Timer();
    private static final int TIMER_DELAY_MILLIS = 0;
    private static final int TIMER_REPEAT_PERIOD_MILLIS = 1000 * 60 * 60;

    public FetchHashtagsIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        timer.scheduleAtFixedRate(new MyTask(), TIMER_DELAY_MILLIS, TIMER_REPEAT_PERIOD_MILLIS);
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            final CustomTwitterApiClient client = new CustomTwitterApiClient(TwitterCore.getInstance().getSessionManager().getActiveSession());

            final List<Tweet> tweets = new ArrayList<>();

            Call<FriendResult> call = client.getCustomService().getFriends();
            call.enqueue(new Callback<FriendResult>() {
                @Override
                public void onResponse(Call<FriendResult> call, Response<FriendResult> response) {
                    for (User user : response.body().users) {
                        Call<List<Tweet>> tweetCall = client.getStatusesService().userTimeline(user.getId(), null, null, null, null, null, null, null, null);
                        tweetCall.enqueue(new Callback<List<Tweet>>() {
                            @Override
                            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                                if (response.body() != null) {
                                    tweets.addAll(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Tweet>> call, Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<FriendResult> call, Throwable t) {

                }
            });
        }
    }
}
