package agent;

import java.util.ArrayList;
import java.util.Observable;

public class Agent extends Observable implements Runnable {
    private int id;
    private Position position;
    private Position finalPosition;
    private ArrayList<Message> messages;

    public Agent(int id, Position position, Position finalPosition) {
        this.id = id;
        this.position = position;
        this.finalPosition = finalPosition;
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
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
        checkMoveIsOk(new Position(position.getRow(), position.getColumn() - 1));
    }

    public void moveRight() {
        checkMoveIsOk(new Position(position.getRow(), position.getColumn() + 1));
    }

    public void moveUp() {
        checkMoveIsOk(new Position(position.getRow() - 1, position.getColumn()));
    }

    public void moveDown() {
        checkMoveIsOk(new Position(position.getRow() + 1, position.getColumn()));
    }

    private void checkMoveIsOk(Position nextPosition) {
        if (! Grille.getInstance().samePosition(this)) {
            this.setPosition(nextPosition);
        }
        else {
            System.out.println("MESSAGE : " + this.getId() + "->" + nextPosition);
            new Message(nextPosition).sendIfPossible();
        }
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public boolean rightPosition() {
        return position.equals(finalPosition);
    }

    public boolean samePosition(Agent agent) {
        return this.position.equals(agent.position);
    }

    private void move() {
        int deltaRow = position.getRow() - finalPosition.getRow();
        int deltaColumn = position.getColumn() - finalPosition.getColumn();

        // TODO : Check messages list if agent can't move !
        if(deltaRow != 0 || deltaColumn != 0) {
            if (Math.abs(deltaRow) > Math.abs(deltaColumn)) { // Move row
                if (deltaRow > 0) {
                    this.moveUp();
                } else {
                    this.moveDown();
                }
            } else { // Move column
                if (deltaColumn > 0) {
                    this.moveLeft();
                } else {
                    this.moveRight();
                }
            }
        }
    }

    public void run() {
        while(true) {
//            System.out.println(this.getId() + " : [" + position.getRow() + ";" + position.getColumn() + "]");
            this.move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }
    }
}
