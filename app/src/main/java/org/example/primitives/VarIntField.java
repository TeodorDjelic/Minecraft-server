package org.example.primitives;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.example.binary.Masks;
import org.example.primitives.exceptions.InvalidData;
import org.example.primitives.exceptions.VarIntAboveCapacity;

public class VarIntField extends Field<Integer> {
    
    private static final int PAYLOAD_PER_BYTE = 7;

    private int length = 0;

    public VarIntField(byte[] _data) throws InvalidData {
        super(_data);
    }

    public VarIntField(int _value){
        super(_value);
    }

    public int getLength(){
        return length;
    }

    @Override
    public byte[] getBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int temp = value;

        while ((temp & 0xFFFFFF80) != 0L) {
            outputStream.write((byte) ((temp & 0x7F) | 0x80));
            temp >>>= 7;
        }

        outputStream.write((byte) (temp & 0x7F));

        return outputStream.toByteArray();
    }

    @Override
    public void setBytes(byte[] data) throws VarIntAboveCapacity {
        value = 0;

        do{
            int dataSegment = data[length] & ~Masks.MOST_SIGNIFICANT_BIT_OF_BYTE;
            dataSegment <<= PAYLOAD_PER_BYTE * length;
            length++;
            value |= dataSegment;

            if(length > 5){
                throw new VarIntAboveCapacity();
            }

        }while((data[0] & Masks.MOST_SIGNIFICANT_BIT_OF_BYTE) != 0);
    }

}
