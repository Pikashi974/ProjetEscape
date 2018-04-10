package com.example.users_6425.projetescape;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    public String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) throws ClassNotFoundException {
        ID = (getResources().getResourceEntryName(view.getId()));

        Intent intent = new Intent(this, Class.forName("com.example.users_6425.projetescape." + ID));

        startActivity(intent);
    }
}
