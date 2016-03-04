package com.statsapp.stats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2/23/2016.
 */
public class Player {
    public List<StatAmt> stats=new ArrayList<StatAmt>();

    int pid;
    public String toString() {

        return "P" + pid;
    };
}
