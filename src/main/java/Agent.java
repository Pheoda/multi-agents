import graphics.Window;

public class Agent implements Runnable {

    private int id;
    private Position position;
    private Position finalPosition;

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

    public void moveLeft() {
        position.setX(position.getX() - 1);
    }

    public void moveRight() {
        position.setX(position.getX() + 1);
    }

    public void moveUp() {
        position.setY(position.getY() - 1);
    }

    public void moveDown() {
        position.setY(position.getY() + 1);
    }

    public void run() {
        while(true) {
        }
    }
}
