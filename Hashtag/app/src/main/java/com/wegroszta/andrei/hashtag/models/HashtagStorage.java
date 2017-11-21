package com.wegroszta.andrei.hashtag.models;

import com.wegroszta.andrei.hashtag.entities.Hashtag;

import java.util.List;

public interface HashtagStorage {
    long createHashtag(Hashtag hashtag);

    List<Hashtag> readHashtags();

    long updateHashtag(Hashtag hashtag);

    long deleteHashtag(Hashtag hashtag);
}
