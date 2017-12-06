package com.musicvideoplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  int REQUEST_CODE_READ_EXTERNAL_STORAGE = 101;

  private final String[] permissions = { android.Manifest.permission.READ_EXTERNAL_STORAGE };

  private ArrayList<String> permissionsToRequest = new ArrayList<>();

  private List<AudioFileModal> audioFilesList = new ArrayList<>();


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    checkPermissionsAndGetData();
  }

  private void checkPermissionsAndGetData() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      checkMediaPermissions(permissions[0]);
    } else {
      new AudioPlayerHelper().getAudioFilesData(this, audioFilesObserver());
    }
  }

  @TargetApi(23) private void checkMediaPermissions(String permission) {
    if (this.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
      new AudioPlayerHelper().getAudioFilesData(this, audioFilesObserver());
    } else {
      permissionsToRequest.add(permissions[0]);
      requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
          REQUEST_CODE_READ_EXTERNAL_STORAGE);
    }
  }

  @TargetApi(23)
  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    int count = 0;
    for (int result : grantResults) {
      if (result == -1) {
        count++;
        break;
      }
    }

    if (count > 0) {
      Toast.makeText(MainActivity.this,
          "You cannot play music or view videos without proper permissions", Toast.LENGTH_LONG)
          .show();
    } else {
      permissionsToRequest.clear();
      checkPermissionsAndGetData();
    }
  }

  private Observer<AudioFileModal> audioFilesObserver() {
    return new Observer<AudioFileModal>() {
      @Override public void onSubscribe(Disposable d) {

      }

      @Override public void onNext(AudioFileModal audioFileModal) {
        audioFilesList.add(audioFileModal);
      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onComplete() {

      }
    };
  }
}
