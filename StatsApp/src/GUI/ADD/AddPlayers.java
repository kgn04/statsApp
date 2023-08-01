package GUI.ADD;

import DataBase.DataBase;
import DataBase.Player;
import GUI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPlayers implements ActionListener {
    private JFrame frame;

    private JPanel infoPanel;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel position;
    private JLabel number;

    private ArrayList<JPanel> enterPanels = new ArrayList<>();
    private ArrayList<JLabel> indexes = new ArrayList<>();
    private ArrayList<JTextField> firstNameFields = new ArrayList<>();
    private ArrayList<JTextField> lastNameFields = new ArrayList<>();
    private String[] positionsList = {"Przyjmujacy", "Rozgrywajacy", "Atakujacy", "Srodkowy", "Libero"};
    private ArrayList<JComboBox> positionFields = new ArrayList<>();
    private ArrayList<JTextField> numberFields = new ArrayList<>();

    private JScrollPane scrollPane;

    private JPanel buttonPanel;
    private JButton BACK;
    private JLabel message;
    private JButton OK;

    private int n;
    private String teamName;

    public AddPlayers() {
        frame = new JFrame("Dodawanie nowej druzyny");

        infoPanel = new JPanel();
        firstName = new JLabel("Imie");
        lastName = new JLabel("Nazwisko");
        position = new JLabel("Pozycja");
        number = new JLabel("Numer");

        scrollPane = new JScrollPane();

        buttonPanel = new JPanel();
        BACK = new JButton("Wroc");
        message = new JLabel();
        OK = new JButton("OK");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        n = AddCount.getCount();
        teamName = AddCount.getName();
        AddCount.getFrame().dispose();

        infoPanel.add(firstName);
        infoPanel.add(lastName);
        infoPanel.add(position);
        infoPanel.add(number);

        frame.add(infoPanel);

        for (int i=0; i<n; i++) {
            enterPanels.add(new JPanel());
            indexes.add(new JLabel((i+1)+". "));
            firstNameFields.add(new JTextField(10));
            lastNameFields.add(new JTextField(10));
            positionFields.add(new JComboBox(positionsList));
            numberFields.add(new JTextField(10));
            enterPanels.get(i).add(indexes.get(i));
            enterPanels.get(i).add(firstNameFields.get(i));
            enterPanels.get(i).add(lastNameFields.get(i));
            enterPanels.get(i).add(positionFields.get(i));
            enterPanels.get(i).add(numberFields.get(i));
            frame.add(enterPanels.get(i));
        }

        buttonPanel.add(BACK);
        buttonPanel.add(message);
        buttonPanel.add(OK);
        frame.add(buttonPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 150+n*30);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(n+2,1));

        BACK.addActionListener(new BackButton());
        OK.addActionListener(new OkButton());
    }

    private class BackButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenu.run();
        }
    }

    private class OkButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Player> players = new ArrayList<>();
            String tempFirstName;
            String tempLastName;
            String tempPosition;
            int tempNumber;

            for (int i=0; i<n; i++) {
                tempFirstName = firstNameFields.get(i).getText();
                tempLastName = lastNameFields.get(i).getText();
                tempPosition = ""+positionFields.get(i).getItemAt(positionFields.get(i).getSelectedIndex());
                tempNumber = Integer.parseInt(numberFields.get(i).getText());

                players.add(new Player(tempFirstName, tempLastName, tempPosition, tempNumber));
            }
            DataBase.addTeam(teamName, players);
            frame.dispose();
            MainMenu.run();
        }
    }
}
