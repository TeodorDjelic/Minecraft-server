package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;

import org.example.binary.Masks;

public class VarIntField {
    
    private static final int PAYLOAD_PER_BYTE = 7;
    
    private int value;

    private int length = 0;


    public VarIntField(InputStream is) throws IOException{

        byte[] data;

        do{

            value >>= 7;

            data = is.readNBytes(1);

            if(data.length != 1){
                throw new IOException();
            }

            int dataSegment = data[0] & ~Masks.MOST_SIGNIFICANT_BIT_OF_BYTE;
            dataSegment <<= PAYLOAD_PER_BYTE * length;
            length++;
            value |= dataSegment;

        }while((data[0] & Masks.MOST_SIGNIFICANT_BIT_OF_BYTE) != 0);

    }

    public int getValue(){
        return value;
    }

    public int getLength(){
        return length;
    }

}
