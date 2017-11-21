package com.wegroszta.andrei.hashtag.android.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wegroszta.andrei.hashtag.R;
import com.wegroszta.andrei.hashtag.android.io.FetchHashtagsIntentService;
import com.wegroszta.andrei.hashtag.entities.Hashtag;
import com.wegroszta.andrei.hashtag.models.HashtagModel;
import com.wegroszta.andrei.hashtag.models.db.SqliteHashtagStorage;
import com.wegroszta.andrei.hashtag.presenters.HashtagPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HashtagRecyclerViewAdapter.OnHashtagClickListener, View.OnClickListener, HashtagPresenter.HashtagView {
    private FloatingActionButton fabAddHashtag;
    private RecyclerView rvHashtags;
    private HashtagRecyclerViewAdapter hashtagRecyclerViewAdapter;

    private List<Hashtag> hashtags;
    private HashtagPresenter hashtagPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddHashtag = (FloatingActionButton) findViewById(R.id.fab_add_hashtag);
        fabAddHashtag.setOnClickListener(this);

        hashtags = new ArrayList<>();
        rvHashtags = (RecyclerView) findViewById(R.id.rv_hashtags);
        rvHashtags.setLayoutManager(new LinearLayoutManager(this));
        hashtagRecyclerViewAdapter = new HashtagRecyclerViewAdapter(hashtags);
        hashtagRecyclerViewAdapter.setOnHashtagClickListener(this);
        rvHashtags.setAdapter(hashtagRecyclerViewAdapter);

        hashtagPresenter = new HashtagPresenter(new HashtagModel(new SqliteHashtagStorage(this)));
        hashtagPresenter.bindView(this);
        hashtagPresenter.readHashtags();

        Intent intent = new Intent(this, FetchHashtagsIntentService.class);
        startService(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_hashtag:
                showAddHashtagDialog();
                break;
        }
    }

    private void showAddHashtagDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_hashtag);

        final EditText input = createAddHashtagDialogEditText();
        builder.setView(input);

        addAlertDialogBuilderButtons(builder, input);

        builder.show();
    }

    private void addAlertDialogBuilderButtons(AlertDialog.Builder builder, final EditText input) {
        addAlertDialogBuilderPositiveButton(builder, input);
        addAlertDialogBuilderNegativeButton(builder);
    }

    private void addAlertDialogBuilderNegativeButton(AlertDialog.Builder builder) {
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private void addAlertDialogBuilderPositiveButton(AlertDialog.Builder builder, final EditText input) {
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hashtag = input.getText().toString();
                addNewHashtag(hashtag);
            }
        });
    }

    private void addNewHashtag(String hashtag) {
        Hashtag newHashtag = new Hashtag(hashtag);
        hashtags.add(newHashtag);
        hashtagRecyclerViewAdapter.setHashtags(hashtags);
        hashtagRecyclerViewAdapter.notifyDataSetChanged();

        hashtagPresenter.createHashtag(newHashtag);
    }

    @NonNull
    private EditText createAddHashtagDialogEditText() {
        final EditText input = new EditText(this);
        input.setMaxLines(1);
        return input;
    }

    @Override
    public void onHashtagClicked(Hashtag hashtag) {
        Toast.makeText(this, hashtag.getName() + " selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHashtagCreated(long id) {

    }

    @Override
    public void onHashtagsRead(List<Hashtag> hashtags) {
        this.hashtags.clear();
        this.hashtags.addAll(hashtags);
        hashtagRecyclerViewAdapter.setHashtags(hashtags);
        hashtagRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHashtagUpdated(long id) {

    }

    @Override
    public void onHashtagDeleted(long id) {

    }
}
