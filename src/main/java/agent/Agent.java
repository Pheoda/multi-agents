package agent;

import tools.Utils;

import java.util.ArrayList;
import java.util.Observable;

public class Agent extends Observable implements Runnable {

    private int id;
    private Position position;
    private Position finalPosition;
    private Position nextPosition;
    private ArrayList<Message> messages;

    public Agent(int id, Position position, Position finalPosition) {
        this.id = id;
        this.position = position;
        this.finalPosition = finalPosition;
        this.messages = new ArrayList<>();
        this.nextPosition = position;
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
        nextPosition.setRow(position.getRow() - 1);
        checkMoveIsOk();
    }

    public void moveRight() {
        nextPosition.setRow(position.getRow() + 1);
        checkMoveIsOk();
    }

    public void moveUp() {
        nextPosition.setColumn(position.getColumn() - 1);
        checkMoveIsOk();
    }

    public void moveDown() {
        nextPosition.setColumn(position.getColumn() + 1);
        checkMoveIsOk();
    }

    private void checkMoveIsOk() {
        if (! Grille.getInstance().samePosition(this)) {
            this.setPosition(nextPosition);
        }
        else {
            sendMessage(Grille.getInstance().findAgentByPosition(nextPosition), Utils.TYPE.DO);
        }
    }

    private void sendMessage(Agent agent, Utils.TYPE type) {
        agent.addMessage(new Message(agent, type));
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
        int size = Grille.getInstance().getSize();

        if (Math.abs(position.getRow() - finalPosition.getRow()) >
                Math.abs(position.getColumn() - finalPosition.getColumn())) {
            if (position.getRow() - finalPosition.getRow() > 0) {
                this.moveUp();
            } else {
                this.moveDown();
            }
        } else {
            if (position.getColumn() - finalPosition.getColumn() > 0) {
                this.moveRight();
            } else {
                this.moveLeft();
            }
        }
    }

    public void run() {
        System.out.println("AGENT STARTED");
        while(true) {
            System.out.println("position : [" + position.getRow() + ";" + position.getColumn() + "]");
            this.move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }


        /*while (!Grille.getInstance().finalPositionsForAll()) {
            if (!this.rightPosition()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Grille.getInstance().move(this);
            }
        }*/
    }
}
