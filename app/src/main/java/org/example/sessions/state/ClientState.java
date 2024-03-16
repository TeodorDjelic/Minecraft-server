package org.example.sessions.state;

import java.io.IOException;
import java.net.Socket;

import org.example.sessions.ClientConnection;

public abstract class ClientState {
    
    private final ClientConnection clientConnection;

    public ClientState(ClientConnection _clientConnection){
        this.clientConnection = _clientConnection;
    }

    public abstract void stateActivity() throws IOException;
    
    public abstract ClientState getNextState();
    
    public abstract boolean shouldConnectionBeKeptAlive();

    protected ClientConnection getClientConnection(){
        return clientConnection;
    }

}
