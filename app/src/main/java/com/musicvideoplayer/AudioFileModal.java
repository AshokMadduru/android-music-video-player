package com.musicvideoplayer;

/**
 * AudioFileModal - modal class to hold audio file data.
 *
 * Created by Ashok on 6/12/17.
 */

public class AudioFileModal {
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

  public void setId(String id) {
    this.id = id;
  }

  public void setData(String data) {
    this.data = data;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void setAlbum(String album) {
    this.album = album;
  }
}
