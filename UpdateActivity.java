package com.maame.words;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbmanager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbmanager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<words> wordS = dbmanager.selectAll();
        if (wordS.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(wordS.size());
            grid.setColumnCount(4);

            // create arrays of components
            TextView[] corrects = new TextView[wordS.size()];
            EditText[][] mispeltandcorrect = new EditText[wordS.size()][1];
            Button[] buttons = new Button[wordS.size()];
            ButtonHandler bh = new ButtonHandler();

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (words word : wordS) {
                corrects[i] = new TextView(this);
                corrects[i].setGravity(Gravity.CENTER);
                corrects[i].setText("" + word.getCorrectWord());

                // create the two EditTexts for the candy's name and price
                mispeltandcorrect[i][0] = new EditText(this);
                mispeltandcorrect[i][1] = new EditText(this);
                mispeltandcorrect[i][0].setText(word.getCorrectWord());
                mispeltandcorrect[i][1].setText("" + word.getWrongWord());
                mispeltandcorrect[i][1]
                        .setInputType(InputType.TYPE_CLASS_NUMBER);
                mispeltandcorrect[i][0].setText(word.getCorrectWord());
                mispeltandcorrect[i][1].setText(word.getWrongWord() + 1);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].seCorrecttword(word.getCorrectWord());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add the elements to grid
                grid.addView(corrects[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(mispeltandcorrect[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(mispeltandcorrect[i][1], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {

            String word = v.getword();
            EditText correct = (EditText) findViewById(10 * correctId);
            EditText wrong = (EditText) findViewById(10 * wrongId + 1);
            String correctString = correct.getText().toString();
            String wrongString = wrong.getText().toString();

            // update word in database
            try {
                String = String.valueOf((correctString));
                dbmanager.updateByCORRECt(correctString, wrongString);
                Toast.makeText(UpdateActivity.this, "Candy updated",
                        Toast.LENGTH_SHORT).show();

                // update screen
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this,
                        "word error", Toast.LENGTH_LONG).show();
            }
        }
    }
}

