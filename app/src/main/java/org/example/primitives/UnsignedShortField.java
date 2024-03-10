package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;

public class UnsignedShortField {
    
    private short value;

    public UnsignedShortField(InputStream is) throws IOException{

        byte[] data = is.readNBytes(2);

        if(data.length != 2){
            throw new IOException();
        }

        value = (short) ((Byte.toUnsignedInt(data[0]) << 8) | Byte.toUnsignedInt(data[1]));
    }

    public short getValue(){
        return value;
    }

}
