package org.example.packets;

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

    public byte[] getData() {
        return data;
    }

}
