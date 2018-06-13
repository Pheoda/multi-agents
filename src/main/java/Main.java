import agent.Grille;
import agent.Message;
import agent.Position;
import graphics.Window;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Grille grille = Grille.getInstance(5);
        grille.generateAgent(10);

        Window window = new Window();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        grille.runAgents();
    }


}
