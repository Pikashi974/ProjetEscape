package com.example.users_6425.projetescape;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    public String ID;
    private MyDBAdapter dbAdapter;
    private MyArrayAdapter adapter;
    private ListView mListView;
    private String m_Text = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
    }

    public void NewName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        m_Text = input.getText().toString();
    }


    public void onClick(View view) throws ClassNotFoundException {
        String next;
        if (view.getId() == R.id.N1) {
            next = "N1";
            dbAdapter.updateScore(dbAdapter.getAllScores().get(0).getID(), next, 0);
            System.out.println(dbAdapter.getAllScores().get(0).getName());
        } else if (view.getId() == R.id.UnderScore) {
            next = "UnderScore";
        } else if (view.getId() == R.id.continuer) {
            next = dbAdapter.getAllScores().get(0).getName();
            System.out.println(next);
        } else {
            next = "MainActivity";
        }
        Intent intent = new Intent(this, Class.forName("com.example.users_6425.projetescape." + next));
        intent.putExtra("id", dbAdapter.getAllScores().get(0).getID());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }
}
