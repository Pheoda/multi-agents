package agent;

public class Message {

    private Agent to;    // Can be null !

    private Position toFree;


    /**
     * agent.Message : create a message with to (can be null)
     * @param agent : agent to be moved
     */
    public Message(Agent agent) {
        this.to = agent;
        this.toFree = agent.getPosition();
    }

    /**
     * Add the message to the destination agent.Agent ONLY if "to" is not null
     */
    public void sendIfPossible() {
        if(this.to != null)
            this.to.addMessage(this);
    }
}
