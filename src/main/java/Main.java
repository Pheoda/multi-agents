import agent.Grille;
import agent.Message;
import agent.Position;
import graphics.Window;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Grille grille = Grille.getInstance(3);
        grille.addAgent(new Position(1, 2), new Position(2, 0));
        grille.addAgent(new Position(1, 1), new Position(2, 2));
        grille.addAgent(new Position(0, 0), new Position(2, 1));
        grille.addAgent(new Position(1, 0), new Position(0, 0));

        Window window = new Window();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        grille.runAgents();
    }


}
