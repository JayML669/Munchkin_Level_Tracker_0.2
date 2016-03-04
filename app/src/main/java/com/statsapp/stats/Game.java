package com.statsapp.stats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2/23/2016.
 */
public class Game {

    public List<Player> player=new ArrayList<Player>();
    int id;
public String toString(){

  return"Game "+id;
};
    public void addPlayer(){
        Player newPlayer = new Player();
        newPlayer.pid=this.player.size() + 1;

    }
}
