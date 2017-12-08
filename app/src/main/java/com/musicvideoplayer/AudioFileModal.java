package com.musicvideoplayer;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

/**
 * AudioFileModal - modal class to hold audio file data.
 *
 * Created by Ashok on 6/12/17.
 */

public class AudioFileModal implements Parcelable{
  private String id;
  private String data;
  private String name;
  private String artist;
  private String album;

  public AudioFileModal(String id, String data, String name, String artist, String album) {
    this.id = id;
    this.data = data;
    this.name = name;
    this.artist = artist;
    this.album = album;
  }

  public String getId() {
    return id;
  }

  public String getData() {
    return data;
  }

  public String getName() {
    return name;
  }

  public String getArtist() {
    return artist;
  }

  public String getAlbum() {
    return album;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(data);
    parcel.writeString(name);
    parcel.writeString(artist);
    parcel.writeString(album);
  }

  private AudioFileModal(Parcel parcel) {
    this.id = parcel.readString();
    this.data = parcel.readString();
    this.name = parcel.readString();
    this.artist = parcel.readString();
    this.album = parcel.readString();
  }

  public static final Creator<AudioFileModal> CREATOR = new Creator<AudioFileModal>() {
    @Override public AudioFileModal createFromParcel(Parcel parcel) {
      return new AudioFileModal(parcel);
    }

    @Override public AudioFileModal[] newArray(int i) {
      return new AudioFileModal[0];
    }
  };

}
