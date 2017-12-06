package com.musicvideoplayer;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * AudioPlayerHelper - Helper class to handle audio files.
 *
 * Created by Ashok on 6/12/17.
 */

public class AudioPlayerHelper {

  private String[] audioFileContents = new String[] {
      MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST,
      MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DISPLAY_NAME
  };

  private Cursor getAudioFilesCursor(Context activityContext) {
    return activityContext.getContentResolver()
        .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, audioFileContents, "1=1", null, null);
  }

  public void getAudioFilesData(Context activityContext,
      Observer<AudioFileModal> audioFileObserver) {
    audioFilesObservable(activityContext).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(audioFileObserver);
  }

  private Observable<AudioFileModal> audioFilesObservable(Context activityContext) {
    return Observable.create(e -> {
      Cursor cursor = getAudioFilesCursor(activityContext);
      if (cursor.getCount() > 0) {
        cursor.moveToFirst();
        do {
          AudioFileModal fileData = new AudioFileModal(
              cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),
              cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)),
              cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)),
              cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
              cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
          e.onNext(fileData);
        } while (cursor.moveToNext());
      }
      e.onComplete();
    });
  }
}
