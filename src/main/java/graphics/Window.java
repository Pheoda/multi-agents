package graphics;

import javax.swing.*;

public class Window extends JFrame {

    private Panel panel;

    public Window() {
        this.setTitle("Interaction multi-agents");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new Panel();

        this.add(panel);

        this.setVisible(true);
    }
}
