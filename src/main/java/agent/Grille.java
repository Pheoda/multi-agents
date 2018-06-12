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

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void moveLeft(int idAgent) {
        agents.get(idAgent).moveLeft();
        if (samePosition()) {
            agents.get(idAgent).moveRight();
        }
    }

    public void moveRight(int idAgent) {
        agents.get(idAgent).moveRight();
        if (samePosition()) {
            agents.get(idAgent).moveLeft();
        }
    }

    public void moveUp(int idAgent) {
        agents.get(idAgent).moveUp();
        if (samePosition()) {
            agents.get(idAgent).moveDown();
        }
    }

    public void moveDown(int idAgent) {
        agents.get(idAgent).moveDown();
        if (samePosition()) {
            agents.get(idAgent).moveUp();
        }
    }

    private boolean samePosition(Agent agent) {
        return agents.stream()
                .filter(agent1 -> agent != agent1)
                .anyMatch(agent1 -> agent.getPosition().equals(agent1.getPosition()));
    }

    private boolean samePosition() {
        return agents.stream().anyMatch(agent -> samePosition(agent));
    }


    public void move(Agent agent) {
        Position oldPosition = agent.getPosition();
        if (Math.abs(agent.getPosition().getX() - agent.getFinalPosition().getX()) >
                Math.abs(agent.getPosition().getY() - agent.getFinalPosition().getY())) {
            if (agent.getPosition().getX() - agent.getFinalPosition().getX() > 0) {
                agent.moveLeft();
            } else {
                agent.moveRight();
            }
        } else {
            if (agent.getPosition().getY() - agent.getFinalPosition().getY() > 0) {
                agent.moveUp();
            } else {
                agent.moveDown();
            }
        }

        // if not moving
        if (oldPosition == agent.getPosition()) {
            switch (agent.getMove()) {
                case UP:
                    new Message(agent, new Position(agent.getPosition().getX(), agent.getPosition().getY() - 1)).sendIfPossible();
                    break;
                case DOWN:
                    new Message(agent, new Position(agent.getPosition().getX(), agent.getPosition().getY() + 1)).sendIfPossible();
                    break;
                case LEFT:
                    new Message(agent, new Position(agent.getPosition().getX() - 1, agent.getPosition().getY())).sendIfPossible();
                    break;
                case RIGHT:
                    new Message(agent, new Position(agent.getPosition().getX() + 1, agent.getPosition().getY())).sendIfPossible();
                    break;
            }

        }
    }

    public Agent findAgentByPosition(Position position) {
        for (Agent agent : agents) {
            if (agent.getPosition() == position)
                return agent;
        }
        return null;

        // Before :
        //return agents.stream().filter(agent -> agent.getPosition().equals(position));
    }

    public boolean finalPositionsForAll() {
        return this.getAgents().stream().allMatch(Agent::rightPosition);
    }

    public int getSize() {
        return size;
    }
}
