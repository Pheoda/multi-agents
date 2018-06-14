package agent;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Grille {

    private int size;
    private ArrayList<Agent> agents;
    private static Grille INSTANCE = null;

    private Semaphore semaphore;


    private Grille(int size) {
        this.size = size;
        this.agents = new ArrayList<>();
        this.semaphore = new Semaphore(1);
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

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void generateAgent(int number) {
        ArrayList<Position> positionsTaken = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < number; i++) {
            Position randomPosition;
            do {
                randomPosition = new Position(random.nextInt(this.size), random.nextInt(this.size));
            }while (positionsTaken.contains(randomPosition));
            Position finalPosition = new Position(i / this.size, i % this.size);

            addAgent(randomPosition, finalPosition);
            positionsTaken.add(randomPosition);
        }
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

    public boolean isPositionFree(Position position) {
        for(Agent agent : agents) {
            if(agent.getPosition().equals(position))
                return false;
        }
        return true;
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
