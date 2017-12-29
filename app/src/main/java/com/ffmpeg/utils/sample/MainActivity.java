package com.ffmpeg.utils.sample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ffmpeg.utils.FFmpegCMD;
import com.ffmpeg.utils.FFmpegProcessCallback;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File("/storage/emulated/0/Android/data/"+getPackageName()+"/log");
        file.mkdirs();


    }

    public void hello(View view) {
        FFmpegCMD.setDebugLevel(1);

//        final String cmd = "ffmpeg  -i /storage/emulated/0/Movies/test.mp4 -i /storage/emulated/0/Android/data/com.beibo.yuerbao/cache/music/3f012da9ea8766bf8a469a215dc52bb6  -filter_complex [0:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=1.0[a0];[1:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=1.0[a1];[a0][a1]amix=inputs=2:duration=first[aout] -map [aout] -ac 2 -c:v copy -map 0:v:0 -max_muxing_queue_size 9999 -movflags +faststart -r 30 -y /storage/emulated/0/yuerbao/video/result.mp4";


        final String cmd = "ffmpeg  -i /storage/emulated/0/Movies/test.mp4 -i /storage/emulated/0/Android/data/com.beibo.yuerbao/cache/music/3f012da9ea8766bf8a469a215dc52bb6  -filter_complex [0:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=1.0[a0];[1:a]aformat=sample_fmts=fltp:sample_rates=44100:channel_layouts=stereo,volume=1.0[a1];[a0][a1]amix=inputs=2:duration=first[aout] -map [aout] -ac 2 -c:v copy -map 0:v:0 -max_muxing_queue_size 9999 -movflags +faststart -r 30 -y /storage/emulated/0/yuerbao/video/result.mp4";

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                FFmpegCMD fFmpegCMD = new FFmpegCMD();
                int result = fFmpegCMD.runFFmpegCmd(cmd, new FFmpegProcessCallback() {
                    @Override
                    public void hasProcessTime(int time) {
                        Log.e("huangyanan", "time : " + time);
                    }
                });

                Log.e("huangyanan", "time : " + result);
                return null;
            }
        }.execute();
    }
}
