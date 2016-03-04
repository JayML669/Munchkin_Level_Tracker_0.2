package com.example.jay.localsavetest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;
import com.statsapp.stats.App;
import com.statsapp.stats.Game;
import com.statsapp.stats.Player;
import com.statsapp.stats.StatsAppState;
import java.util.List;

public class PlayerPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private int gameNumber;
    private int numberOfTabs = 0;
    boolean showToast = false;
    private StatsAppState statsAppState;

    public PlayerPagerAdapter(FragmentManager paramFragmentManager, Context paramContext, StatsAppState paramStatsAppState, int paramInt)
    {
        super(paramFragmentManager);
        this.context = paramContext;
        this.statsAppState = paramStatsAppState;
        this.numberOfTabs = ((Game)paramStatsAppState.games.games.get(paramInt)).player.size();
        toast("MainPagerAdapter:constructor");
    }

    public int getCount()
    {
        return this.numberOfTabs;
    }

    public Fragment getItem(int paramInt)
    {
        return new Fragment();
    }

    public CharSequence getPageTitle(int paramInt)
    {
        return ((Player)((Game)this.statsAppState.games.games.get(this.gameNumber)).player.get(paramInt)).toString();
    }

    public void toast(String paramString)
    {
        if (this.showToast) {
            Toast.makeText(this.context, paramString, Toast.LENGTH_SHORT).show();
        }
    }
}