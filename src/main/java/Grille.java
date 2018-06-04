import java.util.ArrayList;

public class Grille {

    private int size;
    private ArrayList<Agent> agents;

    public Grille(int size) {
        this.size = size;
        this.agents = new ArrayList<Agent>();
    }

    public void addAgent(Position initialPosition, Position finalPosition) {
        agents.add(new Agent(agents.size(), initialPosition, finalPosition));
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void moveLeft(int idAgent) {
       agents.get(idAgent).moveLeft();
    }

    public void moveRight(int idAgent) {
        agents.get(idAgent).moveRight();
    }

    public void moveUp(int idAgent) {
        agents.get(idAgent).moveUp();
    }

    public void moveDown(int idAgent) {
        agents.get(idAgent).moveDown();
    }

}
