package graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        this.setTitle("Intercation multi-agents");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO : get correct Grid size !
        GridLayout gridLayout = new GridLayout(3, 3);

        for(int column = 0; column < gridLayout.getColumns(); column++)
            for(int row = 0; row < gridLayout.getRows(); row++) {
                // TODO : get label from grid
                JLabel label = new JLabel("label");
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.add(label);
            }

        this.setLayout(gridLayout);

        this.setVisible(true);
    }

}
