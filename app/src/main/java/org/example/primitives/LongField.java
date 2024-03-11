package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;

public class LongField extends Field<Long>{

    public LongField(InputStream is) throws IOException{
        byte[] data = is.readNBytes(8);

        value = 0L;

        if(data.length != 8){
            throw new IOException();
        }

        for(int i = 0; i < 8; i++){
            value <<= 8;
            value |= Byte.toUnsignedLong(data[i]);
        }   
    }

    public LongField(Long _value){
        super(_value);
    }

    @Override
    public byte[] getBytes() {
        byte[] data = new byte[8];

        long temp = value;

        for(int i = 7; i >= 0; i--){
            data[i] = (byte) temp;
            temp >>>= 8;
        }

        return data;
    }

}
