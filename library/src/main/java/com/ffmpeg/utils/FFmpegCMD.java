package com.ffmpeg.utils;

import android.support.annotation.Keep;
import android.util.Log;


/**
 * Created by huangyanan on 2017/7/30.
 */
@Keep
public class FFmpegCMD {


    static {
        System.loadLibrary("ffmpeg");
        System.loadLibrary("ffmpeg_invoker");
    }

    private static native int execCmd(String cmd[], FFmpegCMD fFmpegCMD);

    public static native int setDebugLevel(int level);

    @Keep
    public void processCallback(final int time) {
        Log.d("FFmpegCMD", "ffmpeg has process time : " + time);
        if (mListener != null) {
            mListener.hasProcessTime(time);
        }
    }

    public FFmpegCMD() {

    }

    private FFmpegProcessCallback mListener;

    public int runFFmpegCmd(String cmd, FFmpegProcessCallback listener) {
        Log.e("FFmpegCMD", cmd);
        mListener = listener;
        String regulation = "[ \\t]+";
        final String[] split = cmd.split(regulation);
        return execCmd(split, FFmpegCMD.this);
    }

    public void release() {
        mListener = null;
    }

}
