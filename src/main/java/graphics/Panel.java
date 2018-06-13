package graphics;

import agent.Agent;
import agent.Grille;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Panel extends JPanel implements Observer {

    Grille grid;

    public Panel() {
        super();

        grid = Grille.getInstance();

        for (Agent agent : grid.getAgents()) {
            agent.addObserver(this);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);
        drawAgents(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
        revalidate();
    }

    private void drawGrid(Graphics g) {
        for (int i = 1; i < grid.getSize(); i++) {
            // Horizontal
            g.drawLine(0, i * 640 / grid.getSize(), 640, i * 640 / grid.getSize());
            // Vertical
            g.drawLine(i * 640 / grid.getSize(), 0, i * 640 / grid.getSize(), 640);
        }
    }

    private void drawAgents(Graphics g) {
        for (Agent agent : grid.getAgents()) {
            g.drawString("Agent " + agent.getId(), (int) ((agent.getPosition().getColumn() + 0.5) * 640 / grid.getSize()), (int) ((agent.getPosition().getRow() + 0.5) * 640 / grid.getSize()));
        }
    }
}
