package agent;

import java.util.ArrayList;

public class Grille {

    private int size;
    private ArrayList<Agent> agents;
    private static Grille INSTANCE = null;


    private Grille(int size) {
        this.size = size;
        this.agents = new ArrayList<Agent>();
    }


    public static Grille getInstance(int size) {
        if (INSTANCE == null) {
            INSTANCE = new Grille(size);
        }
        return INSTANCE;
    }

    public static Grille getInstance() {
        return INSTANCE;
    }

    public void addAgent(Position initialPosition, Position finalPosition) {
        agents.add(new Agent(agents.size(), initialPosition, finalPosition));
    }

    public void runAgents() {
        for (Agent agent : agents) {
            new Thread(agent).start();
        }
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public boolean samePosition(Agent agent) {
        for (Agent a : agents) {
            if(a.getPosition().equals(agent.getPosition()))
                return false;
        }
        return false;
    }

    public Agent findAgentByPosition(Position position) {
        for (Agent agent : agents) {
            if (agent.getPosition() == position)
                return agent;
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}
