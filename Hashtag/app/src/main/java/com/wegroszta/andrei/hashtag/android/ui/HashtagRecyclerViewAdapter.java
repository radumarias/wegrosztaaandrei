package com.wegroszta.andrei.hashtag.android.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wegroszta.andrei.hashtag.R;
import com.wegroszta.andrei.hashtag.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

public class HashtagRecyclerViewAdapter extends RecyclerView.Adapter<HashtagRecyclerViewAdapter.HashtagViewHolder> {
    private List<Hashtag> hashtags;
    private OnHashtagClickListener onHashtagClickListener;

    public HashtagRecyclerViewAdapter(List<Hashtag> hashtags) {
        this.hashtags = new ArrayList<>();
        this.hashtags.addAll(hashtags);
    }

    public void setOnHashtagClickListener(OnHashtagClickListener onHashtagClickListener) {
        this.onHashtagClickListener = onHashtagClickListener;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags.clear();
        this.hashtags.addAll(hashtags);
    }

    @Override
    public HashtagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HashtagViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hashtag, parent, false));
    }

    @Override
    public void onBindViewHolder(HashtagViewHolder holder, int position) {
        final Hashtag hashtag = hashtags.get(position);

        holder.tvHashtagName.setText(hashtag.getName());
        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onHashtagClickListener != null) {
                    onHashtagClickListener.onHashtagClicked(hashtag);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hashtags.size();
    }

    class HashtagViewHolder extends RecyclerView.ViewHolder {
        TextView tvHashtagName;
        CardView cvContainer;

        HashtagViewHolder(View itemView) {
            super(itemView);
            tvHashtagName = (TextView) itemView.findViewById(R.id.tv_hashtag_name);
            cvContainer = (CardView) itemView.findViewById(R.id.cv_container);
        }
    }

    interface OnHashtagClickListener {
        void onHashtagClicked(Hashtag hashtag);
    }
}
