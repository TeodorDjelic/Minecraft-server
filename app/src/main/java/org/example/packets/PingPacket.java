package org.example.packets;

import org.example.primitives.LongField;
import org.example.primitives.exceptions.InvalidData;

public class PingPacket extends Packet {

    private LongField payload;

    public PingPacket(PacketData packetData) throws InvalidData {
        super(packetData);
    }

    @Override
    public PacketData getPacketData() {
        return new PacketData(1, payload.getBytes());
    }

    @Override
    protected void setPacketData(PacketData packetData) throws InvalidData {
        payload = new LongField(packetData.getData());
    }

    public long getPayload(){
        return payload.getValue();
    }
    
}
