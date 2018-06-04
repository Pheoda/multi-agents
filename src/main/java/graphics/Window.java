package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Window extends JFrame implements Observer {

    private GridLayout gridLayout;
    private static Window INSTANCE = null;

    private Window() {
        this.setTitle("Interaction multi-agents");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // TODO : get correct Grid size !
        gridLayout = new GridLayout(3, 3);

        this.setLayout(gridLayout);

        this.setVisible(true);
    }

    public static Window getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Window();
        return INSTANCE;
    }

    public void update(Observable o, Object arg) {
        for (int column = 0; column < gridLayout.getColumns(); column++)
            for (int row = 0; row < gridLayout.getRows(); row++) {
                // TODO : get label from grid
                JLabel label = new JLabel("label");
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.add(label);
            }

    }
}
