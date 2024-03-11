package org.example.sessions.state;

import org.example.sessions.ClientConnection;

public class Handshaking extends ClientState{

    private boolean hasAnErrorOccured = false;

    public Handshaking(ClientConnection _clientConnection) {
        super(_clientConnection);
    }

    @Override
    public void stateActivity() {

    }

    @Override
    public ClientState getNextState() {
        return null;
    }

    @Override
    public boolean shouldConnectionBeKeptAlive() {
        return !hasAnErrorOccured;
    }
    
}
