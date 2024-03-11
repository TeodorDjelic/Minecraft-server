package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;

public class UnsignedShortField extends Field<Short> {

    public UnsignedShortField(InputStream is) throws IOException{

        byte[] data = is.readNBytes(2);

        if(data.length != 2){
            throw new IOException();
        }

        value = (short) ((Byte.toUnsignedInt(data[0]) << 8) | Byte.toUnsignedInt(data[1]));
    }

    @Override
    public byte[] getBytes() {
        byte[] data = new byte[2];

        data[1] = (byte) value.shortValue();
        data[0] = (byte) (value >>> 8);

        return data;
    }

}
