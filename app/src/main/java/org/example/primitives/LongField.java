package org.example.primitives;

import org.example.primitives.exceptions.InvalidData;

public class LongField extends Field<Long>{

    public LongField(byte[] _data) throws InvalidData{
        super(_data);
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

    @Override
    public void setBytes(byte[] data) throws InvalidData {
        value = 0L;

        if(data.length != 8){
            throw new InvalidData();
        }

        for(int i = 0; i < 8; i++){
            value <<= 8;
            value |= Byte.toUnsignedLong(data[i]);
        } 
    }

}
