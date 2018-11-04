package com.androidcss.jsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /** Called when the user taps the Send button */
    public void launchSecondActivity(View view) {

        Log.d("coba", "Button clicked!");
//        Intent intent = new Intent(this, Tampil_jadwal_activity.class);
//        TextView editText = (TextView) findViewById(R.id.textJudulApp);
//        String message = editText.getText().toString();
//        intent.putExtra("jenis", message);
//        startActivity(intent);
    }


}
