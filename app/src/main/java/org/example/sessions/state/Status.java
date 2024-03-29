package org.example.sessions.state;

import java.io.IOException;

import org.example.packets.Packet;
import org.example.packets.PingPacket;
import org.example.packets.StatusPacket;
import org.example.packets.communication.Packeteer;
import org.example.sessions.ClientConnection;

public class Status extends ClientState {

    private static Packeteer packeteer = new Packeteer();

    static{
        packeteer.registerPackageType(0, StatusPacket.class);
        packeteer.registerPackageType(1, PingPacket.class);
    }

    public Status(ClientConnection _clientConnection) {
        super(_clientConnection);
    }

    @Override
    public void stateActivity() throws IOException {
        Packet packet = packeteer.receivePacket(getClientConnection().getPacketDataCommunication());

        if(packet instanceof StatusPacket sp){
            System.out.println("Status request received from " + getClientConnection().getSocketAddress());
            packeteer.sendPacket(getClientConnection().getPacketDataCommunication(), sp);
        }

        packet = packeteer.receivePacket(getClientConnection().getPacketDataCommunication());

        if(packet instanceof PingPacket pp){
            packeteer.sendPacket(getClientConnection().getPacketDataCommunication(), pp);
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
