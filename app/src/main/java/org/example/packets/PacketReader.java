package org.example.packets;

import java.io.IOException;
import java.io.InputStream;

import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;

public class PacketReader {
    
    public static void readPacket(InputStream is) throws IOException{
        
        VarIntField length = new VarIntField(is);
        VarIntField packetID = new VarIntField(is);

        int dataLength = length.getValue() - packetID.getLength();

        System.out.println("PacketID: " + packetID.getValue()
        + ", data length: " + dataLength);


        try {
            HandshakePacket handshakePacket = new HandshakePacket(is, dataLength);
        } catch (StringFieldLengthAboveCapacity e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
