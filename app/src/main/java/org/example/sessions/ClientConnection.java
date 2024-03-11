package org.example.sessions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.example.sessions.state.ClientState;

public class ClientConnection extends Thread {

    private final Socket clientSocket;
    
    private ClientState clientState;
    
    public ClientConnection(Socket _clientSocket){
        this.clientSocket = _clientSocket;
    }

    @Override
    public void run() {
        while(clientState.shouldConnectionBeKeptAlive()){
            clientState.stateActivity();
            clientState = clientState.getNextState();
        }

        try {
            if(!clientSocket.isClosed())
                clientSocket.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public InputStream getInputStream() throws IOException{
        return clientSocket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException{
        return clientSocket.getOutputStream();
    }
    

}
