package org.example.sessions.state;

import java.io.IOException;

import org.example.packets.HandshakePacket;
import org.example.packets.Packet;
import org.example.packets.communication.Packeteer;
import org.example.sessions.ClientConnection;

// LEGACY STANDARD IS NOT BEING HANDLED

public class Handshaking extends ClientState{

    private static Packeteer packeteer = new Packeteer();

    static{
        packeteer.registerPackageType(0, HandshakePacket.class);
    }

    private ClientState nextClientState;
    private boolean hasAnErrorOccured = false;

    public Handshaking(ClientConnection _clientConnection) {
        super(_clientConnection);
    }

    @Override
    public void stateActivity() throws IOException {
        Packet packet = packeteer.receivePacket(getClientConnection().getPacketDataCommunication());

        if(packet == null){
            hasAnErrorOccured = true;
        }

        if(packet instanceof HandshakePacket hpacket){
            System.out.println("Protocol version: " + hpacket.getProtocolVersion());
            System.out.println("Server address: " + hpacket.getServerAddress());
            System.out.println("Server port: " + hpacket.getServerPort());
            System.out.println("Next state: " + hpacket.getNextState());

            if(hpacket.getNextState() == HandshakePacket.NextState.Status){
                nextClientState = new Status(getClientConnection());
            }
            else if(hpacket.getNextState() == HandshakePacket.NextState.Login){
                throw new UnsupportedOperationException("Not implemented.");
            }
            else{
                hasAnErrorOccured = true;
            }
        }
        else{
            hasAnErrorOccured = true;
        }
    }

    @Override
    public ClientState getNextState() {
        return nextClientState;
    }

    @Override
    public boolean shouldConnectionBeKeptAlive() {
        return !hasAnErrorOccured;
    }
    
}
