package org.example.packets;

import org.example.structures.OffsetByteArray;

public class PacketData {
    
    private int packetID;
    private byte[] data;

    public PacketData(int _packetID, byte[] _data){
        this.packetID = _packetID;
        this.data = _data;
    }

    public int getPacketID() {
        return packetID;
    }

    public OffsetByteArray getData() {
        return getData(0);
    }

    public OffsetByteArray getData(int offset){
        return new OffsetByteArray(data, offset);
    }

}
