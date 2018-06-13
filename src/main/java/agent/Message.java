package agent;

import tools.Utils.TYPE;

public class Message {

    private Agent to;    // Can be null !

    private Position toFree;

    private TYPE type;

    /**
     * agent.Message : create a message with to (can be null)
     * @param agent : agent to be moved
     */
    public Message(Agent agent, TYPE type) {
        this.to = agent;
        this.toFree = agent.getPosition();
        this.type = type;
    }

    /**
     * Add the message to the destination agent.Agent ONLY if "to" is not null
     */
    public void sendIfPossible() {
        if(this.to != null)
            this.to.addMessage(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Agent.class) {
            return false;
        }
        Agent agent = (Agent) obj;
        return this.to.equals(agent) && this.toFree.equals(agent.getPosition());
    }
}
