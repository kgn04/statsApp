package DataBase;

public class PlayerAction {
    private String playerName;
    private String actionName;
    private int rating;

    public PlayerAction(String playerName, String actionName, int rating) {
        this.playerName = playerName;
        this.actionName = actionName;
        this.rating = rating;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getActionName() {
        return actionName;
    }

    public int getRating() {
        return rating;
    }
}
