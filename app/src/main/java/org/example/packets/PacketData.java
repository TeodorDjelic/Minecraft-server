package org.example.packets;

public class PacketData {
    
    private int length;
    private int packetID;
    private byte[] data;

    public PacketData(int _length, int _packetID, byte[] _data){
        this.length = _length;
        this.packetID = _packetID;
        this.data = _data;
    }

    public int getLength() {
        return length;
    }

    public int getPacketID() {
        return packetID;
    }

    public byte[] getData() {
        return data;
    }

}
