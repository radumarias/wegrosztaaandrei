package com.wegroszta.andrei.hashtag.models;

import android.os.AsyncTask;

import com.wegroszta.andrei.hashtag.entities.Hashtag;

import java.util.List;

public class HashtagModel {
    private HashtagStorage hashtagStorage;
    private HashtagModelListener listener;

    public HashtagModel(HashtagStorage hashtagStorage) {
        this.hashtagStorage = hashtagStorage;
    }

    public void setListener(HashtagModelListener listener) {
        this.listener = listener;
    }

    public void createHashtag(Hashtag hashtag) {
        new CreateHashtagAsyncTask(hashtag).execute();
    }

    public void readHashtags() {
        new ReadHashtagAsyncTask().execute();
    }

    public void updateHashtag(Hashtag hashtag) {
        new UpdateHashtagAsyncTask(hashtag).execute();
    }

    public void deleteHashtag(Hashtag hashtag) {
        new DeleteHashtagAsyncTask(hashtag).execute();
    }

    private class CreateHashtagAsyncTask extends AsyncTask<Void, Void, Long> {
        private Hashtag hashtag;

        CreateHashtagAsyncTask(Hashtag hashtag) {
            this.hashtag = hashtag;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return hashtagStorage.createHashtag(hashtag);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (listener != null) {
                listener.onHashtagCreated(aLong);
            }
        }
    }

    private class ReadHashtagAsyncTask extends AsyncTask<Void, Void, List<Hashtag>> {
        @Override
        protected List<Hashtag> doInBackground(Void... voids) {
            return hashtagStorage.readHashtags();
        }

        @Override
        protected void onPostExecute(List<Hashtag> hashtags) {
            super.onPostExecute(hashtags);
            if (listener != null) {
                listener.onHashtagsRead(hashtags);
            }
        }
    }

    private class UpdateHashtagAsyncTask extends AsyncTask<Void, Void, Long> {
        private Hashtag hashtag;

        UpdateHashtagAsyncTask(Hashtag hashtag) {
            this.hashtag = hashtag;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return hashtagStorage.updateHashtag(hashtag);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (listener != null) {
                listener.onHashtagUpdated(aLong);
            }
        }
    }

    private class DeleteHashtagAsyncTask extends AsyncTask<Void, Void, Long> {
        private Hashtag hashtag;

        DeleteHashtagAsyncTask(Hashtag hashtag) {
            this.hashtag = hashtag;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return hashtagStorage.deleteHashtag(hashtag);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (listener != null) {
                listener.onHashtagDeleted(aLong);
            }
        }
    }

    public interface HashtagModelListener {
        void onHashtagCreated(long id);

        void onHashtagsRead(List<Hashtag> hashtags);

        void onHashtagUpdated(long id);

        void onHashtagDeleted(long id);
    }
}
