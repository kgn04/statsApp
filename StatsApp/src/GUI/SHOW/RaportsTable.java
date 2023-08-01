package GUI.SHOW;

import DataBase.PlayerAction;
import DataBase.Raport;
import DataBase.Team;
import GUI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RaportsTable {
    private static JFrame frame;

    private static JPanel panel;
    private static JScrollPane scrollpane;
    private static JTable table;

    private static JComboBox choice;
    private static JButton BACK;
    private static JButton OK;

    private Team team;
    private String raport;
    private String raportUnit;

    private static Team chosenTeam;
    private static Raport chosenRaport;

    private static String realChoice;

    public static void setRealChoice() {
        realChoice = "Cala druzyna";
    }

    public static String[][] teamRaport() {
        String[][] result = new String[5][7];
        for (int i=0; i<4; i++)
            result[i][0] = ""+i;
        result[4][0] = "Dokladnosc";

        for (int i=0; i<5; i++)
            for (int j=1; j<7; j++)
                result[i][j]="0";

        int rating;
        for (PlayerAction playerAction : chosenRaport.getPlayerActions()) {
            if (playerAction!=null)
                switch (playerAction.getActionName()) {
                    case "Przyjecie" -> result[playerAction.getRating()][1] = ""+(Integer.parseInt(result[playerAction.getRating()][1])+1);
                    case "Zagrywka" -> result[playerAction.getRating()][2] = ""+(Integer.parseInt(result[playerAction.getRating()][2])+1);
                    case "Obrona" -> result[playerAction.getRating()][3] = ""+(Integer.parseInt(result[playerAction.getRating()][3])+1);
                    case "Rozegranie" -> result[playerAction.getRating()][4] = ""+(Integer.parseInt(result[playerAction.getRating()][4])+1);
                    case "Atak" -> result[playerAction.getRating()][5] = ""+(Integer.parseInt(result[playerAction.getRating()][5])+1);
                    case "Blok" -> result[playerAction.getRating()][6] = ""+(Integer.parseInt(result[playerAction.getRating()][6])+1);
                }
            int sum;
            int actions;
            for (int i=1; i<7; i++) {
                sum=0;
                actions=0;
                for (int j=0; j<4; j++) {
                    actions += Integer.parseInt(result[j][i]);
                    sum += Integer.parseInt(result[j][i])*j;
                }
                if (actions!=0)
                    result[4][i] = ""+((sum*1.0/(actions*3.0))*100)+"%";
                else result[4][i] = "brak";
            }
        }

        return result;
    }

    public static String[][] playerRaport(String player) {
        String[][] result = new String[5][7];
        for (int i=0; i<4; i++)
            result[i][0] = ""+i;
        result[4][0] = "Dokladnosc";

        for (int i=0; i<5; i++)
            for (int j=1; j<7; j++)
                result[i][j]="0";

        int rating;
        for (PlayerAction playerAction : chosenRaport.getPlayerActions()) {
            if (playerAction!=null && playerAction.getPlayerName().equals(player))
                switch (playerAction.getActionName()) {
                    case "Przyjecie" -> result[playerAction.getRating()][1] = ""+(Integer.parseInt(result[playerAction.getRating()][1])+1);
                    case "Zagrywka" -> result[playerAction.getRating()][2] = ""+(Integer.parseInt(result[playerAction.getRating()][2])+1);
                    case "Obrona" -> result[playerAction.getRating()][3] = ""+(Integer.parseInt(result[playerAction.getRating()][3])+1);
                    case "Rozegranie" -> result[playerAction.getRating()][4] = ""+(Integer.parseInt(result[playerAction.getRating()][4])+1);
                    case "Atak" -> result[playerAction.getRating()][5] = ""+(Integer.parseInt(result[playerAction.getRating()][5])+1);
                    case "Blok" -> result[playerAction.getRating()][6] = ""+(Integer.parseInt(result[playerAction.getRating()][6])+1);
                }
            int sum;
            int actions;
            for (int i=1; i<7; i++) {
                sum=0;
                actions=0;
                for (int j=0; j<4; j++) {
                    actions += Integer.parseInt(result[j][i]);
                    sum += Integer.parseInt(result[j][i])*j;
                }
                if (actions!=0)
                    result[4][i] = ""+((sum*1.0/(actions*3.0))*100)+"%";
                else result[4][i] = "brak";
            }
        }

        return result;
    }

    public static void run(Team team, String raport) {
        chosenTeam = team;
        for (Raport r : team.getRaports())
            if (r.toString().equals(raport)) {
                chosenRaport = r;
                break;
            }

        frame = new JFrame();

        panel = new JPanel();


        //
        final int n = team.getPlayers().size();
        String[] playersStrings = new String[n+1];
        playersStrings[0] = "Cala druzyna";
        for (int i=0; i<team.getPlayers().size(); i++)
            playersStrings[i+1] = team.getPlayers().get(i).toString();
        choice = new JComboBox(playersStrings);
        BACK = new JButton("Wroc");
        OK = new JButton("OK");


        String[] columnsNames = {"Ocena", "Przyjecie", "Zagrywka", "Obrona", "Rozegranie", "Atak", "Blok"};
        if (realChoice.equals("Cala druzyna"))
            table = new JTable(teamRaport(), columnsNames);
        else
            table = new JTable(playerRaport(realChoice), columnsNames);
        scrollpane = new JScrollPane(table);

        panel.add(scrollpane);
        panel.add(new JLabel("Wybierz jednostke raportu"));
        panel.add(choice);
        panel.add(BACK);
        panel.add(OK);

        frame.add(panel);
        frame.setTitle(""+realChoice);

        BACK.addActionListener(new BackButton());
        OK.addActionListener(new OkButton());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private static class BackButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenu.run();
        }
    }

    private static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            realChoice = choice.getSelectedItem().toString();
            run(chosenTeam, chosenRaport.toString());
        }
    }
}
