package com.ffmpeg.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by huangyanan on 2017/9/8.
 */

public class FFmpegRepoter {

    public static final int MAX_UPLOAD_FILE_SIZE = 5;

    public static void reportLogFile(final Context context) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                File uploadFile = compressLogFiles(context);
                if (uploadFile == null) return null;
                //upload log file to analyse
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private static File compressLogFiles(Context context) {
        File zipLogDir = new File(context.getExternalCacheDir(), "ffmpeg_log_zip");
        if (!zipLogDir.exists()) {
            zipLogDir.mkdirs();
        }

        File[] zipFiles = zipLogDir.listFiles();
        if (zipFiles != null && zipFiles.length > 0) {
            for (File f : zipFiles) {
                f.delete();
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        File zipFile = new File(zipLogDir, dateFormat.format(System.currentTimeMillis()) + ".zip");

        File logDir = new File(context.getExternalCacheDir(), "ffmpeg_log");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        File[] files = logDir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        try {
            zipFiles(zipFile, files);
        } catch (Exception e) {
            e.printStackTrace();
            zipFile.delete();
            return null;
        }
        return zipFile;
    }

    private static void zipFiles(File zipFile, File[] inputFiles) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        int max = inputFiles.length > MAX_UPLOAD_FILE_SIZE ? MAX_UPLOAD_FILE_SIZE : inputFiles.length;
        for (int i = 0; i < max; i++) {
            File file = inputFiles[i];
            out.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream in = new FileInputStream(file);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b);
            }
            bi.close();
            in.close();
            file.delete();
        }
        bo.close();
        out.close();
    }
}
