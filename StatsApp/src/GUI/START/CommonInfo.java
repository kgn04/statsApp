package GUI.START;

import DataBase.*;
import GUI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommonInfo {
    private static JFrame frame;

    private static JPanel mainTeamPanel;
    private static JLabel mainTeamLabel;
    private static JComboBox mainTeam;

    private static JPanel enemyTeamPanel;
    private static JLabel enemyTeamLabel;
    private static JTextField enemyTeam;

    private static JPanel datePanel;
    private static JLabel dateLabel;
    private static JTextField dateDay;
    private static JTextField dateMonth;
    private static JTextField dateYear;

    private static JPanel arenaPanel;
    private static JLabel arenaLabel;
    private static JTextField arena;

    private static JPanel buttonsPanel;
    private static JButton BACK;
    private static JButton OK;

    public static void run() {
        frame = new JFrame("Mecz");

        mainTeamPanel = new JPanel();
        mainTeamLabel = new JLabel("Moj zespol");
        final int n = DataBase.getTeams().size();
        String[] teamsList = new String[n];
        for (int i=0; i<n; i++)
            teamsList[i] = DataBase.getTeams().get(i).getName();
        mainTeam = new JComboBox(teamsList);
        mainTeamPanel.add(mainTeamLabel);
        mainTeamPanel.add(mainTeam);

        enemyTeamPanel = new JPanel();
        enemyTeamLabel = new JLabel("Nazwa zespolu przeciwnika");
        enemyTeam = new JTextField(20);
        enemyTeamPanel.add(enemyTeamLabel);
        enemyTeamPanel.add(enemyTeam);

        datePanel = new JPanel();
        dateLabel = new JLabel("Data");
        dateDay = new JTextField("DD");
        dateMonth = new JTextField("MM");
        dateYear = new JTextField("RRRR");
        datePanel.add(dateLabel);
        datePanel.add(dateDay);
        datePanel.add(dateMonth);
        datePanel.add(dateYear);

        arenaPanel = new JPanel();
        arenaLabel = new JLabel("Obiekt");
        arena = new JTextField(20);
        arenaPanel.add(arenaLabel);
        arenaPanel.add(arena);

        buttonsPanel = new JPanel();
        BACK = new JButton("Wroc");
        OK = new JButton("OK");
        buttonsPanel.add(BACK);
        buttonsPanel.add(OK);

        frame.add(mainTeamPanel);
        frame.add(enemyTeamPanel);
        frame.add(datePanel);
        frame.add(arenaPanel);
        frame.add(buttonsPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(5,1));

        BACK.addActionListener(new BackButton());
        OK.addActionListener(new OkButton());
    }

    public static Team getChosenTeam() {
        Team result = null;
        for (Team team : DataBase.getTeams())
            if (team.getName().equals(mainTeam.getItemAt(mainTeam.getSelectedIndex()))) {
                result = team;
                break;
            }
        return result;
    }

    public static class BackButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenu.run();
        }
    }

    public static class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FirstString.updateData(enemyTeam.getText(), Integer.parseInt(dateDay.getText()), Integer.parseInt(dateMonth.getText()), Integer.parseInt(dateYear.getText()), arena.getText());
            frame.dispose();
            FirstString.run(getChosenTeam());
        }
    }
}
