package org.example.packets;

import java.util.Arrays;

import org.example.primitives.StringField;
import org.example.primitives.UnsignedShortField;
import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.InvalidData;

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
        byte[] data = packetData.getData();

        int dataLength = data.length;
        int current = 0;

        this.protocolVersion = new VarIntField(
            Arrays.copyOfRange(data, current, current + 5)
        );

        current += protocolVersion.getLength();

        this.serverAddress = new StringField(
            Arrays.copyOfRange(data, current, dataLength),
            255);

        current += serverAddress.getLength();

        this.serverPort = new UnsignedShortField(
            Arrays.copyOfRange(data, current, current + 2)
        );

        current += serverPort.getLength();

        this.nextState = new VarIntField(
            Arrays.copyOfRange(data, current, dataLength)
        );
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
