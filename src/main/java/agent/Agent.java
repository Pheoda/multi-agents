package agent;

import tools.Utils;

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
        if (Grille.getInstance().isPositionFree(nextPosition)) {
            System.out.println("DEPLACE : " + this.id + "->" + nextPosition);
            this.setPosition(nextPosition);
        }
        else {
            System.out.println("MESSAGE : " + this.id + "->" + nextPosition);
            new Message(nextPosition, Utils.TYPE.DO);
        }
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public boolean rightPosition() {
        return position.equals(finalPosition);
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

    @Override
    public boolean equals(Object obj) {
        Agent a = (Agent) obj;
        return a.getId() == this.id;
    }

    public void run() {
        System.out.println(this.id + " : " + this.position + " -> " + this.finalPosition);
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
