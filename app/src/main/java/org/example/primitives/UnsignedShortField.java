package org.example.primitives;

import org.example.primitives.exceptions.InvalidData;

public class UnsignedShortField extends Field<Short> {

    public UnsignedShortField(byte[] _data) throws InvalidData{
        super(_data);
    }

    public UnsignedShortField(short _value){
        super(_value);
    }

    @Override
    public byte[] getBytes() {
        byte[] data = new byte[2];

        data[1] = (byte) value.shortValue();
        data[0] = (byte) (value >>> 8);

        return data;
    }

    @Override
    public void setBytes(byte[] data) throws InvalidData {
        
        if(data.length != 2){
            throw new InvalidData();
        }

        value = (short) ((Byte.toUnsignedInt(data[0]) << 8) | Byte.toUnsignedInt(data[1]));
    }

}
