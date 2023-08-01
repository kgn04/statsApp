package DataBase;

import java.util.ArrayList;

public class Raport {
    private String enemyName;
    private int day;
    private int month;
    private int year;
    private String arena;
    private ArrayList<PlayerAction> playerActions;
    private int ourSets;
    private int enemySets;
    private ArrayList<Integer> ourPoints;
    private ArrayList<Integer> enemyPoints;

    public String getEnemyName() {
        return enemyName;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getArena() {
        return arena;
    }

    public ArrayList<PlayerAction> getPlayerActions() {
        return playerActions;
    }

    public int getOurSets() {
        return ourSets;
    }

    public int getEnemySets() {
        return enemySets;
    }

    public ArrayList<Integer> getOurPoints() {
        return ourPoints;
    }

    public ArrayList<Integer> getEnemyPoints() {
        return enemyPoints;
    }

    public Raport(String enemyName, int day, int month, int year, String arena, ArrayList<PlayerAction> playerActions, int ourSets, int enemySets, ArrayList<Integer> ourPoints, ArrayList<Integer> enemyPoints) {
        this.enemyName = enemyName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.arena = arena;
        this.playerActions = playerActions;
        this.ourSets = ourSets;
        this.enemySets = enemySets;
        this.ourPoints = ourPoints;
        this.enemyPoints = enemyPoints;
    }

    public Raport() {
        enemyName = "";
        day = 0;
        month = 0;
        year = 0;
        arena = "";
        playerActions = null;
        ourSets = 0;
        enemySets = 0;
        ourPoints = null;
        enemyPoints = null;
    }

    @Override
    public String toString() {
        return enemyName+"_"+day+"-"+month+"-"+year;
    }

}
