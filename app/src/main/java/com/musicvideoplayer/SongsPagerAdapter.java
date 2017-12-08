package com.musicvideoplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * SongsPagerAdapter - PagerAdapter class to populate different fragments.
 *
 * Created by Ashok on 8/12/17.
 */

public class SongsPagerAdapter extends FragmentStatePagerAdapter {
  private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
  private int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

  private ArrayList<AudioFileModal> songsList = new ArrayList<>();

  // Build a Constructor and assign the passed Values to appropriate values in the class
  public SongsPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, ArrayList<AudioFileModal> songsList) {
    super(fm);

    this.Titles = mTitles;
    this.NumbOfTabs = mNumbOfTabsumb;
    this.songsList = songsList;

  }

  //This method return the fragment for the every position in the View Pager
  @Override
  public Fragment getItem(int position) {
    Fragment fragment = null;
    switch (position) {
      case 0:
        fragment = AllSongsFragment.newInstance(songsList);
        break;
      case 1:
        fragment = AllSongsFragment.newInstance(songsList);
        break;
      case 2:
        fragment =  AllSongsFragment.newInstance(songsList);
        break;
    }
    return fragment;
  }

  public void updateSongsList(AudioFileModal audioFileModal) {
    songsList.add(audioFileModal);
    notifyDataSetChanged();
  }

  // This method return the titles for the Tabs in the Tab Strip

  @Override
  public CharSequence getPageTitle(int position) {
    return Titles[position];
  }

  // This method return the Number of tabs for the tabs Strip

  @Override
  public int getCount() {
    return NumbOfTabs;
  }
}
