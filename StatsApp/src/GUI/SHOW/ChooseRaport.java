package GUI.SHOW;

import DataBase.Player;
import DataBase.PlayerAction;
import DataBase.Raport;
import DataBase.Team;
import GUI.MainMenu;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ChooseRaport {
    private static JFrame frame;

    private static JPanel raportPanel;
    private static JComboBox raport;


    private static JPanel buttonsPanel;
    private static JButton BACK;
    private static JButton SAVE;
    private static JButton OK;
    private static JLabel message;

    private static Team chosenTeam;

    public static void run(Team team) {
        frame = new JFrame("Raporty");

        raportPanel = new JPanel();
        final int m = team.getRaports().size();
        String[] raportsStrings = new String[m];
        for (int i=0; i<team.getRaports().size(); i++)
            raportsStrings[i] = team.getRaports().get(i).toString();
        raport = new JComboBox(raportsStrings);
        raportPanel.add(new JLabel("Wybierz mecz"));
        raportPanel.add(raport);
        frame.add(raportPanel);

        buttonsPanel = new JPanel();
        BACK = new JButton("Wroc");
        OK = new JButton("Wyswietl raport");
        SAVE = new JButton("Zapisz raport w pliku JSON");
        message = new JLabel();
        buttonsPanel.add(OK);
        buttonsPanel.add(SAVE);
        buttonsPanel.add(BACK);
        buttonsPanel.add(message);
        frame.add(buttonsPanel);

        BACK.addActionListener(new BackButton());
        OK.addActionListener(new OkButton());
        SAVE.addActionListener(new SaveButton());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,1));

        chosenTeam = team;
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
            RaportsTable.setRealChoice();
            RaportsTable.run(chosenTeam, ""+raport.getItemAt(raport.getSelectedIndex()));
        }
    }

    private static class SaveButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String result = "";

            Raport chosenRaport = new Raport();
            int sum;
            int actions;

            for (Raport r : chosenTeam.getRaports())
                if (r.toString().equals(raport.getSelectedItem().toString()))
                    chosenRaport = r;

            result += "Przeciwnik\t"+chosenRaport.getEnemyName();
            result += "\nData\t"+chosenRaport.getDay()+"."+chosenRaport.getMonth()+"."+chosenRaport.getYear();
            result += "\nObiekt\t"+chosenRaport.getArena();

            result += "\n\nWynik\t"+chosenRaport.getOurSets()+" : "+chosenRaport.getEnemySets();
            for (int i=0; i<chosenRaport.getOurPoints().size(); i++)
                result += "\nset "+(i+1)+"\t"+chosenRaport.getOurPoints().get(i)+" : "+chosenRaport.getEnemyPoints().get(i);

            String[][] unitResult = new String[5][7];
            for (int i=0; i<4; i++)
                unitResult[i][0] = ""+i;
            unitResult[4][0] = "Dokladnosc";

            for (int i=0; i<5; i++)
                for (int j=1; j<7; j++)
                    unitResult[i][j]="0";

            for (PlayerAction playerAction : chosenRaport.getPlayerActions()) {
                if (playerAction!=null)
                    switch (playerAction.getActionName()) {
                        case "Przyjecie" -> unitResult[playerAction.getRating()][1] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][1])+1);
                        case "Zagrywka" -> unitResult[playerAction.getRating()][2] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][2])+1);
                        case "Obrona" -> unitResult[playerAction.getRating()][3] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][3])+1);
                        case "Rozegranie" -> unitResult[playerAction.getRating()][4] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][4])+1);
                        case "Atak" -> unitResult[playerAction.getRating()][5] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][5])+1);
                        case "Blok" -> unitResult[playerAction.getRating()][6] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][6])+1);
                    }

                for (int i=1; i<7; i++) {
                    sum=0;
                    actions=0;
                    for (int j=0; j<4; j++) {
                        actions += Integer.parseInt(unitResult[j][i]);
                        sum += Integer.parseInt(unitResult[j][i])*j;
                    }

                    if (actions!=0)
                        unitResult[4][i] = ""+((sum*1.0/(actions*3.0))*100)+"%";
                    else unitResult[4][i] = "brak";
                }
            }

            result += "\n\n\nCala druzyna";
            result += "\n\nOcena\tPrzyjecie\tZagrywka\tObrona\tRozegranie\tAtak\tBlok";

            for (int i=0; i<5; i++) {
                result += "\n";

                for (int j=0; j<7; j++)
                    result += unitResult[i][j]+"\t";
            }


            for (Player player : chosenTeam.getPlayers()) {

                unitResult = new String[5][7];
                for (int i=0; i<4; i++)
                    unitResult[i][0] = ""+i;
                unitResult[4][0] = "Dokladnosc";

                for (int i=0; i<5; i++)
                    for (int j=1; j<7; j++)
                        unitResult[i][j]="0";


                for (PlayerAction playerAction : chosenRaport.getPlayerActions()) {
                    if (playerAction!=null && playerAction.getPlayerName().equals(player.toString()))
                        switch (playerAction.getActionName()) {
                            case "Przyjecie" -> unitResult[playerAction.getRating()][1] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][1])+1);
                            case "Zagrywka" -> unitResult[playerAction.getRating()][2] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][2])+1);
                            case "Obrona" -> unitResult[playerAction.getRating()][3] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][3])+1);
                            case "Rozegranie" -> unitResult[playerAction.getRating()][4] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][4])+1);
                            case "Atak" -> unitResult[playerAction.getRating()][5] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][5])+1);
                            case "Blok" -> unitResult[playerAction.getRating()][6] = ""+(Integer.parseInt(unitResult[playerAction.getRating()][6])+1);
                        }

                    for (int i=1; i<7; i++) {
                        sum=0;
                        actions=0;
                        for (int j=0; j<4; j++) {
                            actions += Integer.parseInt(unitResult[j][i]);
                            sum += Integer.parseInt(unitResult[j][i])*j;
                        }

                        if (actions!=0)
                            unitResult[4][i] = ""+((sum*1.0/(actions*3.0))*100)+"%";
                        else unitResult[4][i] = "brak";
                    }
                }

                result += "\n\n\n"+player;
                result += "\n\nOcena\tPrzyjecie\tZagrywka\tObrona\tRozegranie\tAtak\tBlok";

                for (int i=0; i<5; i++) {
                    result += "\n";

                    for (int j=0; j<7; j++)
                        result += unitResult[i][j]+"\t";
                }
            }

            Gson gson = new Gson();
            String json = gson.toJson(result);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(chosenRaport+".json")))) {
                bw.write(json);
            } catch (Exception ea) {
                ea.printStackTrace();
            }

            message.setText("Raport zapisany pomyslnie");
        }
    }
}
