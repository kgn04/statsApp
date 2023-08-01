package GUI.SHOW;

import DataBase.Team;
import GUI.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BandCombineOp;

public class TeamList {
    private static JFrame frame;

    private static JScrollPane scrollPane;
    private static JTable table;

    private static JPanel panel;
    private static JButton OK;

    private static Team chosenTeam;

    public static void run(Team team) {
        frame = new JFrame("Moje druzyny");
        panel = new JPanel();
        OK = new JButton("OK");
        chosenTeam = team;

        final int n = chosenTeam.getPlayers().size();

        String[] nazwyKolumn = {"Imie", "Nazwisko", "Pozycja", "Numer"};
        String[][] dane = new String[n][4];

        for (int i = 0; i < n; i++) {
            dane[i][0] = chosenTeam.getPlayers().get(i).getFirstName();
            dane[i][1] = chosenTeam.getPlayers().get(i).getLastName();
            dane[i][2] = chosenTeam.getPlayers().get(i).getPosition();
            dane[i][3] = "" + chosenTeam.getPlayers().get(i).getNumber();
        }

            table = new JTable(dane, nazwyKolumn);
            scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            panel.add(OK);
            frame.getContentPane().add(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setResizable(true);
            frame.setVisible(true);

            OK.addActionListener(new OkButton());
    }

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenu.run();
        }
    }
}
