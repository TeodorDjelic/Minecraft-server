package org.example.primitives;

import org.example.binary.TypeSizes;
import org.example.primitives.exceptions.InvalidData;
import org.example.structures.OffsetByteArray;

public class LongField {

    private long value;

    public LongField(OffsetByteArray _data) throws InvalidData{
        setBytes(_data);
    }

    public LongField(long _value){
        this.value = _value;
    }

    public byte[] getBytes() {
        byte[] data = new byte[8];

        long temp = value;

        for(int i = 7; i >= 0; i--){
            data[i] = (byte) temp;
            temp >>>= 8;
        }

        return data;
    }

    public void setBytes(OffsetByteArray data) throws InvalidData {
        value = 0L;

        if(data.length() < 8){
            throw new InvalidData();
        }

        for(int i = 0; i < 8; i++){
            value <<= 8;
            value |= Byte.toUnsignedLong(data.get(i));
        } 
    }

    public Long getValue() {
        return value;
    }

    public int getLength() {
        return TypeSizes.SIZE_OF_LONG;
    }

}
