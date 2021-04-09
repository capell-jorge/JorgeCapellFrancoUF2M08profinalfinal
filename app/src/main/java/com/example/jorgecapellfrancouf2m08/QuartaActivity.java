package com.example.jorgecapellfrancouf2m08;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.Button;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import  androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import java.io.IOException;

public class QuartaActivity extends AppCompatActivity  {
    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btn_recorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarta);

        Button buttonmenu4 = (Button) findViewById(R.id.buttonmenu4);
        buttonmenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMenu();
            }
        });

        btn_recorder = (Button)findViewById(R.id.btn_rec);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(QuartaActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

    }
    public void volverMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Recorder(View view){
        if(grabacion == null){
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            grabacion.setOutputFile(archivoSalida);

            try{
                grabacion.prepare();
                grabacion.start();
            } catch (IOException e){
            }


            Toast.makeText(getApplicationContext(), "Grabando...", Toast.LENGTH_SHORT).show();
        } else if(grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;

            Toast.makeText(getApplicationContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    public void reproducir(View view) {

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        } catch (IOException e){
        }

        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Reproduciendo audio", Toast.LENGTH_SHORT).show();
    }
}
