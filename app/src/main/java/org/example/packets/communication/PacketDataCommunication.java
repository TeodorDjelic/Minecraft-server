package org.example.packets.communication;

import java.io.IOException;

import org.example.packets.PacketData;

/**
 * Packet handler for a given open client socket connection
 * 
 * @author Teodor Đelić
 * @version 1.0
 * 
 */
public interface PacketDataCommunication {

    /**
     * Receives a packet from the client.
     * 
     * @return inbound client packet
     * @throws IOException If the client connection fails
     */
    public abstract PacketData receivePacketData() throws IOException;

    /**
     * Sends a packet to the client
     * 
     * @param packet outbound server packet
     * @throws IOException If the client connection fails
     */
    public abstract void sendPacketData(PacketData packet) throws IOException;

}
