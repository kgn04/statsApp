package GUI.START;

import DataBase.DataBase;
import DataBase.Player;
import DataBase.Team;
import GUI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstString {
    private static JFrame frame;

    private static JPanel labelPanel;
    private static JLabel label;

    private static JPanel przyjmPanel;
    private static JLabel przyjmLabel;
    private static JComboBox przyjm1;
    private static JComboBox przyjm2;

    private static JPanel srodkPanel;
    private static JLabel srodkLabel;
    private static JComboBox srodk1;
    private static JComboBox srodk2;

    private static JPanel rozgPanel;
    private static JLabel rozgLabel;
    private static JComboBox rozg;

    private static JPanel atakPanel;
    private static JLabel atakLabel;
    private static JComboBox atak;

    private static JPanel liberoPanel;
    private static JLabel liberoLabel;
    private static JComboBox libero;

    private static JPanel buttonsPanel;
    private static JButton BACK;
    private static JButton OK;

    private static Team chosenTeam;
    private static Player[] firstString = new Player[7];
    private static String enemy;
    private static int day;
    private static int month;
    private static int year;
    private static String arena;

    public static void updateData (String enemyx, int dayx, int monthx, int yearx, String arenax) {
        enemy = enemyx;
        day = dayx;
        month = monthx;
        year = yearx;
        arena = arenax;
    }

    public static void run(Team chTeam) {
        chosenTeam = new Team(chTeam.getName(), chTeam.getPlayers());

        frame = new JFrame("Mecz");

        labelPanel = new JPanel();
        label = new JLabel("Wybierz pierwszy sklad");
        labelPanel.add(label);

        przyjmPanel = new JPanel();
        przyjmLabel = new JLabel("Przyjmujacy");
        final int n1 = chosenTeam.getPrzyjmujacy().size();
        String[] przyjmList = new String[n1];
        for (int i=0; i<n1; i++)
            przyjmList[i] = chosenTeam.getPrzyjmujacy().get(i).toString();
        przyjm1 = new JComboBox(przyjmList);
        przyjm2 = new JComboBox(przyjmList);
        przyjmPanel.add(przyjmLabel);
        przyjmPanel.add(przyjm1);
        przyjmPanel.add(przyjm2);

        srodkPanel = new JPanel();
        srodkLabel = new JLabel("Srodkowi");
        final int n2 = chosenTeam.getSrodkowi().size();
        String[] srodkList = new String[n2];
        for (int i=0; i<n2; i++)
            srodkList[i] = chosenTeam.getSrodkowi().get(i).toString();
        srodk1 = new JComboBox(srodkList);
        srodk2 = new JComboBox(srodkList);
        srodkPanel.add(srodkLabel);
        srodkPanel.add(srodk1);
        srodkPanel.add(srodk2);

        rozgPanel = new JPanel();
        rozgLabel = new JLabel("Rozgrywajacy");
        final int n3 = chosenTeam.getRozgrywajacy().size();
        String[] rozgList = new String[n3];
        for (int i=0; i<n3; i++)
            rozgList[i] = chosenTeam.getRozgrywajacy().get(i).toString();
        rozg = new JComboBox(rozgList);
        rozgPanel.add(rozgLabel);
        rozgPanel.add(rozg);

        atakPanel = new JPanel();
        atakLabel = new JLabel("Atakujacy");
        final int n4 = chosenTeam.getAtakujacy().size();
        String[] atakList = new String[n4];
        for (int i=0; i<n4; i++)
            atakList[i] = chosenTeam.getAtakujacy().get(i).toString();
        atak = new JComboBox(atakList);
        atakPanel.add(atakLabel);
        atakPanel.add(atak);

        liberoPanel = new JPanel();
        liberoLabel = new JLabel("Libero");
        final int n5 = chosenTeam.getLibero().size();
        String[] liberoList = new String[n5];
        for (int i=0; i<n5; i++)
            liberoList[i] = chosenTeam.getLibero().get(i).toString();
        libero = new JComboBox(liberoList);
        liberoPanel.add(liberoLabel);
        liberoPanel.add(libero);

        buttonsPanel = new JPanel();
        BACK = new JButton("Wroc");
        OK = new JButton("OK");
        buttonsPanel.add(BACK);
        buttonsPanel.add(OK);

        frame.add(labelPanel);
        frame.add(przyjmPanel);
        frame.add(srodkPanel);
        frame.add(rozgPanel);
        frame.add(atakPanel);
        frame.add(liberoPanel);
        frame.add(buttonsPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(7,1));

        BACK.addActionListener(new BackButton());
        OK.addActionListener(new OkButton());
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
            int i = 0;

            for (Player player : chosenTeam.getPrzyjmujacy())
                if (player.toString().equals(przyjm1.getItemAt(przyjm1.getSelectedIndex())) || player.toString().equals(przyjm2.getItemAt(przyjm2.getSelectedIndex()))) {
                    firstString[i] = player;
                    i++;
            }

            for (Player player : chosenTeam.getSrodkowi())
                if (player.toString().equals(srodk1.getItemAt(srodk1.getSelectedIndex())) || player.toString().equals(srodk2.getItemAt(srodk2.getSelectedIndex()))) {
                    firstString[i] = player;
                    i++;
                }

            for (Player player : chosenTeam.getRozgrywajacy())
                if (player.toString().equals(rozg.getItemAt(rozg.getSelectedIndex()))) {
                    firstString[i] = player;
                    i++;
                }

            for (Player player : chosenTeam.getAtakujacy())
                if (player.toString().equals(atak.getItemAt(atak.getSelectedIndex()))) {
                    firstString[i] = player;
                    i++;
                }

            for (Player player : chosenTeam.getLibero())
                if (player.toString().equals(libero.getItemAt(libero.getSelectedIndex()))) {
                    firstString[i] = player;
                    i++;
                }


            frame.dispose();

            Match.updateMatchData(chosenTeam, firstString, enemy, day, month, year, arena);
            Match.run();
        }
    }
}
