package com.musicvideoplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;

/**
 * SongsAdapter - Recycler view adapter class to populate songs.
 *
 * Created by Ashok on 8/12/17.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

  private List<AudioFileModal> songsList;

  public SongsAdapter(List<AudioFileModal> songsList) {
    this.songsList = songsList;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_song, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.songName.setText(songsList.get(position).getName());
    holder.songDetails.setText(songsList.get(position).getData());
  }

  @Override public int getItemCount() {
    return songsList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.song_image) ImageView songImage;
    @BindView(R.id.song_name) TextView songName;
    @BindView(R.id.song_details) TextView songDetails;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
