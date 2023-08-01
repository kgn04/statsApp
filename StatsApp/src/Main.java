import DataBase.DataBase;
import GUI.MainMenu;

public class Main {
    public static void main(String[] args) {
        DataBase.deserializeDatabase();
        MainMenu.run();
    }
}
