package com.statsapp.stats;

/**
 * Created by Jay on 2/23/2016.
 */
public class StatsAppState {


    PlayerStats AdminStats;//String for player stats
    public App games;//games
    
    public void addGame(){
        Game newGame = new Game();
       if (games.games.size() == 0) newGame.id = 1;
        int currentHighest = 0;
        for (Game G : games.games) {
            if(currentHighest < G.id)currentHighest = G.id;


        }
        newGame.id = currentHighest + 1;

        games.games.add(newGame);

    }


}
