package com.ubb.sita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ubb.sitaubb.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /** Called when the user taps the Send button */
    public void launchPendadaran(View view) {

        Log.d("coba", "Button  pendadaran clicked!");
        Intent intent = new Intent(this, Tampil_jadwal_activity.class);
        String message = "3";
        String judulapp = "Pendadaran";
        intent.putExtra("jenis", message);
        intent.putExtra("judulapp", judulapp);
        startActivity(intent);
    }
    /** Called when the user taps the Send button */
    public void launchSempro(View view) {

        Log.d("coba", "Button sempro clicked!");
        Intent intent = new Intent(this, Tampil_jadwal_activity.class);
        String message = "1";
        String judulapp = "Sempro";
        intent.putExtra("jenis", message);
        intent.putExtra("judulapp", judulapp);
        startActivity(intent);
    }
    /** Called when the user taps the Send button */
    public void launchSemhas(View view) {

        Log.d("coba", "Button semhas clicked!");
        Intent intent = new Intent(this, Tampil_jadwal_activity.class);
        String message = "2";
        String judulapp = "Semhas";
        intent.putExtra("jenis", message);
        intent.putExtra("judulapp", judulapp);
        startActivity(intent);
    }


}
