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
        ArrayList<Integer> idGenerated = new ArrayList<>();
        ArrayList<Position> positionsTaken = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < number; i++) {
            int randomId;
            do {
                randomId = random.nextInt(size * size);
            } while (idGenerated.contains(randomId));
            idGenerated.add(randomId);

            Position randomPosition;
            do {
                randomPosition = new Position(random.nextInt(this.size), random.nextInt(this.size));
            } while (positionsTaken.contains(randomPosition));
            positionsTaken.add(randomPosition);

            Position finalPosition = new Position(randomId / this.size, randomId % this.size);

            addAgent(randomPosition, finalPosition, randomId);
        }
    }

    public void addAgent(Position initialPosition, Position finalPosition, int id) {
        agents.add(new Agent(id, initialPosition, finalPosition));
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
        // Out of bound check
        if (position.getColumn() < 0 || position.getColumn() >= this.size || position.getRow() < 0 || position.getRow() >= this.size)
            return false;

        // Agents check
        for (Agent agent : agents) {
            if (agent.getPosition().equals(position))
                return false;
        }
        return true;
    }

    public Agent findAgentByPosition(Position position) {
        for (Agent agent : agents) {
            if (agent.getPosition().equals(position))
                return agent;
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}
