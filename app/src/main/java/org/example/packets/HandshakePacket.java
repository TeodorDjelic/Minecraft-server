package org.example.packets;

import java.io.InputStream;

import org.example.primitives.VarIntField;

public class HandshakePacket extends Packet {

    private VarIntField protocolVersion;
    //private StringField serverAddress;

    public HandshakePacket(InputStream is, int dataLength) {
        super(is, dataLength);
    }

    @Override
    protected void parseData(InputStream is, int dataLength) {
        
    }
    
}
