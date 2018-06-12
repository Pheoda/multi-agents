package agent;

public class Message {

    private Agent to;    // Can be null !

    private Position toFree;


    /**
     * agent.Message : create a message with to (can be null)
     * @param positionTo : position to be freed
     */
    public Message(Position positionTo) {
        this.to = Grille.getInstance().findAgentByPosition(positionTo);
        this.toFree = positionTo;
    }

    /**
     * Add the message to the destination agent.Agent ONLY if "to" is not null
     */
    public void sendIfPossible() {
        if(this.to != null)
            this.to.addMessage(this);
    }
}
