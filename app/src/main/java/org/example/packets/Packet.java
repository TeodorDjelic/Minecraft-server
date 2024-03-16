package org.example.packets;

import org.example.primitives.exceptions.InvalidData;

public abstract class Packet {

    public Packet(PacketData packetData) throws InvalidData{
        setPacketData(packetData);
    }

    public abstract PacketData getPacketData();
    protected abstract void setPacketData(PacketData packetData) throws InvalidData;

}
