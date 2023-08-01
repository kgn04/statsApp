package GUI.START;

import DataBase.DataBase;
import DataBase.Player;
import DataBase.PlayerAction;
import DataBase.Team;
import DataBase.*;
import GUI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.BorderLayout.CENTER;

public class Match {
    private static JFrame frame;

    private static JPanel mainPanel;

    //

    private static JPanel resultPanelMain;

    private static JPanel resultPanel1;
    private static JLabel ourTeamLabel;
    private static JLabel ourSetLabel;
    private static JLabel ourPointLabel;

    private static JPanel resultPanel2;
    private static JLabel setLabel;
    private static JLabel pointLabel;

    private static JPanel resultPanel3;
    private static JLabel enemyTeamLabel;
    private static JLabel enemySetLabel;
    private static JLabel enemyPointLabel;

    //

    private static JPanel changePanelMain;

    private static JPanel changePanel1;
    private static DefaultTableModel tableModel;
    private static JTable firstStringList;

    private static JPanel changePanel2;
    private static JLabel changeFromLabel;
    private static JComboBox changeFrom;
    private static JLabel changeToLabel;
    private static JComboBox changeTo;

    private static JPanel changePanel3;
    private static JButton CHANGE;

    //

    private static JPanel actionPanelMain;

    private static JPanel actionPanel1;
    private static JLabel playerLabel;
    private static JComboBox player;

    private static JPanel actionPanel2;
    private static JLabel actionLabel;
    private static JComboBox action;

    private static JPanel actionPanel3;
    private static JLabel ratingLabel;
    private static JComboBox rating;

    private static JPanel actionPanel4;
    private static JButton ADD;

    //

    private static JPanel pointPanel;
    private static JButton POINTUS;
    private static JButton POINTENEMY;
    private static JButton SET;
    private static JButton MATCH;

    //

    private static Team chosenTeam;
    private static Player[] firstString;
    private static String enemy;
    private static int day;
    private static int month;
    private static int year;
    private static String arena;

    private static int ourSets;
    private static int ourPoints;
    private static int enemySets;
    private static int enemyPoints;
    private static ArrayList<Integer> ourPointsList;
    private static ArrayList<Integer> enemyPointsList;

    private static ArrayList<PlayerAction> playerActions;


    public static void updateMatchData(Team chTeam, Player[] fString, String enem, int d, int m, int y, String aren) {
        chosenTeam = chTeam;
        firstString = fString;
        enemy = enem;
        day = d;
        month = m;
        year = y;
        arena = aren;

        ourSets = 0;
        ourPoints = 0;
        enemySets = 0;
        enemyPoints = 0;

        playerActions = new ArrayList<>();
        ourPointsList = new ArrayList<>();
        enemyPointsList = new ArrayList<>();
    }

    public static void createResultPanel() {
        resultPanelMain = new JPanel(new GridLayout(1,5));
        resultPanelMain.add(new JPanel());

        resultPanel1 = new JPanel(new GridLayout(3,1));
        resultPanel1.add(new JLabel(chosenTeam.getName()));
        ourSetLabel = new JLabel(""+ourSets);
        ourPointLabel = new JLabel(""+ourPoints);
        resultPanel1.add(ourSetLabel);
        resultPanel1.add(ourPointLabel);

        resultPanel2 = new JPanel(new GridLayout(3,1));
        resultPanel2.add(new JLabel());
        resultPanel2.add(new JLabel("SETY"));
        resultPanel2.add(new JLabel("PUNKTY"));

        resultPanel3 = new JPanel(new GridLayout(3,1));
        resultPanel3.add(new JLabel(enemy));
        enemySetLabel = new JLabel(""+enemySets);
        enemyPointLabel = new JLabel(""+enemyPoints);
        resultPanel3.add(enemySetLabel);
        resultPanel3.add(enemyPointLabel);

        resultPanelMain.add(resultPanel1);
        resultPanelMain.add(resultPanel2);
        resultPanelMain.add(resultPanel3);
    }

    public static void createChangePanel() {
        changePanelMain = new JPanel();

        changePanel1 = new JPanel();
        tableModel = new DefaultTableModel();
        firstStringList = new JTable(tableModel);
        tableModel.addColumn("Pierwszy sklad");
        for(Player player : firstString)
            tableModel.insertRow(0, new Object[] {player.toString()});
        changePanel1.add(new JScrollPane(firstStringList));
        changePanelMain.add(changePanel1);

        changePanel2 = new JPanel();
        changePanel2.setLayout(new GridLayout(2,2));
        changeFromLabel = new JLabel("                                               Zmiana z ");
        String[] firstStrings = new String[7];
        for (int i=0; i<7; i++)
            firstStrings[i] = firstString[i].toString();
        changeFrom = new JComboBox(firstStrings);
        changePanel2.add(changeFromLabel);
        changePanel2.add(changeFrom);

        changeToLabel = new JLabel("                                            Zmiana na ");
        final int n = chosenTeam.getPlayers().size() - firstString.length;
        String[] players = new String[n];
        int k = 0;
        for (int i=0; i<chosenTeam.getPlayers().size(); i++) {
            boolean doesHave = false;
            for(int j=0; j<7; j++)
                if (firstString[j].toString().equals(chosenTeam.getPlayers().get(i).toString()))
                    doesHave = true;

                if (!doesHave) {
                    players[k] = chosenTeam.getPlayers().get(i).toString();
                    k++;
                }
        }

        changeTo = new JComboBox(players);
        changePanel2.add(changeToLabel);
        changePanel2.add(changeTo);
        changePanelMain.add(changePanel2);

        changePanel3 = new JPanel(new GridLayout(3,3));
        for (int i=0; i<4; i++)
            changePanel3.add(new JPanel());
        CHANGE = new JButton("Wprowadz zmiane");
        changePanel3.add(CHANGE);
        for (int i=0; i<4; i++)
            changePanel3.add(new JPanel());

        changePanelMain.add(changePanel3);
        changePanelMain.setLayout(new GridLayout(1,3));
    }

