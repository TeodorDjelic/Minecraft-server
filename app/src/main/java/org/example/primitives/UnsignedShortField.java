package org.example.primitives;

import org.example.binary.TypeSizes;
import org.example.primitives.exceptions.InvalidData;
import org.example.structures.OffsetByteArray;

public class UnsignedShortField {

    private short value;

    public UnsignedShortField(OffsetByteArray _data) throws InvalidData{
        setBytes(_data);
    }

    public UnsignedShortField(short _value){
        this.value = _value;
    }

    public byte[] getBytes() {
        byte[] data = new byte[2];

        data[1] = (byte) value;
        data[0] = (byte) (value >>> 8);

        return data;
    }

    public void setBytes(OffsetByteArray data) throws InvalidData {
        
        if(data.length() < 2){
            throw new InvalidData();
        }

        value = (short) ((Byte.toUnsignedInt(data.get(0)) << 8) | Byte.toUnsignedInt(data.get(1)));
    }

    public Short getValue() {
        return value;
    }

    public int getLength() {
        return TypeSizes.SIZE_OF_SHORT;
    }

}
