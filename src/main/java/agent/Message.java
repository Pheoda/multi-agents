package agent;

import javax.swing.*;

public class Message {

    private Agent to;    // Can be null !

    private Position toFree;


    /**
     * agent.Message : create a message with to (can be null)
     * @param positionToFree : position to be freed
     */
    public Message(Position positionToFree) {
        this.to = Grille.getInstance().findAgentByPosition(positionToFree);
        this.toFree = positionToFree;
    }

    /**
     * Add the message to the destination agent.Agent ONLY if "to" is not null
     */
    public void sendIfPossible() {
        if(this.to != null)
            this.to.addMessage(this);
    }
}
