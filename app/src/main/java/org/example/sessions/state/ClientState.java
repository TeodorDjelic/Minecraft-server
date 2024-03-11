package org.example.sessions.state;

import java.net.Socket;

import org.example.sessions.ClientConnection;

public abstract class ClientState {
    
    protected final ClientConnection clientConnection;

    public ClientState(ClientConnection _clientConnection){
        this.clientConnection = _clientConnection;
    }

    public abstract void stateActivity();
    
    public abstract ClientState getNextState();
    
    public abstract boolean shouldConnectionBeKeptAlive();

}
