package com.example.users_6425.projetescape;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by users-6425 on 19/03/2018.
 */

public class N14 extends AppCompatActivity {
    private MyDBAdapter dbAdapter;
    private String next;
    private NumberPicker tenPicker, unitPicker;
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_count);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String text = "";
        Button N3 = (Button) findViewById(R.id.N3);
        N3.setText("Valider");
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        try {
            InputStream is = getAssets().open("14.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(text);


        this.tenPicker = (NumberPicker) findViewById(R.id.ten_picker);
        this.tenPicker.setMinValue(0);
        this.tenPicker.setMaxValue(9);
        this.tenPicker.setValue(0);
        this.tenPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.v("ten OnValueChange", "newVal = " + newVal);
            }
        });

        this.unitPicker = (NumberPicker) findViewById(R.id.unit_picker);
        this.unitPicker.setMinValue(0);
        this.unitPicker.setMaxValue(9);
        this.unitPicker.setValue(0);
        this.unitPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.v("unit OnValueChange", "newValU = " + newVal);
            }
        });
    }

    public void setNumber(int n) {
        this.n = n;
    }

    public void onClick(View view) throws ClassNotFoundException {
        n = 10 * tenPicker.getValue() + unitPicker.getValue();
        if (n == 40) {
            next = "N17";

        } else {
            next = "N16";
        }
        dbAdapter.updateScore(dbAdapter.getAllScores().get(0).getID(), next, 0);
        System.out.println(dbAdapter.getAllScores().get(0).getName());
        System.out.println(n);
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
