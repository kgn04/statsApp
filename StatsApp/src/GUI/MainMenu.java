package GUI;

import GUI.ADD.AddCount;
import GUI.SHOW.ShowOrDelete;
import GUI.START.CommonInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private static JFrame frame;

    private static JPanel labelPanel;
    private static JLabel welcome;

    private static JPanel buttonsPanel;
    private static JButton ADD;
    private static JButton SHOW;
    private static JButton START;

    public static void run() {
        frame = new JFrame("StatsApp");

        labelPanel = new JPanel();
        welcome = new JLabel("Witaj w aplikacji StatsApp!");

        buttonsPanel = new JPanel();
        ADD = new JButton("Dodaj nowa druzyne");
        SHOW = new JButton("Pokaz moje druzyny");
        START = new JButton("Rozpocznij mecz");

        labelPanel.add(welcome);

        buttonsPanel.add(ADD);
        buttonsPanel.add(SHOW);
        buttonsPanel.add(START);

        frame.add(labelPanel);
        frame.add(buttonsPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,1));

        ADD.addActionListener(new AddButton());
        SHOW.addActionListener(new ShowButton());
        START.addActionListener(new StartButton());
    }

    private static class AddButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            AddCount.run();
        }
    }

    private static class ShowButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ShowOrDelete.run();
        }
    }

    private static class StartButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            CommonInfo.run();
        }
    }
}
