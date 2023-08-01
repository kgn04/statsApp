package DataBase;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private ArrayList<Player> rozgrywajacy;
    private ArrayList<Player> atakujacy;
    private ArrayList<Player> srodkowi;
    private ArrayList<Player> przyjmujacy;
    private ArrayList<Player> libero;
    private ArrayList<Raport> raports;

    public Team(String name, ArrayList<Player> players) {
        this.name = name;
        this.players = players;
        rozgrywajacy = new ArrayList<>();
        atakujacy = new ArrayList<>();
        srodkowi = new ArrayList<>();
        przyjmujacy = new ArrayList<>();
        libero = new ArrayList<>();
        raports = new ArrayList<>();
        for (Player player : players)
            switch (player.getPosition()) {
                case "Rozgrywajacy" -> rozgrywajacy.add(player);
                case "Atakujacy" -> atakujacy.add(player);
                case "Srodkowy" -> srodkowi.add(player);
                case "Przyjmujacy" -> przyjmujacy.add(player);
                case "Libero" -> libero.add(player);
            }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getRozgrywajacy() {
        return rozgrywajacy;
    }

    public ArrayList<Player> getAtakujacy() {
        return atakujacy;
    }

    public ArrayList<Player> getSrodkowi() {
        return srodkowi;
    }

    public ArrayList<Player> getPrzyjmujacy() {
        return przyjmujacy;
    }

    public ArrayList<Player> getLibero() {
        return libero;
    }

    public ArrayList<Raport> getRaports() {
        return raports;
    }

    public void addRaport(Raport raport) {
        raports.add(raport);
        DataBase.serializeDatabase();
    }
}
