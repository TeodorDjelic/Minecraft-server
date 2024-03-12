package org.example.packets;

import org.example.sessions.ClientConnection;

public abstract class Packeter {

    protected ClientConnection clientConnection;

    public Packeter(ClientConnection _clientConnection){
        this.clientConnection = _clientConnection;
    }

    public abstract PacketData receivePacketData();
    public abstract void sendPacketData(PacketData packet);

}
