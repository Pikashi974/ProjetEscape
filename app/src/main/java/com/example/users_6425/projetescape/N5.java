package com.example.users_6425.projetescape;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by users-6425 on 19/03/2018.
 */

public class N5 extends AppCompatActivity {
    private MyDBAdapter dbAdapter;
    private String next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        next = intent.getExtras().getString("next");

        String text = "";
        Button N2 = (Button) findViewById(R.id.N2);
        N2.setText("Ecouter la personne");
        Button N3 = (Button) findViewById(R.id.N3);
        N3.setText("Partir et prendre l'autre porte");
        int pos;
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        try {
            InputStream is = getAssets().open("5.txt");
            pos = 1;
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(text);
    }

    public void onClick(View view) throws ClassNotFoundException {
        if (view.getId() == R.id.N2) {
            next = "N8";
        } else {
            next = "N3";
        }
        dbAdapter.updateScore(dbAdapter.getAllScores().get(0).getID(), next, 0);
        System.out.println(dbAdapter.getAllScores().get(0).getName());
        Intent intent = new Intent(this, Class.forName("com.example.users_6425.projetescape." + next));
        intent.putExtra("next", next);
        startActivity(intent);
        finish();
    }

    public void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }
}
