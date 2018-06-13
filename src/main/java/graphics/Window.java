package graphics;

import agent.Grille;

import javax.swing.*;

public class Window extends JFrame {

    private Grille grid;

    private Panel panel;

    public Window() {
        this.setTitle("Interaction multi-agents");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grid = Grille.getInstance();

        panel = new Panel();

        this.add(panel);

        this.setVisible(true);
    }
}
