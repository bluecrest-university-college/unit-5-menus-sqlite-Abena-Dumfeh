package com.maame.words;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch ( id ) {
            case R.id.action_add:
                Log.w( "MainActivity", "Add selected" );
                Intent insertIntent = new Intent( this, insertActivity.class );
                this.startActivity( insertIntent );
                return true;
            case R.id.action_delete:
                Log.w( "MainActivity", "Delete selected" );
                Intent deleteIntent = new Intent( this, DeleteActivity.class );
                this.startActivity( deleteIntent );
                return true;
            case R.id.action_update:
                Log.w( "MainActivity", "Update selected" );
                Intent updateIntent = new Intent( this, UpdateActivity.class );
                this.startActivity( updateIntent );
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
         //   return true;
       // }


    }
}
