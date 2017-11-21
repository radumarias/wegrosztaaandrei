package com.wegroszta.andrei.hashtag.android.io;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

import java.io.Serializable;
import java.util.List;

public class FriendResult implements Serializable {
    @SerializedName("previous_cursor")
    public final long previousCursor;

    @SerializedName("previous_cursor_str")
    public final String previousCursorStr;

    @SerializedName("next_cursor")
    public final long nextCursor;

    @SerializedName("next_cursor_str")
    public final long nextCursorStr;

    @SerializedName("users")
    public final List<User> users;

    public FriendResult(long previousCursor, String previousCursorStr, long nextCursor, long nextCursorStr, List<User> users) {
        this.previousCursor = previousCursor;
        this.previousCursorStr = previousCursorStr;
        this.nextCursor = nextCursor;
        this.nextCursorStr = nextCursorStr;
        this.users = users;
    }
}
