package com.chinanetcenter.streampusherdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitmapSaveTask extends AsyncTask<Bitmap, Void, Bitmap> {
    private Context mContext = null;
    
    public BitmapSaveTask(Context context) {
        super();
        if(context == null){
            throw new IllegalArgumentException("BitmapSaveTask context must not be null");
        }
        mContext = context;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... params) {

      if (params == null || params.length < 1 || params[0] == null) {
        return null;
      }

      Bitmap bitmap = params[0];

      String filePath = null;
      if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable())
      {
          File root = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
          if (root != null) {
              File destDir = new File(root, "/screen/");
              if(!destDir.exists()) {
                  if(!destDir.mkdir()) {
                      Log.e("BitmapSaveTask", "the dirName : " + destDir + " create failure" );
                      return null;
                  }
              }
              filePath = destDir.getPath();
          }
      }
      if(filePath == null) {
          return null;
      }
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SSS");

      String fileName = simpleDateFormat.format(new Date()) + ".jpg";
      File fileImage = null;
      FileOutputStream outputStream = null;
      if (bitmap != null) {
        try {
          fileImage = new File(filePath, fileName);
          if (!fileImage.exists()) {
            fileImage.createNewFile();
          }
          outputStream = new FileOutputStream(fileImage);
          if (outputStream != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(fileImage);
            media.setData(contentUri);
            mContext.sendBroadcast(media);
          }
        } catch (FileNotFoundException e) {
          e.printStackTrace();
          fileImage = null;
        } catch (IOException e) {
          e.printStackTrace();
          fileImage = null;
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
      }

      if (fileImage != null) {
        return bitmap;
      }
      return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      super.onPostExecute(bitmap);
    }
  }