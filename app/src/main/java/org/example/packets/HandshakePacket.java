package org.example.packets;

import org.example.primitives.StringField;
import org.example.primitives.UnsignedShortField;
import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.InvalidData;
import org.example.structures.OffsetByteArray;

public class HandshakePacket extends Packet {

    private VarIntField protocolVersion;
    private StringField serverAddress;
    private UnsignedShortField serverPort;
    private VarIntField nextState;

    public static enum NextState{
        Status, Login, Unknown
    }
    
    public HandshakePacket(PacketData _packetData) throws InvalidData {
        super(_packetData);   
    }

    @Override
    public PacketData getPacketData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPacketData'");
    }

    @Override
    protected void setPacketData(PacketData packetData) throws InvalidData {
        OffsetByteArray data = packetData.getData();

        int dataLength = data.length();
        int current = 0;

        this.protocolVersion = new VarIntField(data);

        current += protocolVersion.getLength();

        this.serverAddress = new StringField(
            data.offset(current),
            255);

        current += serverAddress.getLength();

        this.serverPort = new UnsignedShortField(
            data.offset(current)
        );

        current += serverPort.getLength();

        this.nextState = new VarIntField(
            data.offset(current)
        );

        current += nextState.getLength();

        if(current != dataLength){
            throw new InvalidData();
        }
    }

    public int getProtocolVersion() {
        return protocolVersion.getValue();
    }

    public String getServerAddress() {
        return serverAddress.getValue();
    }

    public short getServerPort() {
        return serverPort.getValue();
    }

    public NextState getNextState() {
        switch(nextState.getValue()){
            case 1: return NextState.Status;
            case 2: return NextState.Login;
            default: return NextState.Unknown;
        }
    }

    
}
