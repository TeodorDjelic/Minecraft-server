package org.example.primitives;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.example.binary.Masks;
import org.example.primitives.exceptions.InvalidData;
import org.example.primitives.exceptions.VarIntAboveCapacity;
import org.example.structures.OffsetByteArray;

public class VarIntField {
    
    private static final int PAYLOAD_PER_BYTE = 7;

    private int value;

    public VarIntField(OffsetByteArray _data) throws InvalidData {
        setBytes(_data);    
    }

    public VarIntField(int _value){
        this.value = _value;
    }

    public VarIntField(InputStream is) throws IOException, VarIntAboveCapacity{
        value = 0;
        int length = 0;

        byte[] data;

        do{
            data = is.readNBytes(1);

            if(data.length != 1){
                throw new IOException();
            }

            int dataSegment = data[0] & ~Masks.MOST_SIGNIFICANT_BIT_OF_BYTE;
            dataSegment <<= PAYLOAD_PER_BYTE * length;
            length++;
            value |= dataSegment;

            if(length > 5){
                throw new VarIntAboveCapacity();
            }

        }while((data[0] & Masks.MOST_SIGNIFICANT_BIT_OF_BYTE) != 0);

    }

    public byte[] getBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int temp = value;

        while ((temp & 0xFFFFFF80) != 0L) {
            outputStream.write((byte) ((temp & 0x7F) | 0x80));
            temp >>>= PAYLOAD_PER_BYTE;
        }

        if(value == 0 || temp != 0){
            outputStream.write((byte) (temp & 0x7F));
        }

        return outputStream.toByteArray();
    }

    public void setBytes(OffsetByteArray data) throws VarIntAboveCapacity {
        value = 0;
        int length = 0;

        do{
            int dataSegment = data.get(length) & ~Masks.MOST_SIGNIFICANT_BIT_OF_BYTE;
            dataSegment <<= PAYLOAD_PER_BYTE * length;
            length++;
            value |= dataSegment;

            if(length > 5){
                throw new VarIntAboveCapacity();
            }

        }while((data.get(length - 1) & Masks.MOST_SIGNIFICANT_BIT_OF_BYTE) != 0);
    }

    public int getValue(){
        return value;
    }

    public int getLength(){
        return getBytes().length;
    }

}
