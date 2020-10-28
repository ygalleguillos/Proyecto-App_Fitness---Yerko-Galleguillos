package com.example.app_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Info_act extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act2);

        videoView = (VideoView)findViewById(R.id.vd);  // llamo el elemento por id.


        // Es colocar el video dentro del videoView a través de su ruta.

        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta);  // ruta en el content provider.
        videoView.setVideoURI(uri);  // le pasamos la ruta al video.

        // Control de navegación

        MediaController media = new MediaController(this); // creamos el control.
        videoView.setMediaController(media); // pasamos el control al videoview.

    }
}