package com.example.users_6425.projetescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.users_6425.projetescape.MainActivity.*;

import java.io.IOException;
import java.io.InputStream;

public class N1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debut);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String text = "";
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        try {
            InputStream is = getAssets().open("1.txt");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(text);
    }

    public void onClick(View view) throws ClassNotFoundException {
        String ID = (getResources().getResourceEntryName(view.getId()));

        Intent intent = new Intent(this, Class.forName("com.example.users_6425.projetescape." + ID));

        startActivity(intent);
        finish();
    }
}
