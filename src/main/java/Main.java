import agent.Grille;
import graphics.Window;

public class Main {

    public static void main(String[] args) {

        Grille grille = Grille.getInstance(5);
        grille.generateAgent(18);

        Window window = new Window();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        grille.runAgents();
    }


}
