package GUI.ADD;

import GUI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AddCount {
    private static JFrame frame;

    private static JPanel namePanel;
    private static JLabel nameMessage;
    private static JTextField name;

    private static JPanel countPanel;
    private static JLabel countMessage;
    private static JTextField count;

    private static JPanel backPanel;
    private static JButton BACK;

    private static JPanel okPanel;
    private static JButton OK;

    public static String getName() {
        return name.getText();
    }

    public static int getCount() {
        return Integer.parseInt(count.getText());
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void run() {
        frame = new JFrame("Dodawanie nowej druzyny");

        namePanel = new JPanel();
        nameMessage = new JLabel("Nazwa druzyny");
        name = new JTextField(20);
        namePanel.add(nameMessage);
        namePanel.add(name);

        countPanel = new JPanel();
        countMessage = new JLabel("Liczba zawodnikow");
        count = new JTextField(5);
        countPanel.add(countMessage);
        countPanel.add(count);

        backPanel = new JPanel();
        BACK = new JButton("Wroc");
        backPanel.add(BACK);

        okPanel = new JPanel();
        OK = new JButton("OK");
        okPanel.add(OK);

        frame.add(namePanel);
        frame.add(countPanel);
        frame.add(backPanel);
        frame.add(okPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,2));

        BACK.addActionListener(new BackButton());
        OK.addActionListener(new AddPlayers());
    }

    private static class BackButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainMenu.run();
        }
    }
}
