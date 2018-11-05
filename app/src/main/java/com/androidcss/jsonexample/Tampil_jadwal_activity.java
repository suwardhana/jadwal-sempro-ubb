package com.androidcss.jsonexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Tampil_jadwal_activity extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVJadwalList;
    private AdapterJadwal mAdapter;
    public String tipe = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tampil_jadwal);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("jenis");
        String judulapp = intent.getStringExtra("judulapp");
        TextView textJudulApp;
        textJudulApp = findViewById(R.id.textJudulApp);
        textJudulApp.setText("Jadwal " + judulapp);
        tipe = message;
        Log.d("tipe", tipe);
        Log.d("message",message);
        //Make call to AsyncTask
        new AsyncLogin().execute();
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Tampil_jadwal_activity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                Log.d("tipe dalam async", tipe);

                url = new URL("https://sita.ubb.ac.id/api/get_jadwal_by_api/"+tipe);
                Log.d("alamat url", "https://sita.ubb.ac.id/api/get_jadwal_by_api/"+tipe);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();
                Log.d("sebelum masuk result",String.valueOf(response_code));
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    Log.d("isi resultt",result.toString());
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<DataJadwal> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    DataJadwal jadwalData = new DataJadwal();
                    jadwalData.jenis = json_data.getString("jenis");
                    Log.d("jenisdalamjson",json_data.getString("jenis"));
                    jadwalData.judul = json_data.getString("judul");
                    jadwalData.nama = json_data.getString("nama");
                    jadwalData.waktu = json_data.getString("waktu");
                    jadwalData.tanggal = json_data.getString("tanggal");
                    jadwalData.ruang = json_data.getString("ruang");
                    jadwalData.pembimbing1 = json_data.getString("pembimbing1");
                    jadwalData.pembimbing2 = json_data.getString("pembimbing2");
                    jadwalData.penguji1 = json_data.getString("penguji1");
                    jadwalData.penguji2 = json_data.getString("penguji2");
                    jadwalData.fotoprofil = json_data.getString("foto_profil");
                    data.add(jadwalData);
                }

                // Setup and Handover data to recyclerview
                mRVJadwalList = (RecyclerView)findViewById(R.id.jadwalList);
                mAdapter = new AdapterJadwal(Tampil_jadwal_activity.this, data);
                mRVJadwalList.setAdapter(mAdapter);
                mRVJadwalList.setLayoutManager(new LinearLayoutManager(Tampil_jadwal_activity.this));

            } catch (JSONException e) {
                Toast.makeText(Tampil_jadwal_activity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
