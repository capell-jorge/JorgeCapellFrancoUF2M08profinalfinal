package com.example.jorgecapellfrancouf2m08;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ThirdActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button buttonmenu3 = (Button) findViewById(R.id.buttonmenu3);
        buttonmenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMenu();
            }
        });


        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
    public void volverMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
