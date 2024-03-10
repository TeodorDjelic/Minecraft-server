package org.example.packets;

import java.io.InputStream;

import org.example.primitives.VarIntField;

public abstract class Packet{
    
    public Packet(InputStream is, int dataLength){
        parseData(is, dataLength);
    }

    protected abstract void parseData(InputStream is, int dataLength);

}
