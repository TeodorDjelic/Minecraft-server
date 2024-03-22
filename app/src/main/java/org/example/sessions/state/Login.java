package org.example.sessions.state;

import java.io.IOException;

import org.example.packets.LoginStartPacket;
import org.example.packets.Packet;
import org.example.packets.communication.Packeteer;
import org.example.sessions.ClientConnection;

public class Login extends ClientState {

    private static Packeteer packeteer = new Packeteer();

    static{
        packeteer.registerPackageType(0, LoginStartPacket.class);
    }

    public Login(ClientConnection _clientConnection) {
        super(_clientConnection);
    }

    @Override
    public void stateActivity() throws IOException {
        Packet packet = packeteer.receivePacket(getClientConnection().getPacketDataCommunication());

        if(packet instanceof LoginStartPacket lsp){
            System.out.println("Player name: " + lsp.getName());
            System.out.println("Player UUID: " + lsp.getUUID());
        }
    }

    @Override
    public ClientState getNextState() {
        return null;
    }

    @Override
    public boolean shouldConnectionBeKeptAlive() {
        return false;
    }
    
}
