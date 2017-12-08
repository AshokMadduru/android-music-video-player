package com.musicvideoplayer;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.io.FileDescriptor;
import java.util.List;

/**
 * SongsAdapter - Recycler view adapter class to populate songs.
 *
 * Created by Ashok on 8/12/17.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

  private List<AudioFileModal> songsList;

  private Context context;

  public SongsAdapter(List<AudioFileModal> songsList) {
    this.songsList = songsList;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    context = parent.getContext();
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_song, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.songName.setText(songsList.get(position).getName());
    holder.songDetails.setText(songsList.get(position).getArtist());
    holder.songDuration.setText(songsList.get(position).getDuration());
    holder.songImage.setImageBitmap(getAlbumArt(context, songsList.get(position).getAlbumId()));
  }

  private Bitmap getAlbumArt(Context context, Long album_id) {
    Bitmap bm = null;
    try {
      final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

      Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

      ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "r");

      if (pfd != null) {
        FileDescriptor fd = pfd.getFileDescriptor();
        bm = BitmapFactory.decodeFileDescriptor(fd);
      }
    } catch (Exception e) {
      e.printStackTrace();
      bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
    }
    return bm;
  }

  @Override public int getItemCount() {
    return songsList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.song_image) ImageView songImage;
    @BindView(R.id.song_name) TextView songName;
    @BindView(R.id.song_details) TextView songDetails;
    @BindView(R.id.song_duration) TextView songDuration;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
