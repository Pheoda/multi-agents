package graphics;

import agent.Grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowParameter extends JFrame {
    JPanel panelLeft;
    JPanel panelRight;

    JSpinner gridSize;
    JSpinner agentNumber;

    JLabel labelGrid;
    JLabel labelAgent;

    public WindowParameter() {

        panelLeft = new JPanel();
        panelRight = new JPanel();

        gridSize = new JSpinner(new SpinnerNumberModel(3, 1, 15, 1));
        agentNumber = new JSpinner(new SpinnerNumberModel(10, 1, 30, 1));

        labelGrid = new JLabel("Taille de la grille ");
        labelAgent = new JLabel("Nombre d'agents ");

        this.setTitle("Paramètrage de l'application");
        this.setSize(640, 150);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        this.setLayout(new GridLayout(1, 3));

        panelLeft.add(labelGrid);
        panelLeft.add(gridSize);

        this.getContentPane().add(panelLeft);

        panelRight.add(labelAgent);
        panelRight.add(agentNumber);

        this.getContentPane().add(panelRight);

        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(correctParameters()) {
                    // Close window
                    WindowParameter.super.dispose();

                    // Launch application with parameters
                    Grille grille = Grille.getInstance((int)gridSize.getValue());
                    grille.generateAgent((int)agentNumber.getValue());

                    Window window = new Window();

                    grille.runAgents();
                }
                else {
                    JOptionPane.showMessageDialog(WindowParameter.super.getRootPane(), "Vous devez choisir un nombre d'agents inférieurs à la taille de la grille - 1");
                    // DISPLAY ERROR
                }
            }
        });

        this.getContentPane().add(button);

        this.setVisible(true);
    }

    private boolean correctParameters() {
        return ((int)agentNumber.getValue() + 1) < ((int)gridSize.getValue() * (int)gridSize.getValue());
    }
}
