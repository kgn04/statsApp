package GUI.SHOW;

import DataBase.DataBase;
import DataBase.*;
import GUI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowOrDelete {
    private static JFrame frame;

    private static JPanel teamsLabel;
    private static JComboBox teams;

    private static JPanel buttonsPanel;
    private static JButton BACK;
    private static JButton SHOW;
    private static JButton RAPORTS;
    private static JButton DELETE;
    private static JLabel message;

    public static Team getChosenTeam() {
        Team result = null;
        for (Team team : DataBase.getTeams())
            if (team.getName().equals(teams.getItemAt(teams.getSelectedIndex()))) {
                result = team;
                break;
            }
             return result;
    }

    public static void run() {
        frame = new JFrame("Moje druzyny");

        teamsLabel = new JPanel();
        final int n = DataBase.getTeams().size();
        String[] teamsList = new String[n];
        for (int i=0; i<n; i++)
            teamsList[i] = DataBase.getTeams().get(i).getName();
        teams = new JComboBox(teamsList);

        buttonsPanel = new JPanel();
        BACK = new JButton("Wroc");
        SHOW = new JButton("Wyswietl liste zawodnikow");
        RAPORTS = new JButton("Wyswietl raporty");
        DELETE = new JButton("Usun");
        message = new JLabel();

        teamsLabel.add(teams);

        buttonsPanel.add(BACK);
        buttonsPanel.add(SHOW);
        buttonsPanel.add(RAPORTS);
        buttonsPanel.add(DELETE);

        frame.add(teamsLabel);
        frame.add(buttonsPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,1));

        BACK.addActionListener(new BackButton());
        SHOW.addActionListener(new ShowButton());
        RAPORTS.addActionListener(new RaportsButton());
        DELETE.addActionListener(new DeleteButton());
    }

    private static class BackButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenu.run();
        }
    }

    private static class ShowButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TeamList.run(getChosenTeam());
            frame.dispose();
        }
    }

    private static class RaportsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChooseRaport.run(getChosenTeam());
            frame.dispose();
        }
    }

    private static class DeleteButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataBase.removeTeam(getChosenTeam());
            frame.dispose();
            MainMenu.run();
        }
    }
}
