package com.ffmpeg.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by huangyanan on 2017/9/8.
 */

public class FFmpegRepoter {

    public static void init(Context context) {
        File logDir = new File(context.getExternalCacheDir(), "log");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        //do report logic



    }

}
