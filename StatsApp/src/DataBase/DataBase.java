package DataBase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
    private static ArrayList<Team> teams = new ArrayList<>();

    public static ArrayList<Team> getTeams() {
        return teams;
    }

    public static void addTeam(String name, ArrayList <Player> players) {
        teams.add(new Team(name, players));
        serializeDatabase();
    }

    public static void removeTeam(Team team) {
        teams.remove(team);
        serializeDatabase();
    }

    public static void serializeDatabase() {
        Gson gson = new Gson();
        String json = gson.toJson(teams);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("teams.json")))) {
            bw.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deserializeDatabase() {
        try { Scanner skaner = new Scanner(new File("teams.json"));
            String json = skaner.nextLine();
            Type listType = new TypeToken<ArrayList<Team>>() {
            }.getType();
            teams = new Gson().fromJson(json, listType);
        } catch (Exception e) {}
    }
}
