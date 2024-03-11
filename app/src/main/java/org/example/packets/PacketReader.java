package org.example.packets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;
import org.example.primitives.exceptions.VarIntAboveCapacity;

public class PacketReader {
    
    public static void readPacket(InputStream is, OutputStream os) throws IOException, VarIntAboveCapacity, InvalidStringFieldFormat{
        
        VarIntField length = new VarIntField(is);
        VarIntField packetID = new VarIntField(is);

        int dataLength = length.getValue() - packetID.getLength();

        System.out.println("PacketID: " + packetID.getValue()
        + ", data length: " + dataLength);


        try {
            HandshakePacket handshakePacket = new HandshakePacket(is, os, dataLength);
        } catch (StringFieldLengthAboveCapacity | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
