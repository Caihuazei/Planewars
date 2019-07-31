package com.lh;

import com.lh.main.GameFram;
import until.DataStore;

public class GameStart {
    public static void main(String[] args) {
        GameFram gameFram = new GameFram();
        DataStore.put("gameFram",gameFram);
        gameFram.init();
    }
}
