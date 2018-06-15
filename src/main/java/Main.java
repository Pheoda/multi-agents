import agent.Grille;
import graphics.Window;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        // TODO : Ajouter première interface demandant la taille de la grille et le nombre d'agents
        //JFrame frame = new JFrame();


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
