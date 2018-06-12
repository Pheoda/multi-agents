package graphics;

import agent.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Window extends JFrame implements Observer {

    private GridLayout gridLayout;
    private Grille grid;

    private JLabel[][] labels;

    public Window() {
        this.setTitle("Interaction multi-agents");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grid = Grille.getInstance();

        gridLayout = new GridLayout(grid.getSize(), grid.getSize());

        labels = new JLabel[grid.getSize()][grid.getSize()];

        for(int row = 0; row < gridLayout.getRows(); row++) {
            for(int column = 0; column < gridLayout.getColumns(); column++) {
                JLabel label = new JLabel("INIT");
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[column][row] = label;
            }
        }

        for (Agent agent : grid.getAgents()) {
            agent.addObserver(this);
        }

        this.setLayout(gridLayout);

        this.setVisible(true);
    }

    public void update(Observable o, Object arg) {
        System.out.println("UPDATE");
        for (int column = 0; column < gridLayout.getColumns(); column++)
            for (int row = 0; row < gridLayout.getRows(); row++) {
                JLabel label = new JLabel("void");

                for (Agent agent : grid.getAgents()) {
                    if (agent.getPosition() == new Position(column, row))
                        label.setName("AGENT");
                }
                labels[column][row].removeAll();
                labels[column][row].add(label);
            }

        repaint();
        revalidate();
    }
}
