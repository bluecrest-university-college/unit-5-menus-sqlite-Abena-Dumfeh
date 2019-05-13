package com.maame.words;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class insertActivity extends AppCompatActivity {
    private DatabaseManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbmanager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);

    }
        public void insert(View v){
        //Retrieve entered words
            EditText edittext1 = (EditText) findViewById(R.id.correctID);
            EditText edittext2 = (EditText) findViewById(R.id.wrongID);
            String correct = edittext1.getText().toString();
            String wrong = edittext2.getText().toString();

            //insert new word
             words wordS = new words(correct, wrong);
             dbmanager.insert(wordS);

            Toast.makeText(this, "Correct Word Added", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Correct Word Added", Toast.LENGTH_LONG).show();

            edittext1.setText("");
            edittext2.setText("");

        }

        public void goBack(View v){
        this.finish();
        }

}
