import agent.Grille;
import agent.Message;
import agent.Position;
import graphics.Window;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Message> communication;

    public static void main(String[] args) {
        communication = new ArrayList<Message>();

        Grille grille = Grille.getInstance(3);
        grille.addAgent(new Position(2, 1), new Position(3, 2));
        grille.addAgent(new Position(1, 1), new Position(2, 2));
        grille.addAgent(new Position(0, 0), new Position(1, 2));
        grille.addAgent(new Position(0, 1), new Position(0, 0));

        Window window = Window.getInstance();

    }


}
