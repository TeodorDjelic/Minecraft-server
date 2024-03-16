package org.example.sessions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import org.example.packets.communication.PacketDataCommunication;
import org.example.packets.communication.UncompressedPacketDataCommunication;
import org.example.sessions.state.ClientState;
import org.example.sessions.state.Handshaking;

public class ClientConnection extends Thread {

    private final Socket clientSocket;
    
    private ClientState clientState;

    private PacketDataCommunication packetDataCommunication;
    
    public ClientConnection(Socket _clientSocket){
        this.clientSocket = _clientSocket;
        packetDataCommunication = new UncompressedPacketDataCommunication(this);
        clientState = new Handshaking(this);
    }

    @Override
    public void run() {

        try {
            clientState.stateActivity();

            while(clientState.shouldConnectionBeKeptAlive()){
                clientState = clientState.getNextState();
                clientState.stateActivity();
            }
            
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

    public PacketDataCommunication getPacketDataCommunication(){
        return packetDataCommunication;
    }

    public void setPacketDataCommunication(PacketDataCommunication packetDataCommunication) {
        this.packetDataCommunication = packetDataCommunication;
    }

    public SocketAddress getSocketAddress(){
        return clientSocket.getRemoteSocketAddress();
    }


}
