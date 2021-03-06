package com.example.jay.localsavetest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerStatsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    public static PlayerStatsFragment newInstance(String paramString1, String paramString2) {
        PlayerStatsFragment localPlayerStatsFragment = new PlayerStatsFragment();
        Bundle localBundle = new Bundle();
        localBundle.putString("param1", paramString1);
        localBundle.putString("param2", paramString2);
        localPlayerStatsFragment.setArguments(localBundle);
        return localPlayerStatsFragment;
    }

    public void onAttach(Context paramContext) {
        super.onAttach(paramContext);
        if ((paramContext instanceof OnFragmentInteractionListener)) {
            this.mListener = ((OnFragmentInteractionListener) paramContext);
            return;
        }
        throw new RuntimeException(paramContext.toString() + " must implement OnFragmentInteractionListener");
    }

    public void onButtonPressed(Uri paramUri) {
        if (this.mListener != null) {
            this.mListener.onFragmentInteraction(paramUri);
        }
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString("param1");
            this.mParam2 = getArguments().getString("param2");
        }
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        return paramLayoutInflater.inflate(R.layout.fragment_player_stats, paramViewGroup, false);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public static abstract interface OnFragmentInteractionListener {
        public abstract void onFragmentInteraction(Uri paramUri);
    }
}