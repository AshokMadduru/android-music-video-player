package com.musicvideoplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllSongsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllSongsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllSongsFragment extends Fragment {

  private OnFragmentInteractionListener mListener;

  private List<AudioFileModal> songsList = new ArrayList<>();

  private Context activityContext;

  @BindView(R.id.songs_recycler) RecyclerView songsRecyclerView;

  public AllSongsFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment AllSongsFragment.
   */
  public static AllSongsFragment newInstance(ArrayList<AudioFileModal> songsList) {
    AllSongsFragment fragment = new AllSongsFragment();
    Bundle args = new Bundle();
    args.putParcelableArrayList("songs", songsList);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey("songs")) {
      songsList = getArguments().getParcelableArrayList("songs");
    }
  }

  @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_all_songs, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    SongsAdapter songsAdapter = new SongsAdapter(songsList);
    songsRecyclerView.setLayoutManager(
        new LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false));
    songsRecyclerView.setAdapter(songsAdapter);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    activityContext = context;
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
