package com.maame.words;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbmanager = new DatabaseManager(this);
        updateView();
        /*setContentView(R.layout.activity_delete);*/
    }

    public void updateView() {
        ArrayList<words> wordS = dbmanager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (words word : wordS) {
            RadioButton rb = new RadioButton(this);
            rb.setText(word.getCorrectWord());
            rb.setText(word.toString());
            group.addView(rb);
        }
        //event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        Button backButton = new Button(this);
        backButton.setText(R.string.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }

        });
        scrollView.addView(group);
        layout.addView(scrollView);

        // add back button at bottom
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private abstract class RadioButtonHandler
            implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, String checkedcorrect) {
            // delete candy from database
            dbmanager.deleteByCORRECt(checkedcorrect);
            Toast.makeText( DeleteActivity.this, "Word deleted",
                    Toast.LENGTH_SHORT ).show( );

            // update screen
            updateView();
        }
    }

}





