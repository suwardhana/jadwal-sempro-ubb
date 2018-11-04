package com.androidcss.jsonexample;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

public class AdapterJadwal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataJadwal> data= Collections.emptyList();
    DataJadwal current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterJadwal(Context context, List<DataJadwal> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_jadwal, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataJadwal current=data.get(position);
        myHolder.textNama.setText(current.nama);
        myHolder.textTanggal.setText(current.tanggal);
        myHolder.textJudul.setText(current.judul);
        myHolder.textWaktu.setText("Jam : " + current.waktu);
        myHolder.textRuang.setText("Ruang : " + current.ruang);
        myHolder.textJenis.setText(current.jenis);
        myHolder.textPembimbing1.setText("Pembimbing 1 : " + current.pembimbing1);
        myHolder.textPembimbing2.setText("Pembimbing 2 : " + current.pembimbing2);
        myHolder.textPenguji1.setText("Penguji 1 : " + current.penguji1);
        myHolder.textPenguji2.setText("Penguji 2 : " + current.penguji2);
        myHolder.textTanggal.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        // load image into imageview using glide
        Glide.with(context).load("http://192.168.100.8/deny/assets/img/mahasiswa/" + current.fotoprofil)
                .placeholder(R.drawable.ic_img_error)
                .error(R.drawable.ic_img_error)
                .into(myHolder.ivFprofil);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textNama;
        ImageView ivFprofil;
        TextView textTanggal;
        TextView textJudul;
        TextView textWaktu;
        TextView textRuang;
        TextView textJenis;
        TextView textPembimbing1;
        TextView textPembimbing2;
        TextView textPenguji1;
        TextView textPenguji2;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textNama= (TextView) itemView.findViewById(R.id.textNama);
            ivFprofil = (ImageView) itemView.findViewById(R.id.ivFprofil);
            textTanggal = (TextView) itemView.findViewById(R.id.textTanggal);
            textJudul = (TextView) itemView.findViewById(R.id.textJudul);
            textWaktu = (TextView) itemView.findViewById(R.id.textWaktu);
            textRuang = (TextView) itemView.findViewById(R.id.textRuang);
            textJenis = (TextView) itemView.findViewById(R.id.textJenis);
            textPembimbing1 = (TextView) itemView.findViewById(R.id.textPembimbing1);
            textPembimbing2 = (TextView) itemView.findViewById(R.id.textPembimbing2);
            textPenguji1 = (TextView) itemView.findViewById(R.id.textPenguji1);
            textPenguji2 = (TextView) itemView.findViewById(R.id.textPenguji2);
        }

    }

}
