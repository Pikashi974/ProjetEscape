package com.example.users_6425.projetescape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class UnderScore extends AppCompatActivity {
    private ListView mListView;
    private MyArrayAdapter adapter;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_score);
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        mListView = findViewById(R.id.scores);
        registerForContextMenu(mListView);

        adapter = new MyArrayAdapter(this, dbAdapter.getAllScores());
        mListView.setAdapter(adapter);

    }

    public void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }
}
