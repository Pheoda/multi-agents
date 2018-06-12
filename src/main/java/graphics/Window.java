package graphics;

import agent.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Window extends JFrame implements Observer {

    private GridLayout gridLayout;
    private Grille grid;

    private JPanel[][] panels;
    private JLabel[][] labels;

    public Window() {
        this.setTitle("Interaction multi-agents");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grid = Grille.getInstance();

        gridLayout = new GridLayout(grid.getSize(), grid.getSize());
        this.setLayout(gridLayout);

        panels = new JPanel[grid.getSize()][grid.getSize()];
        labels = new JLabel[grid.getSize()][grid.getSize()];


        for(int row = 0; row < gridLayout.getRows(); row++) {
            for(int column = 0; column < gridLayout.getColumns(); column++) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("INIT");
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(label);
                labels[column][row] = label;
                panels[column][row] = panel;
                this.add(panels[column][row], column, row);
            }
        }

        for (Agent agent : grid.getAgents()) {
            agent.addObserver(this);
        }

        this.setVisible(true);
    }

    public void update(Observable o, Object arg) {
        System.out.println("UPDATE");
        for (int column = 0; column < gridLayout.getColumns(); column++)
            for (int row = 0; row < gridLayout.getRows(); row++) {
                JLabel label = new JLabel("void");
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                for (Agent agent : grid.getAgents()) {
                    if (agent.getPosition().equals(new Position(column, row)))
                        label.setText("AGENT");
                }
                panels[column][row].removeAll();
                panels[column][row].add(label);
                labels[column][row] = label;
            }

        repaint();
        revalidate();
    }
}