    public static void createActionPanel() {
        actionPanelMain = new JPanel(new GridLayout(1,4));

        actionPanel1 = new JPanel(new GridLayout(2,1));
        actionPanel1.add(new JLabel("Zawodnik"));
        String[] firstStrings = new String[7];
        for (int i=0; i<7; i++)
            firstStrings[i] = firstString[i].toString();
        player = new JComboBox(firstStrings);
        actionPanel1.add(player);

        actionPanel2 = new JPanel(new GridLayout(2,1));
        actionPanel2.add(new JLabel("Akcja"));
        String[] actionStrings = {"Przyjecie", "Zagrywka", "Obrona", "Rozegranie", "Atak", "Blok"};
        action = new JComboBox(actionStrings);
        actionPanel2.add(action);

        actionPanel3 = new JPanel(new GridLayout(2,1));
        actionPanel3.add(new JLabel("Ocena"));
        String[] ratingStrings = {"0", "1", "2", "3"};
        rating = new JComboBox(ratingStrings);
        actionPanel3.add(rating);

        actionPanel4 = new JPanel(new GridLayout(3,3));
        for (int i=0; i<4; i++)
            actionPanel4.add(new JPanel());
        ADD = new JButton("Dodaj");
        actionPanel4.add(ADD);
        for (int i=0; i<4; i++)
            actionPanel4.add(new JPanel());

        actionPanelMain.add(actionPanel1);
        actionPanelMain.add(actionPanel2);
        actionPanelMain.add(actionPanel3);
        actionPanelMain.add(actionPanel4);
    }

    public static void createButtonPanels() {
        pointPanel = new JPanel(new GridLayout(2,2));

        POINTUS = new JButton("Punkt dla nas");
        POINTENEMY = new JButton("Punkt dla przeciwnika");
        pointPanel.add(POINTUS);
        pointPanel.add(POINTENEMY);

        SET = new JButton("Koniec seta");
        MATCH = new JButton("Koniec meczu");
        pointPanel.add(SET);
        pointPanel.add(MATCH);
    }

    public static void run() {
        frame = new JFrame("Mecz");
        mainPanel = new JPanel(new GridLayout(6,1));
        frame.add(mainPanel);

        createResultPanel();
        mainPanel.add(resultPanelMain);

        createChangePanel();
        mainPanel.add(changePanelMain);
        mainPanel.add(new JPanel());

        createActionPanel();
        mainPanel.add(actionPanelMain);
        mainPanel.add(new JPanel());

        createButtonPanels();
        mainPanel.add(pointPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setVisible(true);

        CHANGE.addActionListener(new ChangeButton());
        POINTUS.addActionListener(new pointUsButton());
        POINTENEMY.addActionListener(new pointEnemyButton());
        SET.addActionListener(new setButton());
        ADD.addActionListener(new addActionButton());
        MATCH.addActionListener(new matchButton());
    }

    private static class pointUsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ourPoints++;
            ourPointLabel.setText(""+ourPoints);
        }
    }

    private static class pointEnemyButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            enemyPoints++;
            enemyPointLabel.setText(""+enemyPoints);
        }
    }

    private static class setButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ourPoints > enemyPoints) {
                ourSets++;
                ourSetLabel.setText(""+ourSets);
            } else {
                enemySets++;
                enemySetLabel.setText(""+enemySets);
            }

            ourPointsList.add(ourPoints);
            enemyPointsList.add(enemyPoints);

            ourPoints = 0;
            enemyPoints = 0;

            ourPointLabel.setText("0");
            enemyPointLabel.setText("0");
        }
    }

    private static class ChangeButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();

            int changeIndex = 666;

            for (int i=0; i<7; i++)
                if (firstString[i].toString().equals(changeFrom.getItemAt(changeFrom.getSelectedIndex())))
                    changeIndex = i;

            for (Player player : chosenTeam.getPlayers())
                if (player.toString().equals(changeTo.getItemAt(changeTo.getSelectedIndex()))) {
                    firstString[changeIndex] = player;
                    break;
                }

            run();
        }
    }

    private static class addActionButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            playerActions.add(new PlayerAction(player.getItemAt(player.getSelectedIndex()).toString(), action.getItemAt(action.getSelectedIndex()).toString(), Integer.parseInt(rating.getItemAt(rating.getSelectedIndex()).toString())));
        }
    }

    private static class matchButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            for (Team team : DataBase.getTeams())
                if (chosenTeam.getName().equals(team.getName())) {
                    team.addRaport(new Raport(enemy, day, month, year, arena, playerActions, ourSets, enemySets, ourPointsList, enemyPointsList));
                    break;
                } else
                    System.out.println("gowno");

            frame.dispose();
            MainMenu.run();
        }
    }

}
