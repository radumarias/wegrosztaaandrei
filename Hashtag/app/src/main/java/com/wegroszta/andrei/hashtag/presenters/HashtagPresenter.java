package com.wegroszta.andrei.hashtag.presenters;

import com.wegroszta.andrei.hashtag.entities.Hashtag;
import com.wegroszta.andrei.hashtag.models.HashtagModel;

import java.util.List;

public class HashtagPresenter implements HashtagModel.HashtagModelListener {
    private HashtagModel hashtagModel;
    private HashtagView hashtagView;

    public HashtagPresenter(HashtagModel hashtagModel) {
        this.hashtagModel = hashtagModel;
    }

    public void bindView(HashtagView hashtagView) {
        this.hashtagView = hashtagView;
        hashtagModel.setListener(this);
    }

    public void unbindView() {
        this.hashtagView = null;
        hashtagModel.setListener(null);
    }


    public void createHashtag(Hashtag hashtag) {
        hashtagModel.createHashtag(hashtag);
    }

    public void readHashtags() {
        hashtagModel.readHashtags();
    }

    public void update(Hashtag hashtag) {
        hashtagModel.updateHashtag(hashtag);
    }

    public void delete(Hashtag hashtag) {
        hashtagModel.deleteHashtag(hashtag);
    }

    @Override
    public void onHashtagCreated(long id) {
        if (hashtagView != null) {
            hashtagView.onHashtagCreated(id);
        }
    }

    @Override
    public void onHashtagsRead(List<Hashtag> hashtags) {
        if (hashtagView != null) {
            hashtagView.onHashtagsRead(hashtags);
        }
    }

    @Override
    public void onHashtagUpdated(long id) {
        if (hashtagView != null) {
            hashtagView.onHashtagUpdated(id);
        }
    }

    @Override
    public void onHashtagDeleted(long id) {
        if (hashtagView != null) {
            hashtagView.onHashtagDeleted(id);
        }
    }

    public interface HashtagView {
        void onHashtagCreated(long id);

        void onHashtagsRead(List<Hashtag> hashtags);

        void onHashtagUpdated(long id);

        void onHashtagDeleted(long id);
    }
}
