public class Agent implements Runnable {

    public enum MOVE {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private int id;
    private Position position;
    private Position finalPosition;
    private MOVE move;

    public Agent(int id, Position position, Position finalPosition) {
        this.id = id;
        this.position = position;
        this.finalPosition = finalPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    public MOVE getMove() {
        return move;
    }

    public void moveLeft() {
        position.setX(position.getX() - 1);
        move = MOVE.LEFT;
    }

    public void moveRight() {
        position.setX(position.getX() + 1);
        move = MOVE.RIGHT;
    }

    public void moveUp() {
        position.setY(position.getY() - 1);
        move = MOVE.UP;
    }

    public void moveDown() {
        position.setY(position.getY() + 1);
        move = MOVE.DOWN;
    }

    public boolean rightPosition() {
        return position.equals(finalPosition);
    }

    public boolean samePosition(Agent agent) {
        this.position.equals(agent.position);
    }

    public void run() {
        while (!Grille.getInstance().finalPositionsForAll()) {
            if (!this.rightPosition()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Grille.getInstance().move(this);
            }
        }
    }
}
