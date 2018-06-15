package agent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Agent extends Observable implements Runnable {

    private int id;
    private Position position;
    private Position finalPosition;
    private Random rand;
    private boolean hasToMove;

    public Agent(int id, Position position, Position finalPosition) {
        this.id = id;
        this.position = position;
        this.finalPosition = finalPosition;
        this.rand = new Random();
        this.hasToMove = false;
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

    public void askToMove() {
        this.hasToMove = true;
    }

    private void checkMoveIsOk(Position nextPosition) {
        if (Grille.getInstance().isPositionFree(nextPosition)) { // Can move
            this.hasToMove = false;
            this.setPosition(nextPosition);
        } else { // On ne peut pas bouger à la position souhaitée

            if (this.hasToMove && randomMove()) // S'il faut bouger et qu'on peut faire un random move
                this.hasToMove = false;
            else {
                // 20% de chance random move au lieu de demander de bouger
                int prob = rand.nextInt(100);
                if(prob < 80)
                    Grille.getInstance().findAgentByPosition(nextPosition).askToMove();
                else
                    randomMove();
            }
        }
    }

    public boolean rightPosition() {
        return position.equals(finalPosition);
    }


    private void move() {
        // semaphore : on prend un jeton
        try {
            Grille.getInstance().getSemaphore().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int deltaRow = position.getRow() - finalPosition.getRow();
        int deltaColumn = position.getColumn() - finalPosition.getColumn();

        /* TODO : ici, si un autre Agent bloque celui-ci, il faudrait faire en sorte qu'il puisse vérifier
        si l'autre position qui lui ferait gagner du terrain n'est pas libre. On évite ainsi de faire déplacer un agent pour rien.
         */

        if (deltaRow != 0 || deltaColumn != 0) {
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
        else {
            if (this.hasToMove && randomMove()) // S'il faut bouger et qu'on peut faire un random move
                this.hasToMove = false;
        }

        // on repose un jeton
        Grille.getInstance().getSemaphore().release();
    }

    @Override
    public boolean equals(Object obj) {
        Agent a = (Agent) obj;
        return a.getId() == this.id;
    }

    private boolean randomMove() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        Grille grid = Grille.getInstance();
        Position positionTested;

        positionTested = new Position(this.position.getRow() - 1, this.position.getColumn());
        if (grid.isPositionFree(positionTested)) // Haut
            possibleMoves.add(positionTested);

        positionTested = new Position(this.position.getRow() + 1, this.position.getColumn());
        if (grid.isPositionFree(positionTested)) // Bas
            possibleMoves.add(positionTested);

        positionTested = new Position(this.position.getRow(), this.position.getColumn() - 1);
        if (grid.isPositionFree(positionTested)) // Gauche
            possibleMoves.add(positionTested);

        positionTested = new Position(this.position.getRow(), this.position.getColumn() + 1);
        if (grid.isPositionFree(positionTested)) // Droite
            possibleMoves.add(positionTested);

        if (!possibleMoves.isEmpty()) {
            this.position = possibleMoves.get(rand.nextInt(possibleMoves.size()));
            return true;
        }
        return false; // On n'a pas bougé
    }

    public void run() {
//        System.out.println(this.id + " : " + this.position + " -> " + this.finalPosition);
        while (true) {
//            System.out.println(this.getId() + " : [" + position.getRow() + ";" + position.getColumn() + "]");
            this.move();
            try {
                // Sleep between 0 and 100 ms
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }
    }
}


