package org.example.packets;

import java.io.IOException;
import java.io.InputStream;

import org.example.primitives.VarIntField;

public class PacketReader {
    
    public static void readPacket(InputStream is) throws IOException{
        
        VarIntField length = new VarIntField(is);
        VarIntField packetID = new VarIntField(is);

        int dataLength = length.getValue() - packetID.getLength();

        System.out.println("PacketID: " + packetID.getValue()
        + ", data length: " + dataLength);


        byte[] data = is.readNBytes(dataLength);

        for(byte b: data){
            System.out.println(b);
        }

    }

}
